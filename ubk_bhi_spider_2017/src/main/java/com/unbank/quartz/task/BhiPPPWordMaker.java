package com.unbank.quartz.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.unbank.dao.BaseDao;
import com.unbank.dao.BhiPppReader;
import com.unbank.mybatis.vo.BhiPPPWithBLOBs;

import net.sf.json.JSONObject;
/****
 * 运行前截断 bhi_ppp_word 
 * 
 * 
 * @ClassName:  BhiPPPWordMaker
 * @Description: TODO
 * @author:  liangyangtao
 * @date:  2017年3月6日 上午11:07:16
 */
public class BhiPPPWordMaker {

	public static void main(String[] args) {
		List<BhiPPPWithBLOBs> bhiPPPs = new BhiPppReader().readBhiPppReader("");
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
			String projectName = (String) infoMap.get("ProjectName");
			if (projectNames.contains(projectName)) {
				continue;
			} else {
				projectNames.add(projectName);
			}
			makeInfoExcel(infoMap);
		}

	}

	private static void makeInfoExcel(Map<String, Object> infoMap) {
		Set<String> keyset = infoMap.keySet();
		Iterator<String> iterator = keyset.iterator();
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		while (iterator.hasNext()) {
			String key = iterator.next();
//			System.out.println(key);
			Object object = infoMap.get(key);
//			System.out.println(object);
			/**
			 * 2016-11-4 09:22:57 新增存入数据库部分
			 * 
			 * */
			if (key.equals("ProjectName")) {
				sqlMap.put("ppp_name", object);
			} else if (key.equals("GreaterArea")) {
				sqlMap.put("ppp_region", object);
			} else if (key.equals("Province")) {
				String ind = object + "";
				String temp = ind.split(",")[0];
				sqlMap.put("ppp_area", temp);
			} else if (key.equals("Industry")) {
				// 环保,市政
				String ind = object + "";
				String temp = ind.split(",")[0];
				sqlMap.put("ppp_ind", temp);
			} else if (key.equals("ProjectType")) {
//				String ind = object + "";
//				String temp = ind.split(",")[0];
				sqlMap.put("ppp_type", object);
			} else if (key.equals("OperationMode")) {
				sqlMap.put("ppp_model", object);
			}/*
			 * else if(key.equals("ProjectDescription")){ sqlMap.put(key,
			 * object); }
			 */else if (key.equals("Investment")) {
				sqlMap.put("ppp_assets", object);
			} else if (key.equals("Level")) {
				sqlMap.put("ppp_level", object);
			}/*
			 * else if(key.equals("Company")){ sqlMap.put(key, object); }
			 */else if (key.equals("CreateTime")) {
				sqlMap.put("ppp_utime", object);
			} else if (key.equals("EditTime")) {
				sqlMap.put("ppp_itime", object);
			}
			// }else if(key.equals("title")){
			// sqlMap.put(key, object);
			// }else if(key.equals("EventsID")){
			// sqlMap.put(key, object);
			// }
		}
		String sql = "insert into bhi_ppp_word ";
		new BaseDao().executeMapSQL(sql, sqlMap);

	}

}
