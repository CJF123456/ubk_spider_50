package com.unbank.quartz.task;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.unbank.dao.WebBeanStore;
import com.unbank.fetch.HttpClientBuilder;

/***
 * 每月执行一次
 * 
 * 时间个Cookie
 * 	public static String startBijiaoTime = "2016/12/01";
	public static String endBijiaoTime = "2016/12/31";
 *
 *
 *headerCookie   先手动登陆上拟在建， 复制登陆后的Cookie 黏贴上即可
 *
 *采完后运行bhipppwordmaker  生成 bhi_ppp_word
 */
public class BhiPPPSpider {

	private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000)
			.setStaleConnectionCheckEnabled(true).setCircularRedirectsAllowed(true)
			// .setProxy(proxy)
			.setMaxRedirects(50).build();

	private static BasicCookieStore cookieStore = new BasicCookieStore();

	private static String headerCookie = "";
	public static String startBijiaoTime = "2016/12/01";
	public static String endBijiaoTime = "2016/12/31";

	public static void main(String[] args) {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false, poolingHttpClientConnectionManager,
				cookieStore);
		final CloseableHttpClient httpClient = httpClientBuilder.getHttpClient();

		headerCookie = "ASPSESSIONIDQAQBCTCS=GHBKAGAADNPBBPIGGNLHIHHB; ASP.NET_SessionId=topbdd45wls5wxm4psnqap55; CheckCode=d03e; LogUser=fDFoEC8Afd2vgzcpWs5hf3R4ozBU1qsh+JV2GYBlTl86h+X/dHjRnqDGMrUEfU58; Hm_lvt_8d994d177d2158b74a6011c3839d1a20=1477567940,1477653676,1477912161,1477999606; Hm_lpvt_8d994d177d2158b74a6011c3839d1a20=1478000073; BHI_BROWSE_STATICS_WIDTH=1366; BHI_BROWSE_STATICS_HEIGHT=768";
		// "PageIndex":1,"PageSize":10,"UserLevel":1,"RecordCount":47
		// 江苏, 河南

		String provices[] = new String[] { "北京", "天津", "河北", "山西", "内蒙古", // bei
				"上海", "浙江", "江苏", "安徽", "福建", "江西", "山东", // d
				"湖北", "湖南", "河南", //
				"广东", "广西", "海南",
				//
				"黑龙江", "吉林", "辽宁",
				//
				"新疆", "青海", "甘肃", "宁夏", "陕西",
				//
				"四川", "云南", "贵州", "重庆", "西藏" };

		for (final String provice : provices) {
			new Thread(new Runnable() {
				public void run() {
					String industrys[] = new String[] { "%E7%8E%AF%E4%BF%9D", "%E4%BA%A4%E9%80%9A",
							"%E5%B8%82%E6%94%BF", "%E7%A4%BE%E4%BC%9A%E4%BA%8B%E4%B8%9A", "%E8%83%BD%E6%BA%90",
							"%E6%B0%B4%E5%88%A9", "%E5%8C%96%E5%B7%A5", "%E5%85%B6%E4%BB%96" };
					for (String industry : industrys) {
						int pageSize = 10;
						for (int i = 1; i <= pageSize; i++) {
							try {
								String url = "http://www.bhi.com.cn/project/Project.ashx?PageIndex=" + i
										+ "&PageSize=10&Type=1&Province=" + URLEncoder.encode(provice, "utf-8")
										+ "&Industry=" + industry
										+ "&ProjectType=&GreaterArea=&isProgress=0&isLinkMan=0";
								pageSize = getInfo(httpClient, url, provice);
							} catch (Exception e) {
								e.printStackTrace();
								continue;
							}
						}
					}

				}
			}).start();

		}

	}

	private static int getInfo(CloseableHttpClient httpClient, String url, String provice) {
		int num =0;
		try{
		String html = getHtml(httpClient, url, "utf-8", headerCookie);
		JSONObject bodyObject = JSONObject.fromObject(html);
		if (!bodyObject.containsKey("list")) {
			return 0;
		}
		if (bodyObject.get("list").equals("")) {
			return 0;
		}

		JSONArray list = bodyObject.getJSONArray("list");
		int pageSize = bodyObject.getInt("PageSize");
		int recordCount = bodyObject.getInt("RecordCount");
		 num = recordCount / pageSize + 1;

		int size = 0;

		for (Object object : list) {
			JSONObject proObject = JSONObject.fromObject(object);

			Map<String, Object> project = (Map<String, Object>) JSONObject.toBean(proObject, Map.class);
			if (project.size() == 0) {
				continue;
			}
			int id = (Integer) project.get("Exportid");
			String createTime = (String) project.get("CreateTime");

			long createDate = Date.parse(createTime);

			long startbijiaoDate = Date.parse(startBijiaoTime);

			long endBijiaoDate = Date.parse(endBijiaoTime);
			if (startbijiaoDate > createDate) {
				continue;
			}
			if (createDate > endBijiaoDate) {
				continue;
			}
			String detailurl = "http://www.bhi.com.cn/project/ProjectDetails.ashx?Exportid=" + id;
			String detailHtml = getHtml(httpClient, detailurl, "utf-8", headerCookie);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info", proObject.toString());
			map.put("description", detailHtml);
			map.put("provice", provice);
			saveProject(map);
			size++;
		}
		if (size == 0) {
			return 0;
		}
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return num;
	}

	private static void saveProject(Map<String, Object> project) {
		new WebBeanStore().executeMapSQL("insert into  bhi_ppp ", project);

	}

	public static String getHtml(CloseableHttpClient httpClient, String url, String charset, String cookie) {
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		String useCharset = charset;
		HttpGet httpGet = new HttpGet(url);
		fillGetHeader(url, httpGet, cookie);
		httpGet.setConfig(requestConfig);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet, context);
			try {
				HttpEntity entity = response.getEntity();
				return EntityUtils.toString(entity, useCharset);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	private static void fillGetHeader(String url, HttpGet httpGet, String cookie) {

		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.2; rv:23.0) Gecko/20100101 Firefox/23.0");
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-us;q=0.8,en;q=0.6");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate,sdch");
		httpGet.setHeader("Host", getDomain(url));
		httpGet.setHeader("DHT", "1");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Referer", "http://www.bhi.com.cn");
		httpGet.setHeader("Cache-Control", "private");
		httpGet.setHeader("Cookie", cookie);
	}

	private static String getDomain(String url) {
		String domain = "";
		try {
			URL u = new URL(url);
			domain = u.getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return domain;
	}

}
