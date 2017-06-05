////package com.unbank.task;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//
//import com.unbank.dao.QuotasStorer;
//import com.unbank.fetch.Fetcher;
//import com.unbank.fetch.URLBaseFilter;
//import com.unbank.mybatis.entity.QgydData;
//import com.unbank.store.QgydDataStorer;
//import com.unbank.tools.DataIndexer;
//import com.unbank.tools.MD5;
//
//@Component
//public class StatsQgSpider {
//
//	private final static String url = "http://data.stats.gov.cn/tablequery.htm";
//	private final static String charset = "utf-8";
//
//	private static boolean update = false;
//	private static String tablePre = "qgyd";
//	public static Map<Integer, String> dataindex = new HashMap<Integer, String>();
//	public static void main(String[] args) {
//		dataindex.put(2, "");
//		dataindex.put(4, "");
//		dataindex.put(6, "");
//		new StatsQgSpider().getQuotasTree();
//	}
//	
//	
//	
//	public static Fetcher fetcher = Fetcher.getInstance();
//	 Map<String, String> header=new HashMap<String, String>();
//	private HashMap<String, String> headers=new HashMap<String, String>();
//	static URLBaseFilter urlFilter = new URLBaseFilter();
//	
//	
//	public void getQuotasTree() {
//		headers = new HashMap<String, String>();
//		headers.put("Accept", "text/plain, */*; q=0.01");
//		headers.put("Accept-Encoding", "gzip, deflate");
//		headers.put("Accept-Language", "zh-CN,zh;q=0.8");
//		headers.put("Cache-Control", "no-cache");
//		headers.put("Connection", "keep-alive");
//		headers.put("Content-Type", "application/x-www-form-urlencoded");
//		headers.put("Host", "data.stats.gov.cn");
//		headers.put("Origin", "http://data.stats.gov.cn");
//		headers.put("Pragma", "no-cache");
//		headers.put(
//				"User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
//		headers.put("X-Requested-With", "XMLHttpRequest");
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("id", "AD");
//		params.put("dbcode", tablePre);
//		params.put("name", "年度");
//		params.put("m", "getTree");
//		getTree(params, "");
//
//	}
//
//	public void getTree(Map<String, String> params, String name) {
//		
//		
//		JSONArray treeArray = getJsonArray(params);
//		List<Map<String, Object>> result = getTree(treeArray);
//		for (Map<String, Object> map : result) {
//			String id = (String) map.get("id");
//			name = (String) map.get("name");
//			int idLength = id.length();
//			String dataindexName = dataindex.get(idLength);
//			if (dataindexName.isEmpty()) {
//
//			} else {
//				if (!dataindexName.equals(name)) {
//					continue;
//				} else {
//					dataindex.put(idLength, "");
//				}
//			}
//			new DataIndexer().writerIndex(name, idLength);
//			
////			case 2:
////				// new MysqlTableMaker().makeTableSql("quotas",map);
////
////				if (!update) {
////					new QuotasStorer().saveQuotas(tablePre + "_quotas", map);
////				}
////				break;
////			case 4:
////				// new MysqlTableMaker().makeTableSql("category",map);
////				if (!update) {
////					new QuotasStorer().saveQuotas(tablePre + "_category", map);
////				}
////				break;
////			case 7:
////				// new MysqlTableMaker().makeTableSql("subcategory",map);
////				if (!update) {
////					new QuotasStorer().saveQuotas(tablePre + "_subcategory",
////							map);
////				}
////				break;
////			case 9:
////				// new MysqlTableMaker().makeTableSql("subcategory",map);
////				if (!update) {
////					new QuotasStorer().saveQuotas(tablePre + "_treecategory",
////							map);
////				}
//			//	break;
////			default:
////
////				break;
////			}
//			if (!update) {
//				new QuotasStorer().saveQuotas(tablePre + "_tree",
//						map);
//			}
//			boolean isp = true;
//			Object isPObject = map.get("isParent");
//			if (isPObject instanceof String) {
//				String isParent = (String) map.get("isParent");
//				if (isParent.equals("0")) {
//					isp = false;
//				} else {
//					isp = true;
//				}
//			} else if (isPObject instanceof Boolean) {
//				Boolean isParent = (Boolean) map.get("isParent");
//				if (isParent) {
//					isp = true;
//				} else {
//					isp = false;
//				}
//
//			}
//			if (isp) {
//				// 获取下一级
//				params.put("id", id);
//				getTree(params, name);
//
//			} else {
//				Map<String, String> params1 = new HashMap<String, String>();
//				params.put("id", id);
//				params.put("name", "年度");
//				params.put("m", "getTree");
//				long longtime=new Date().getTime();
//				String durl= url+"?m=OtherWds&code="+id+"&_="+longtime;
//				String html = fetcher.post(durl, params1, headers, charset);
//				System.out.println(html);
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				JSONArray resultBody = JSONArray.fromObject(html);
//				String nodes=null;
//				for (Object object : resultBody) {
//					JSONObject o = JSONObject.fromObject(object);
//					nodes= o.get("nodes").toString();
//				}
//				JSONArray resultBody1 = JSONArray.fromObject(nodes);
//				for (Object object1 : resultBody1) {
//					JSONObject o1 = JSONObject.fromObject(object1);
//					String time = o1.get("code").toString();
//					clikeTime(id,time);
//				}
//			}
//		}
//	}
//
//	private void clikeTime(String id, String time) {
//			Map<String, String> params = new HashMap<String, String>();
//			String url1 = url+"?m=QueryData&code="+id+"&wds=%5B%7B%22wdcode%22%3A%22reg%22%2C%22valuecode%22%3A%22000000%22%7D%2C%7B%22wdcode%22%3A%22sj%22%2C%22valuecode%22%3A%22"+time+"%22%7D%5D";
//			 String  html1= fetcher.post(url1, params, headers, charset);
//		    String html=html1.toString().split("htmltable")[1].substring(3).split("memos")[0];
//		    String text=html.substring(0,html.length()-3);
//		    String detail= id+time;
//		   System.out.println(detail);
//		   if (urlFilter.checkNewsURL(detail)) {
//				try {
//					QgydData newHgyd =new QgydData();
//					newHgyd.setText(text);
//					newHgyd.setTime(time);
//					newHgyd.setHgyddeatail(detail);
//					newHgyd.setCategoryid(id);
//					new  QgydDataStorer().saveNewHgyd(newHgyd);
//				}catch (Exception e) {
//				}
//			}
//		
//	}
//
//	// 获取导航树
//	public String spiderNode(Map<String, String> params) {
//		String html = fetcher.post(url, params, null, charset);
//		return html;
//	}
//
//	public JSONArray getJsonArray(Map<String, String> params) {
//		String html = spiderNode(params);
//		JSONArray treeArray = JSONArray.fromObject(html);
//		return treeArray;
//	}
//
//	public List<Map<String, Object>> getTree(JSONArray treeArray) {
//		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		for (Object object : treeArray) {
//			JSONObject jsonObject = JSONObject.fromObject(object);
//			Map<String, Object> resultMap = (Map<String, Object>) JSONObject
//					.toBean(jsonObject, Map.class);
//			String id = (String) resultMap.get("id");
//			resultMap.put("uniqueid", MD5.GetMD5Code(id));
//			result.add(resultMap);
//		}
//		return result;
//	}
//
//	// 获取最后结果
//
//	public JSONObject getJsonObject(Map<String, String> resultparams) {
//		String html = spiderNode(resultparams);
//		JSONObject jsonObject = JSONObject.fromObject(html);
//		return jsonObject;
//	}
//
//	public void getResult(JSONObject jsonObject, String linkPid) {
//		JSONObject returndata = jsonObject.getJSONObject("returndata");
//		JSONArray dataNodeArray = returndata.getJSONArray("datanodes");
//		JSONArray wdNodeArray = returndata.getJSONArray("wdnodes");
//		Map<String, Map<String, Object>> wdMap = parserWdNode(wdNodeArray,
//				linkPid);
//		parserDataNode(dataNodeArray, wdMap);
//
//	}
//
//	private Map<String, Map<String, Object>> parserWdNode(
//			JSONArray wdNodeArray, String linkPid) {
//		Object object = wdNodeArray.get(0);
//		JSONArray nodes = JSONArray.fromObject(JSONObject.fromObject(object)
//				.get("nodes"));
//		Map<String, Map<String, Object>> wdMap = new HashMap<String, Map<String, Object>>();
//		for (Object node : nodes) {
//			JSONObject jsonObject = JSONObject.fromObject(node);
//			Map<String, Object> htmlMap = (Map<String, Object>) JSONObject
//					.toBean(jsonObject, Map.class);
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap.put("cname", htmlMap.get("cname"));
//			resultMap.put("code", htmlMap.get("code"));
//			resultMap.put("dotcount", htmlMap.get("dotcount"));
//			resultMap.put("name", htmlMap.get("name"));
//			resultMap.put("nodesort", htmlMap.get("nodesort"));
//			resultMap.put("sortcode", htmlMap.get("sortcode"));
//			resultMap.put("unit", htmlMap.get("unit"));
//			resultMap.put("uniqueid", MD5.GetMD5Code(htmlMap.get("code") + ""));
//			resultMap.put("linkPid", linkPid);
//			wdMap.put((String) resultMap.get("code"), resultMap);
//			if (!update) {
//				new QuotasStorer().saveQuotas(tablePre + "_wd", resultMap);
//			}
//		}
//
//		return wdMap;
//	}
//
//	private void parserDataNode(JSONArray dataNodeArray,
//			Map<String, Map<String, Object>> wdMap) {
//
//		for (Object object : dataNodeArray) {
//			JSONObject datanode = JSONObject.fromObject(object);
//			String code = datanode.getString("code");
//			String wdcode = StringUtils.substringBetween(code, "zb.", "_sj.");
//			String sjcode = StringUtils.substringAfterLast(code, "_sj.");
//			JSONObject dataInfo = datanode.getJSONObject("data");
//			Double data = dataInfo.getDouble("data");
//			int dotcount = dataInfo.getInt("dotcount");
//			boolean hasdata = dataInfo.getBoolean("hasdata");
//			String strdata = dataInfo.getString("strdata");
//			if (hasdata) {
//				Map<String, Object> resultMap = new HashMap<String, Object>();
//				resultMap.put("code", code);
//				resultMap.put("wdcode", wdcode);
//				resultMap.put("sjcode", sjcode);
//				resultMap.put("data", data);
//				resultMap.put("dotcount", dotcount);
//				resultMap.put("hasdata", hasdata);
//				resultMap.put("strdata", strdata);
//				resultMap.put("uniqueid", MD5.GetMD5Code(code));
//				new QuotasStorer().saveQuotas(tablePre + "_data", resultMap);
//
//			}
//
//		}
//
//	}
//
//}
