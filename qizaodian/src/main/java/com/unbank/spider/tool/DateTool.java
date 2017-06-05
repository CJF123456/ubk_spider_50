/**
 * 
 */
package com.unbank.spider.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateTool
 * @Description: 时间戳转化成Date
 * @author: Administrator
 * @version: V1.0
 * @date: 2016-11-28 下午4:05:03
 */
public class DateTool {

	private static Date date=null;
	private String dateTime = null;

	/**
	 * @description: 时间戳转成时间
	 * @param: @param time 时间戳
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public String getDateByLong(String time) {
		long longtime = Long.parseLong(time);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(longtime * 1000);
		dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c
				.getTime());
		return dateTime;
	}

	
	/*private void sleep() {
	try {
		Random rand = new Random();
		int randNum = rand.nextInt(1) + 2;
		Thread.sleep(1000 * randNum);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}*/
	public static Date getStringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * @description: 写成文件
	 * @param: @param path
	 * @param: @param name
	 * @param: @param con
	 * @return: void
	 * @throws
	 */
	/*public void saveToFile(String path, String keyword, String con) {
		if (!keyword.endsWith(".txt"))
			keyword = keyword + ".txt";
		File file = new File(path + File.separator + keyword);
		FileOutputStream fos = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file, true);
			fos.write(con.getBytes());
			fos.write('\r');
		} catch (Exception e) {
			System.out.println("文件出错");
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}
