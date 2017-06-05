package com.unbank.quartz.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unbank.dao.StatsDataStorer;
import com.unbank.dao.StatsTreeStorer;
import com.unbank.ftp.SendFileByftp;
import com.unbank.mybatis.entity.DataModel;
import com.unbank.mybatis.entity.TreeModel;

public class QuartzTask {

	public static void main(String[] args) {
		  new QuartzTask() .dotask(
		  "country_datafield_month,country_datafield_quarter,region_datafield_month,region_datafield_quarter",
		  "country_month_data,country_quarter_data,region_month_data,region_quarter_data"
		  );
		 
	}

	public void dotask(String trees, String datas) {
		// 先修改时间
		new UpdateTimeTask().doTask(trees, datas);
		System.out.println("更新时间完成");
		// 修改数据地图mapFile并上传 ,不要我处理了
		// new UpdateMapFileTask().doTask();
//		System.out.println("修改数据地图的Map 完成");
		// 后上传
		// 上传tree 和data
		uploadData(trees, datas);

	}

	private void uploadData(String trees, String datas) {
		String path = null;
		try {
			path = QuartzTask.class.getClassLoader().getResource("").toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		File file = new File(path + "/result");
		if (!file.exists()) {
			file.mkdirs();
		}
		/*
		 * String[] treestableNames = { "country_datafield_month",
		 * "country_datafield_quarter", "country_datafield_year",
		 * "region_datafield_month", "region_datafield_quarter",
		 * "region_datafield_year" };
		 */
		String[] treestableNames = trees.split(",");
		for (String tableName : treestableNames) {
			new Thread(new MyTreesTask(tableName.trim(), file)).start();
		}
		/*
		 * String[] datatableNames = { "country_month_data",
		 * "country_quarter_data", "country_year_data", "region_month_data",
		 * "region_quarter_data", "region_year_data" };
		 */
		String[] datatableNames = datas.split(",");
		for (String tableName : datatableNames) {
			new Thread(new MyDataTask(tableName.trim(), file)).start();
		}
	}
}

class MyDataTask extends MyTreesTask implements Runnable {
	public MyDataTask(String tableName, File file) {
		super(tableName, file);
	}

	public boolean doTask(BufferedWriter bw) {
		List<DataModel> dataModels = new StatsDataStorer().getDatas(tableName);
		if (dataModels.size() == 0) {
			return true;
		} else {
			StringBuffer sb = new StringBuffer();
			StringBuffer idsb = new StringBuffer();
			for (DataModel dataModel : dataModels) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonstr = gson.toJson(dataModel);
				sb.append(jsonstr + "\n");
				idsb.append(dataModel.getId() + ",");
			}
			try {
				bw.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String ids = idsb.substring(0, idsb.length() - 1);
			new StatsDataStorer().updateTask(tableName, ids);
		}
		return false;

	}
}

class MyTreesTask implements Runnable {
	protected String tableName;
	protected File file;

	public MyTreesTask(String tableName, File file) {
		super();
		this.tableName = tableName;
		this.file = file;
	}

	public void run() {

		// 需要格式化一下 将 task 设置为0
		new StatsTreeStorer().updateTask(tableName);
		//
		File tableFile = new File(file.getAbsolutePath() + "/" + tableName + ".json");
		if (tableFile.exists()) {
			tableFile.delete();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + "/" + tableName + ".json", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			while (true) {
				boolean isBreak = doTask(bw);
				if (isBreak) {
					break;
				}
			}
			bw.flush();
			fos.close();
			osw.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(file.getAbsolutePath() + "/" + tableName + ".json   正在上传");
		File tempfile = new File(file.getAbsolutePath() + "/" + tableName + ".json");
		if (tempfile.length() != 0) {
			// 如果文件为空就不上传了
			FileInputStream input = null;
			try {
				input = new FileInputStream(tempfile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			/*
			 * new SendFileBySftp().uploadFile("10.0.2.126", 22, "sftpuser",
			 * "Windows2008", "/hgdata/", tempfile.getName(), input);
			 */

			
		/*	  new SendFileByftp().uploadFile("123.56.182.93", 21, "ftpuser",
			 "ftpuser", "/plugin/hgdata/", tempfile.getName(), input);*/
			

			new SendFileByftp().uploadFile("10.0.0.16", 21, "ftpuser", "Windows2012", "/plugin/hgdata/",
					tempfile.getName(), input);
 
		}

	}

	public boolean doTask(BufferedWriter bw) {
		List<TreeModel> treeModels = new StatsTreeStorer().getTrees(tableName);
		if (treeModels.size() == 0) {
			return true;
		} else {
			StringBuffer sb = new StringBuffer();
			StringBuffer idsb = new StringBuffer();
			for (TreeModel treeModel : treeModels) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				String jsonstr = gson.toJson(treeModel);
				sb.append(jsonstr + "\n");
				idsb.append(treeModel.getId() + ",");
			}
			try {
				bw.write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			String ids = idsb.substring(0, idsb.length() - 1);
			new StatsDataStorer().updateTask(tableName, ids);
		}
		return false;

	}

}
