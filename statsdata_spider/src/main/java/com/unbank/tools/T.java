package com.unbank.tools;

import java.util.HashMap;

import com.unbank.fetch.Fetcher;

public class T {

	public static void main(String[] args) {
//		String url = "http://www.cninfo.com.cn/cninfo-new/disclosure/szse";
//		String html = Fetcher.getInstance().get(url, "utf-8");
		// System.out.println(html);
//		for (int i = 2; i < 4; i++) {

			String postUrl = "http://www.cninfo.com.cn/cninfo-new/announcement/query";
			HashMap<String, String> params = new HashMap<String, String>();
			// params.put("stock", null);
			// params.put("searchkey", null);
			// params.put("plate", null);
			// params.put("category", null);
			// params.put("trade", "szse");
//			params.put("columnTitle", "历史公告查询");
//			params.put("column", "szse");
//			params.put("pageNum", "" + i);
//			params.put("pageSize", "30");
			// params.put("tabName", null);
			// params.put("sortType", null);
			// params.put("limit", null);
			// params.put("showTitle", null);
//			params.put("seDate", "2015-11-08+~+2016-11-08");
			System.out.println(Fetcher.getInstance().post(postUrl, params,
					null, "utf-8"));
//		}

	}
}
