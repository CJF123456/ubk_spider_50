package com.unbank.quartz.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unbank.dao.MapFileDataStorer;
import com.unbank.dao.StatsTreeStorer;
import com.unbank.ftp.SendFileByftp;
import com.unbank.mybatis.entity.MapFileHomeModel;
import com.unbank.mybatis.entity.MapFileModel;
import com.unbank.mybatis.entity.MapFileTypeModel;
import com.unbank.mybatis.entity.TreeModel;

public class UpdateMapFileTask {

	public void doTask() {
		// 先更新数据SUBId 和vid
		updateSubIdAndVid();
		// 更新首页的表
		updateMapFileHomeModel();
		// 上传到服务器
		uploadFile("map_field", getMapFieldResult());
		uploadFile("map_field_homepage", getMapFieldHomepageResult());

	}

	// 获取MapFieldHomepage 列表
	private String getMapFieldHomepageResult() {
		StringBuffer sb = new StringBuffer();
		List<MapFileHomeModel> mapFileModels = new MapFileDataStorer()
				.readMapFileHomeModels();
		for (MapFileHomeModel mapFileModel : mapFileModels) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonstr = gson.toJson(mapFileModel);
			sb.append(jsonstr + "\n");
		}
		return sb.toString();
	}

	private String getMapFieldResult() {
		StringBuffer sb = new StringBuffer();
		List<MapFileModel> mapFileModels = new MapFileDataStorer()
				.readMapFileModels();
		for (MapFileModel mapFileModel : mapFileModels) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonstr = gson.toJson(mapFileModel);
			sb.append(jsonstr + "\n");
		}
		return sb.toString();
	}


	// 修改map_field_homepage 填上 subId 和单位
	private void updateMapFileHomeModel() {
		List<MapFileHomeModel> mapFileModels = new MapFileDataStorer()
				.readMapFileHomeModels();
		for (MapFileHomeModel mapFileHomeModel : mapFileModels) {
			String[] pnames = mapFileHomeModel.getPname().split(",");
			int pid = new StatsTreeStorer().getLastTreeIdByName(
					"region_datafield_month", pnames);
			if (pid > 0) {
				List<TreeModel> treeModels = new StatsTreeStorer()
						.getTreesByid("region_datafield_month", pid);
				// 更新表
				if (treeModels.size() > 0) {
					new MapFileDataStorer().updateMapFileHomeModel(
							mapFileHomeModel.getId(), treeModels.get(0),
							"sub_id");
				}
			}
		}
	}

	private void uploadFile(String tableName, String result) {
		String path = null;
		try {
			path = UpdateMapFileTask.class.getClassLoader().getResource("")
					.toURI().getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		File file = new File(path + "/result");
		if (!file.exists()) {
			file.mkdirs();
		}

		File tableFile = new File(file.getAbsolutePath() + "/" + tableName
				+ ".json");
		if (tableFile.exists()) {
			tableFile.delete();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath()
					+ "/" + tableName + ".json", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(result);
			bw.flush();
			fos.close();
			osw.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath() + "/" + tableName
				+ ".json   正在上传");
		File tempfile = new File(file.getAbsolutePath() + "/" + tableName
				+ ".json");
		if (tempfile.length() != 0) {
			// 如果文件为空就不上传了
			FileInputStream input = null;
			try {
				input = new FileInputStream(tempfile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			/*
			 * sftp new SendFileBySftp().uploadFile("10.0.2.126", 22,
			 * "sftpuser", "Windows2008", "/hgdata/", tempfile.getName(),
			 * input);
			 */
			// ftp

			/*
			 * new SendFileByftp().uploadFile("123.56.182.93", 21, "ftpuser",
			 * "ftpuser", "/plugin/hgdata/", tempfile.getName(), input);
			 */

			new SendFileByftp()
					.uploadFile("10.0.0.16", 21, "ftpuser", "Windows2012",
							"/plugin/hgdata/", tempfile.getName(), input);
		}

	}

	// 修改map_field 填上 subId 和单位 vid
	private void updateSubIdAndVid() {

		// 将 pid 和vid 更新为 0
		new MapFileDataStorer().updateVidAndPid();
		//

		List<MapFileModel> mapFileModels = new MapFileDataStorer()
				.readMapFileModels();
		for (MapFileModel mapFileModel : mapFileModels) {
			int type = mapFileModel.getType();
			String pname = mapFileModel.getPname() + ","
					+ mapFileModel.getName();
			String pnames[] = null;
			// 分省指标
			if (!pname.isEmpty()) {
				pnames = pname.split(",");
			}
			String vname = mapFileModel.getVname();
			String vnames[] = null;
			if (!vname.isEmpty()) {
				vnames = vname.split(",");
			}
			MapFileTypeModel mapFileTypeModel = new MapFileDataStorer()
					.readMapFileTypeById(type);
			int mode = mapFileTypeModel.getMode();
			String pTableName = null;
			String vTabelName = null;
			switch (mode) {
			case 1:
				// 分省年度
				pTableName = "region_datafield_year";
				vTabelName = "country_datafield_year";
				break;
			case 2:
				// 分省季度
				pTableName = "region_datafield_quarter";
				vTabelName = "country_datafield_quarter";
				break;
			case 3:
				// 分省月度
				pTableName = "region_datafield_month";
				vTabelName = "country_datafield_month";
				break;
			default:
				break;
			}

			if (vnames != null) {
				int vid = new StatsTreeStorer().getLastTreeIdByName(vTabelName,
						vnames);
				if (vid > 0) {
					List<TreeModel> treeModels = new StatsTreeStorer()
							.getTreesByid(vTabelName, vid);
					if (treeModels.size() > 0) {

						new MapFileDataStorer()
								.updateMapFileModel(mapFileModel.getId(),
										treeModels.get(0), "v_id");
					} else {
						System.out.println(vid + "  " + vTabelName);
					}
				}
			}

			if (pnames != null) {
				// 寻找真实的id
				int pid = new StatsTreeStorer().getLastTreeIdByName(pTableName,
						pnames);
				if (pid > 0) {

					List<TreeModel> treeModels = new StatsTreeStorer()
							.getTreesByid(pTableName, pid);

					// 更新表
					if (treeModels.size() > 0) {
						new MapFileDataStorer().updateMapFileModel(
								mapFileModel.getId(), treeModels.get(0),
								"sub_id");
					} else {
						System.out.println(pid + "   " + pTableName);
					}

				}

			}

		}
	}
}
