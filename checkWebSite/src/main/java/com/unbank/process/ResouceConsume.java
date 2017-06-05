package com.unbank.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.unbank.mybatis.entity.WebSiteInfo;

public class ResouceConsume implements Runnable {
	LinkedBlockingQueue<Object> resoucreQueue;

	public ResouceConsume(LinkedBlockingQueue<Object> resoucreQueue) {
		this.resoucreQueue = resoucreQueue;
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (resoucreQueue.size() > 0) {
				try {
					WebSiteInfo webSiteInfo = (WebSiteInfo) resoucreQueue
							.take();
					savetoExcel(webSiteInfo);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void savetoExcel(WebSiteInfo webSiteInfo) {
		String fileName = getyyyyMMddTimeString(new Date(), 0) + ".xlsx";
		File file = new File(fileName);
		XSSFWorkbook xwb = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		if (!file.exists()) {
			// 第一次 的时候弄的
			String rows[] = new String[] { "新闻id", "新闻url", "板块名称", "原因" };
			xwb = new XSSFWorkbook();
			sheet = xwb.createSheet();
			row = sheet.createRow(0);
			for (int i = 0; i < rows.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(rows[i]);

			}
		} else {
			try {
				xwb = (XSSFWorkbook) WorkbookFactory
						.create(new FileInputStream(file));
				sheet = xwb.getSheetAt(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		row = sheet.createRow(sheet.getLastRowNum() + 1);
		Cell cell = row.createCell(0);
		cell.setCellValue(webSiteInfo.getWebsiteId());
		cell = row.createCell(1);
		cell.setCellValue(webSiteInfo.getUrlHome());
		cell = row.createCell(2);
		cell.setCellValue(webSiteInfo.getWebName());
		cell = row.createCell(3);
		cell.setCellValue(webSiteInfo.getMsg());
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			xwb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			fileName = "";
		}
	}

	public String getyyyyMMddTimeString(Date date, int num) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, num);
		date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}

}
