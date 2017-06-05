package com.unbank.quartz.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.unbank.dao.BhiPppReader;
import com.unbank.mybatis.vo.BhiPPPWithBLOBs;
import com.unbank.tools.WordLorder;

/****
 *  每月执行一次，
 *  
 *  改时间
 *  	public static String startBijiaoTime = "2017/02/01";
	public static String endBijiaoTime = "2017/02/28";
	public static String fileTime = "201702";
	
	运行即可
 *  
 * @ClassName:  BhiPppExcelxlsMaker
 * @Description: TODO
 * @author:  liangyangtao
 * @date:  2017年3月6日 上午11:02:20
 */
public class BhiPppExcelxlsMaker extends WordLorder {
	public static int index = 1;
	public static String startBijiaoTime = "2017/02/01";
	public static String endBijiaoTime = "2017/02/28";
	public static String fileTime = "201702";

	public static void main(String[] args) {
		// '%特许经营期限%'
		// '%火喷淋系统%'
		// '%世纪之窗%'
		//'%日月湖%'”
		String[] areas = new String[] {  "", "湖南", "江苏", "内蒙古", "河北", "陕西",
				"山东", "安徽", "山西", "江西", "广东", "宁夏", "福建", "四川", "辽宁", "广西",
				"湖北", "吉林", "河南", "贵州", "新疆", "西藏", "黑龙江", "重庆", "甘肃", "浙江",
				"上海", "北京", "云南", "天津", "青海", "海南" };
		for (String area : areas) {
			List<BhiPPPWithBLOBs> bhiPPPs = new BhiPppReader()
					.readBhiPppReader(area);
			List<String> zonglist = loadWords(BhiPppExcelxlsMaker.class
					.getResourceAsStream("zong2.txt"));

			List<String> fenglist = loadWords(BhiPppExcelxlsMaker.class
					.getResourceAsStream("feng2.txt"));

			HSSFWorkbook xwb = new HSSFWorkbook();
			HSSFSheet sheet = xwb.createSheet("info");
			String[] a = new String[] { "编号", "项目名称", "地区", "省份", "项目类别", "类型",
					"合作模式", /* "项目简介", */"总投资（亿元）", "级别", /* "是否有参与单位信息" */
					"更新时间", "发布时间", /* "是否有进展跟踪", *//* "是否有联系人信息", */"项目详细描述",
					"公司参与信息", "项目进展信息", "联系人信息", "所涉总行级战略客户集团名称",
					"所涉分行级战略客户集团名称" };
			HSSFRow row = sheet.createRow(0);
			for (int i = 0; i < a.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(a[i]);
			}
			int rowIndex = 1;
			boolean isMakeFile = false;
			Set<String> projectNames = new HashSet<String>();
			for (int j = 0; j < bhiPPPs.size(); j++) {
				BhiPPPWithBLOBs bhiPPP = bhiPPPs.get(j);
				String info = bhiPPP.getInfo();
				String description = bhiPPP.getDescription();
				if (description == null || description.isEmpty()) {
					continue;
				}
				JSONObject infoJsonObject = JSONObject.fromObject(info);
				LinkedHashMap<String, Object> infoMap = (LinkedHashMap<String, Object>) JSONObject
						.toBean(infoJsonObject, LinkedHashMap.class);
				description = description.replace("\r\n", "");
				description = description.replace("\n", "");
				description = description.replace("	", "");
				description = description.replace(" ", "");
				description = description.replace("\\\"", "\"");
				description = description.replace(": \"\",", "@@@");
				description = description.replace(":\"\",", "@@@");
				description = description.replace(":\"\"}", "@@@@");
				description = description.replace("\"\"", "\"");
				description = description.replace("@@@@", ":\"\"}");
				description = description.replace("@@@", ":\"\",");
				description = description.replace("\\", "");
				JSONObject jsonObject = JSONObject.fromObject(description);
				String createTime = (String) infoMap.get("CreateTime");
				String projectName = (String) infoMap.get("ProjectName");
				long createDate = Date.parse(createTime);

				long startbijiaoDate = Date.parse(startBijiaoTime);

				long endBijiaoDate = Date.parse(endBijiaoTime);
				// 20160901
				if (startbijiaoDate > createDate) {
					continue;
				}
				if (createDate > endBijiaoDate) {
					continue;
				}
				if (projectNames.contains(projectName)) {
					continue;
				} else {
					projectNames.add(projectName);
				}
				isMakeFile = true;
				row = sheet.createRow(rowIndex++);
				Cell cell = row.createCell(0);
				cell.setCellValue(rowIndex - 1);
				makeInfoExcel(infoMap, row);
				makeDesExcel(jsonObject, row);
				boolean isZong = false;
				String zongjituanName = "";
				for (String string : zonglist) {
					String temp[] = string.split("@@@");
					if (description.contains(temp[1].trim())) {
						isZong = true;
						zongjituanName = temp[0].trim();
						break;
					}
					if (info.contains(string.trim())) {
						isZong = true;
						zongjituanName = temp[0].trim();
						break;
					}
				}
				cell = row.createCell(15);
				if (isZong) {
					cell.setCellValue(zongjituanName);
				}

				boolean isFeng = false;
				String fengjituanName = "";
				for (String string : fenglist) {
					String temp[] = string.split("@@@");
					if (description.contains(temp[1].trim())) {
						isFeng = true;
						fengjituanName = temp[0].trim();
						break;
					}
					if (info.contains(temp[1].trim())) {
						isFeng = true;
						fengjituanName = temp[0].trim();
						break;
					}
				}

				cell = row.createCell(16);
				if (isFeng) {
					cell.setCellValue(fengjituanName);
				}

				index = 1;
			}
			if (!isMakeFile) {
				continue;
			}
			String path = null;
			try {
				path = BhiPppExcelxlsMaker.class.getClassLoader()
						.getResource("").toURI().getPath();
				path = path.substring(1, path.length());
			} catch (Exception e) {
				e.printStackTrace();
			}
			String filePath = path + "temp/";
			File file = new File(filePath);
			if (!file.exists()) {
				System.out.println("执行");
				file.mkdirs();
			}
			StringBuffer fileName = new StringBuffer();
			fileName.append(area + "PPP项目报告");
			fileName.append(fileTime);
			fileName.append(".xls");
			filePath = filePath + fileName.toString();
			try {
				FileOutputStream fileOut = new FileOutputStream(filePath);
				xwb.write(fileOut);
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void makeDesExcel(JSONObject jsonObject, HSSFRow row) {

		if (jsonObject.containsKey("Description")) {

			JSONArray jsonArray = jsonObject.getJSONArray("Description");
			JSONObject descriptionjsonObject = JSONObject.fromObject(jsonArray
					.get(0));
			if (descriptionjsonObject.containsKey("ProjectDescription")) {
				String projectDescription = descriptionjsonObject
						.getString("ProjectDescription");
				Cell cell = row.createCell(11);
				cell.setCellValue(projectDescription);
			}
			if (descriptionjsonObject.containsKey("Company")) {
				String company = descriptionjsonObject.getString("Company");
				Cell cell = row.createCell(12);
				cell.setCellValue(company);
			}

		}
		;
		if (jsonObject.containsKey("Progress")) {
			JSONArray jsonArray = jsonObject.getJSONArray("Progress");
			StringBuffer titleBuffer = new StringBuffer();
			for (Object object : jsonArray) {
				JSONObject titleObject = JSONObject.fromObject(object);
				titleBuffer.append(titleObject.get("title") == null ? ""
						: titleObject.get("title"));
				titleBuffer.append("\r\n");
			}
			Cell cell = row.createCell(13);
			cell.setCellValue(titleBuffer.toString());

		}
		if (jsonObject.containsKey("LinkMan")) {
			JSONObject linkManJsonObject = jsonObject.getJSONObject("LinkMan");
			JSONArray linManJsonArray = linkManJsonObject
					.getJSONArray("content");

			// {"owner":"其他单位","company":"邓州市妇幼保健院","more":"","LabelName":"PPP合作方采购单位","address":"","addDate":"2016/1/7 16:19:59","actors":[{"FullName":"刘书记","OfficePhone":"13949383607"}]},
			StringBuffer linkMan = new StringBuffer();
			for (Object object : linManJsonArray) {
				String company = object.toString();
				company = company.replace("{", "");
				company = company.replace("owner", "单位性质");
				company = company.replace("company", "单位名称");
				company = company.replace("more", "其他信息");
				company = company.replace("LabelName", "参与关系");
				company = company.replace("addDate", "参加时间");
				company = company.replace("actors", "联系人");
				company = company.replace("FullName", "联系人姓名");
				company = company.replace("OfficePhone", "联系人电话");
				company = company.replace("address", "单位地址");
				company = company.replace("}", "");
				linkMan.append(company);
			}
			Cell cell = row.createCell(14);
			String linkManStr = linkMan.toString();
			linkManStr = linkManStr.replace("\"", "");
			cell.setCellValue(linkManStr);

		}
	}

	private static void makeInfoExcel(Map<String, Object> infoMap, HSSFRow row) {
		Set<String> keyset = infoMap.keySet();
		Iterator<String> iterator = keyset.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object object = infoMap.get(key);
			String value = null;
			if (object instanceof String) {
				value = (String) object;
			} else if (object instanceof Integer) {
				value = object + "";
			} else if (object instanceof Double) {
				value = object + "";
			} else {
				value = (String) object;
			}
			if (key.equals("ProjectDescription")) {
				continue;
			}
			if (key.equals("Company")) {
				continue;
			}
			if (key.equals("title")) {
				continue;
			}
			if (key.equals("EventsID")) {
				continue;
			}
			if (key.equals("Exportid")) {

			} else {
				Cell cell = row.createCell(index++);
				cell.setCellValue(value);
			}
		}
	}

	private static void makeDescriptionExcel(Map<String, Object> descMap,
			HSSFRow row) {
		Set<String> keyset = descMap.keySet();
		Iterator<String> iterator = keyset.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key);
			Object object = descMap.get(key);
			String value = null;
			if (object instanceof List) {
				JSONArray temp = JSONArray.fromObject(object);
				for (Object tempObject : temp) {
					JSONObject tempJsonObject = JSONObject
							.fromObject(tempObject);
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) JSONObject
							.toBean(tempJsonObject, LinkedHashMap.class);
					makeDescriptionExcel(map, row);
				}
			} else {
				if (object instanceof LinkedHashMap) {
					makeDescriptionExcel((LinkedHashMap) object, row);

				} else {

					if (object instanceof MorphDynaBean) {
						JSONObject tempJsonObject = JSONObject
								.fromObject(object);
						LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) JSONObject
								.toBean(tempJsonObject, LinkedHashMap.class);
						makeDescriptionExcel(tempJsonObject, row);

					} else {
						if (object instanceof String) {
							value = (String) object;
						} else if (object instanceof Integer) {
							value = object + "";
						} else if (object instanceof Double) {
							value = object + "";
						} else {
							System.out.println(object);
						}
						Cell cell = row.createCell(index++);
						cell.setCellValue(value);
					}
				}
			}

		}

	}
}
