package com.unbank.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.dao.StatsDataStorer;
import com.unbank.dao.StatsTreeStorer;
import com.unbank.fetch.Fetcher;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BaseSpider {
	public final static String url = "http://data.stats.gov.cn/easyquery.htm";
	public final static String charset = "utf-8";
	public static Log logger = LogFactory.getLog(BaseSpider.class);
	public static boolean update;
	public static String tablePre = "";
	public static String tableTreeName;
	public static String tableDataName;
	public static int frequency;
	public static Fetcher fetcher = Fetcher.getInstance();
	public LinkedBlockingQueue<Object> datas = new LinkedBlockingQueue<Object>();
	public LinkedBlockingQueue<Object> trees = new LinkedBlockingQueue<Object>();

	public int regcodeToAreaId(String regcode) {
		return new StatsDataStorer().checkArea(regcode);
	}

	public void getQuotasTree() {

		for (int i = 0; i < 1; i++) {
			new Thread(new DataThread(datas)).start();
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

	/**
	 * 
	 * @param params
	 *            post 参数
	 * @param zbPid
	 *            自增id
	 * @param leval
	 *            级别
	 * @param code
	 *            上次加这层的id 1.3.33
	 */
	public void getTree(Map<String, String> params, int zbPid, int leval, String code) {
		JSONArray treeArray = getJsonArray(params);
		getTree(treeArray, zbPid, leval, code);
	}

	// 获取导航树
	public String spiderNode(Map<String, String> params) {
		String html = fetcher.post(url, params, null, charset);
		return html;
	}

	public JSONArray getJsonArray(Map<String, String> params) {
		String html = spiderNode(params);
		JSONArray treeArray = JSONArray.fromObject(html);
		return treeArray;
	}

	public void getTree(JSONArray treeArray, int zbPid, int leval, String code) {
		for (Object object : treeArray) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			Map<String, Object> resultMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
			resultMap.put("zbPid", zbPid);
			resultMap.put("leval", leval);
			resultMap.put("zbcode", code);
			try {
				trees.put(resultMap);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取最后结果

	public JSONObject getJsonObject(Map<String, String> resultparams) {
		String html = spiderNode(resultparams);
		JSONObject jsonObject = JSONObject.fromObject(html);
		return jsonObject;
	}

	public void getResult(JSONObject jsonObject, Map<String, Object> map) {
		JSONObject returndata = jsonObject.getJSONObject("returndata");
		JSONArray dataNodeArray = returndata.getJSONArray("datanodes");
		JSONArray wdNodeArray = returndata.getJSONArray("wdnodes");
		parserWdNode(wdNodeArray, map);
		parserDataNode(dataNodeArray);

	}

	private void parserWdNode(JSONArray wdNodeArray, Map<String, Object> map) {
		Object object = wdNodeArray.get(0);
		JSONArray nodes = JSONArray.fromObject(JSONObject.fromObject(object).get("nodes"));
		for (Object node : nodes) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			Map<String, Object> htmlMap = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);
			Map<String, Object> treesMap = new HashMap<String, Object>();
			String id = (String) htmlMap.get("code");
			String name = (String) htmlMap.get("name");
			treesMap.put("pid", map.get("zbpid"));
			treesMap.put("value", htmlMap.get("name"));
			treesMap.put("level", map.get("leval"));
			treesMap.put("end", 1);
			treesMap.put("code", "");
			treesMap.put("unit", htmlMap.get("unit"));
			treesMap.put("frequency", frequency);
			// treesMap.put("update_time", new Date());
			// treesMap.put("start_time", new Date());
			treesMap.put("updatetask", 0);
			treesMap.put("cid", htmlMap.get("code"));
			treesMap.put("cpid", map.get("id"));
			String zbcode = (String) map.get("zbcode");
			// 先判断是否存在cid 存在则不变
			int returnId = new StatsTreeStorer().checkTreeExit(tableTreeName, id, name);
			if (!update) {

				if (returnId != 0) {
					returnId = 0;
					// zbcode = returnId + "";
				} else {
					returnId = new StatsTreeStorer().saveTrees(tableTreeName, treesMap);
					if (zbcode.isEmpty()) {
						zbcode = returnId + "";
					} else {
						zbcode = zbcode + "." + returnId;
					}
					new StatsTreeStorer().updateZbCode(tableTreeName, returnId, zbcode);
				}
			}

		}

	}

	public void parserDataNode(JSONArray dataNodeArray) {

		for (Object object : dataNodeArray) {
			JSONObject datanode = JSONObject.fromObject(object);
			String code = datanode.getString("code");
			String wdcode = StringUtils.substringBetween(code, "zb.", "_sj.");
			String sjcode = StringUtils.substringAfterLast(code, "_sj.");
			JSONObject dataInfo = datanode.getJSONObject("data");
			Double data = dataInfo.getDouble("data");
			int dotcount = dataInfo.getInt("dotcount");
			boolean hasdata = dataInfo.getBoolean("hasdata");
			String strdata = dataInfo.getString("strdata");
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
				int id = new StatsTreeStorer().checkTreeExit(tableTreeName, wdcode, "");
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("sub_id", id);
				resultMap.put("date_year", year);
				resultMap.put("date_month", month);
				resultMap.put("datavalue", strdata);
				resultMap.put("area_id", 1);
				// resultMap.put("industry_id", "");
				resultMap.put("charascope", "");
				resultMap.put("date_year_int", Integer.parseInt(year));
				resultMap.put("date_month_int", Integer.parseInt(month));
				resultMap.put("insert_time", new Date());
				boolean isExit = new StatsDataStorer().isDataExit(tableDataName, id, year, month, 1);
				if (isExit) {
					System.out.println(id + "  " + year + "  " + month + "已经存在");
					continue;
				} else {
					System.out.println(resultMap);
					new StatsDataStorer().saveDateModel(tableDataName, resultMap);
				}
				resultMap.clear();
				resultMap = null;

			}

		}

	}

	class DataFsThread implements Runnable {

		private LinkedBlockingQueue<Object> datas;

		public DataFsThread(LinkedBlockingQueue<Object> datas) {
			super();
			this.datas = datas;
		}

		public void run() {
			int breakNum = 0;
			while (true) {
				if (datas.size() > 0) {
					breakNum = 0;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					try {
						Map<String, Object> map = (Map<String, Object>) datas.take();
						String id = (String) map.get("id");
						String[] regWd = new String[] { "110000", "120000", "130000", "140000", "150000", "210000",
								"220000", "230000", "310000", "320000", "330000", "340000", "350000", "360000",
								"370000", "410000", "420000", "430000", "440000", "450000", "460000", "500000",
								"510000", "520000", "530000", "540000", "610000", "620000", "630000", "640000",
								"650000" };
						for (String reg : regWd) {
							System.out.println(reg);
							Map<String, String> resultparams = new HashMap<String, String>();
							resultparams.put("m", "QueryData");
							resultparams.put("dbcode", tablePre);
							resultparams.put("rowcode", "zb");
							resultparams.put("colcode", "sj");
							resultparams.put("wds", "[{\"wdcode\":\"reg\",\"valuecode\":\"" + reg + "\"}]");
							resultparams.put("k1", new Date().getTime() + "");
							resultparams.put("dfwds", "[{\"wdcode\":\"zb\",\"valuecode\":\"" + id + "\"}]");
							//
							if (update) {
								spiderNode(resultparams);
								resultparams.put("dfwds", "[{\"wdcode\":\"sj\",\"valuecode\":\"LAST36\"}]");
							}
							JSONObject jsonObject = getJsonObject(resultparams);
							getResult(jsonObject, map);
							resultparams.clear();
							resultparams = null;
						}
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					breakNum++;
					try {
						Thread.sleep(500 * breakNum);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("数据空的第" + breakNum + "次");
					if (breakNum > 20  ) {
						System.out.println("没有采集回数据跳出");
						break;

					}

				}
			}

		}

	}

	class DataThread implements Runnable {

		private LinkedBlockingQueue<Object> datas;

		public DataThread(LinkedBlockingQueue<Object> datas) {
			super();
			this.datas = datas;
		}

		public void run() {
			int breakNum = 0;
			while (true) {

				if (datas.size() > 0) {
					breakNum = 0;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Map<String, Object> map = (Map<String, Object>) datas.take();
						String id = (String) map.get("id");
						Map<String, String> resultparams = new HashMap<String, String>();
						resultparams.put("m", "QueryData");
						resultparams.put("dbcode", tablePre);
						resultparams.put("rowcode", "zb");
						resultparams.put("colcode", "sj");
						resultparams.put("wds", "[]");
						resultparams.put("k1", new Date().getTime() + "");
						resultparams.put("dfwds", "[{\"wdcode\":\"zb\",\"valuecode\":\"" + id + "\"}]");
						if (update) {
							spiderNode(resultparams);
							resultparams.put("dfwds", "[{\"wdcode\":\"sj\",\"valuecode\":\"LAST36\"}]");
						}
						JSONObject jsonObject = getJsonObject(resultparams);
						getResult(jsonObject, map);
						resultparams.clear();
						resultparams = null;
						map.clear();
						// Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					breakNum++;
					try {
						Thread.sleep(500 * breakNum);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("数据空的第" + breakNum + "次");
					if (breakNum > 20  ) {
						System.out.println("没有采集回数据跳出");
						break;

					}
				}
			}

		}
	}

	class TreeThread implements Runnable {

		private LinkedBlockingQueue<Object> trees;

		public TreeThread(LinkedBlockingQueue<Object> trees) {
			super();
			this.trees = trees;
		}

		public void run() {
			int breakNum = 0;
			while (true) {

				if (trees.size() > 0) {
					breakNum = 0;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					try {
						Map<String, Object> map = (Map<String, Object>) trees.take();
						boolean isp = true;
						Object isPObject = map.get("isParent");
						if (isPObject instanceof String) {
							String isParent = (String) map.get("isParent");
							if (isParent.equals("0")) {
								isp = false;
							} else {
								isp = true;
							}
						} else if (isPObject instanceof Boolean) {
							Boolean isParent = (Boolean) map.get("isParent");
							if (isParent) {
								isp = true;
							} else {
								isp = false;
							}

						}
						String id = (String) map.get("id");
						String name = (String) map.get("name");
						String pid = (String) map.get("pid");
						// boolean isParent = (Boolean) map.get("isParent");
						int zbpid = (Integer) map.get("zbPid");
						int leval = (Integer) map.get("leval");
						String zbcode = (String) map.get("zbcode");
						Map<String, Object> treesMap = new HashMap<String, Object>();
						// treesMap.put("id", "");
						treesMap.put("pid", zbpid);
						treesMap.put("value", name);
						treesMap.put("level", leval);
						treesMap.put("end", 0);
						treesMap.put("code", "");
						treesMap.put("unit", "");
						treesMap.put("frequency", frequency);
						// treesMap.put("update_time", new Date());
						// treesMap.put("start_time", new Date());
						treesMap.put("updatetask", 0);
						treesMap.put("cid", id);
						treesMap.put("cpid", pid);
						if (!id.contains(pid)) {
							System.out.println("采集官网返回数据异常");
							System.out.println(id);
							System.out.println(pid);
							System.exit(0);
						}
						// 先判断是否存在cid 存在则不变
						int returnId = new StatsTreeStorer().checkTreeExit(tableTreeName, id, name);
						if (!update) {
							if (returnId != 0) {
								zbcode = returnId + "";
							} else {
								returnId = new StatsTreeStorer().saveTrees(tableTreeName, treesMap);
								if (zbcode.isEmpty()) {
									zbcode = returnId + "";
								} else {
									zbcode = zbcode + "." + returnId;
								}
								new StatsTreeStorer().updateZbCode(tableTreeName, returnId, zbcode);
							}
						}
						// {id=A0H, zbcode=17, wdcode=zb, dbcode=hgnd, leval=1,
						// name=社会消费品零售总额, isParent=false, zbPid=0, pid=}
						map.put("zbcode", zbcode);
						map.put("leval", leval + 1);
						map.put("zbpid", returnId);
						if (isp) {
							// 获取下一级
							Map<String, String> params = new HashMap<String, String>();
							params.put("dbcode", tablePre);
							params.put("wdcode", "zb");
							params.put("m", "getTree");
							params.put("id", id);
							getTree(params, returnId, leval + 1, zbcode);
							params.clear();
							params = null;
						} else {
							datas.put(map);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} else {
					breakNum++;
					try {
						Thread.sleep(500 * breakNum);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("数据空的第" + breakNum + "次");
					if (breakNum > 20  ) {
						System.out.println("没有采集回数据跳出");
						break;

					}
				}
			}

		}
	}

}
