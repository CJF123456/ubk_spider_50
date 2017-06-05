package com.unbank.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;

import com.unbank.dao.StatsDataStorer;
import com.unbank.dao.StatsTreeStorer;

/**
 * @author Administrator
 * 
 */
public class StatsFsndSpider extends BaseSpider {

	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(StatsFsndSpider.class
					.getClassLoader().getResource("").toURI().getPath()
					+ "log4j.properties");
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		update = true;
		tablePre = "fsnd";
		tableTreeName = "region_datafield_year"; 
		tableDataName = "region_year_data";
		frequency = 1;
		new StatsFsndSpider().getQuotasTree();
	}

	public void getQuotasTree() {
		for (int i = 0; i < 1; i++) {
			new Thread(new DataFsThread(datas)).start();
		}
		for (int i = 0; i < 1; i++) {
			new Thread(new TreeThread(trees)).start();
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "zb");
		params.put("dbcode", tablePre);
		params.put("wdcode", "zb");
		params.put("m", "getTree");
		getTree(params, 0, 1, "");

	}

	public void parserDataNode(JSONArray dataNodeArray) {
		for (Object object : dataNodeArray) {
			JSONObject datanode = JSONObject.fromObject(object);
			// zb.A01010101_reg.110000_sj.201605
			String code = datanode.getString("code");
			String wdcode = StringUtils.substringBetween(code, "zb.", "_reg.");
			String sjcode = StringUtils.substringAfterLast(code, "_sj.");
			String regcode = StringUtils
					.substringBetween(code, "_reg.", "_sj.");
			JSONObject dataInfo = datanode.getJSONObject("data");
			Double data = dataInfo.getDouble("data");
			int dotcount = dataInfo.getInt("dotcount");
			boolean hasdata = dataInfo.getBoolean("hasdata");
			String strdata = dataInfo.getString("strdata");

			int area_id = regcodeToAreaId(regcode);

			String year = null;
			String month = null;
			switch (sjcode.length()) {
			case 4:
				year = sjcode.substring(0, 4);
				month = "0";
				break;
			case 5:
				year = sjcode.substring(0, 4);
				month = sjcode.substring(4, 5);
				switch (month.charAt(0)) {
				case 'A':
					month = "03";
					break;
				case 'B':
					month = "06";
					break;
				case 'C':
					month = "09";
					break;
				case 'D':
					month = "12";
					break;
				default:
					break;
				}
				break;
			default:
				year = sjcode.substring(0, 4);
				month = sjcode.substring(4, 6);
				break;
			}
			if (hasdata) {
				// 父节点id
				int id = new StatsTreeStorer().checkTreeExit(tableTreeName,
						wdcode, "");
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("sub_id", id);
				resultMap.put("date_year", year);
				resultMap.put("date_month", month);
				resultMap.put("datavalue", strdata);

				resultMap.put("area_id", area_id);
				// resultMap.put("industry_id", "");
				resultMap.put("charascope", "");
				resultMap.put("date_year_int", Integer.parseInt(year));
				resultMap.put("date_month_int", Integer.parseInt(month));
				resultMap.put("insert_time", new Date());
				boolean isExit = new StatsDataStorer().isDataExit(
						tableDataName, id, year, month, area_id);
				if (isExit) {
					System.out.println(id + " " + year + "  " + month + "已经存在");
					continue;
				} else {
					System.out.println(resultMap);
					new StatsDataStorer().saveDateModel(tableDataName,
							resultMap);
				}
				resultMap.clear();
				resultMap = null;

			}

		}

	}

}
