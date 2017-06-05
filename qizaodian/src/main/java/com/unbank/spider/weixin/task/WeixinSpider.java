/**
 * 
 */
package com.unbank.spider.weixin.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.spider.mybatis.vo.Weixin;
import com.unbank.spider.tool.DateTool;
import com.unbank.spider.weixin.fetcher.HttpClientBuilder;
import com.unbank.spider.weixin.fetcher.WeixinFetcher;
import com.unbank.spider.weixin.login.Token4Sogou;

/**
 * 
 * @ClassName: WeixinSpider
 * @Description: 搜狗微信采集系统
 * @author: Administrator
 * @version: V1.0
 * @date: 2016-11-24
 */
public class WeixinSpider {
	private static CloseableHttpClient httpClient = null;
	private static WeixinFetcher fetcher = new WeixinFetcher();
	private  String keyword;
	private List<Weixin> weixins;

	/**
	 * @param keyword
	 * @param weixins
	 */
	public WeixinSpider(String keyword) {
		super();
		this.keyword = keyword;
		weixins = new ArrayList<Weixin>();
	}

	// public static void main(String[] args) {
	// new WeixinSpider().Spider();
	// }

	/**
	 * @description: 采集程序入口 @param: @return: void @throws
	 */
	public List<Weixin> Spider() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false, poolingHttpClientConnectionManager,
				fetcher.cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		saveWeixin();
		return weixins;
	}

	/**
	 * @description: TODO(这里用一句话描述这个方法的作用) @param: @return: void @throws
	 */
	private void saveWeixin() {
		boolean isLogin = login();
		if (isLogin) {
			spiderList(11);
		}

	}

	/**
	 * @description: TODO(这里用一句话描述这个方法的作用) @param: @return @return:
	 * boolean @throws
	 */
	private boolean login() {
		int loginNum = 0;
		boolean isLogin = false;
		while (true) {
			String user = new ReaderLine().ReaderUserByFile();
			String name = user.split(",")[0];
			String pwd = user.split(",")[1];
			if (login(name, pwd)) {
				System.out.println("登录成功,欢迎您:" + name + "!");
				isLogin = true;
				break;
			}
			loginNum++;
			if (loginNum >= 10) {
				break;
			}
		}
		return isLogin;
	}

	/**
	 * @description: 控制层 @param: @param i @return: void @throws
	 */
	private void spiderList(int i) {
		/** 标记位 **/
		int num = 0;
		// 控制采集的页数 可以修改
		boolean iskd = false;
		for (; i <= 12; i++) {
			if (!spiderDetail(i)) {
				num = i;
				iskd = true;
				break;
			}
		}
		if (iskd) {
			boolean isLogin = login();
			if (isLogin) {
				spiderList(num);
			}
		}

	}

	/**
	 * @description: 保存详情 @param: @param i @param: @return @return:
	 * boolean @throws
	 */
	public boolean spiderDetail(int i) {
		System.out.println("现在采集到第" + i + "页,请耐心等待！");
		String url = "http://weixin.sogou.com/weixin?query=" + keyword
				+ "&_sug_type_=&sut=769&lkt=1%2C1479803756148%2C1479803756148&_sug_=y&type=2&sst0=1479803756249&page="
				+ i + "&ie=utf8&w=01019900&dr=1";
		String sougouWeixinhtml = fetcher.getHtml(httpClient, url, fetcher.getCookiesString());
		Document document = Jsoup.parse(sougouWeixinhtml);
		Elements resultsElements = document.select("div.news-box");
		if (resultsElements.size() == 0) {
			return false;
		} else {
			Elements aElements = resultsElements.first().select("ul.news-list>li");
			for (Element element : aElements) {
				List<Weixin> lists = getDetailByElement(keyword, element);
				weixins.addAll(lists);
			}
		}
		return true;

	}

	/**
	 * @description: TODO(这里用一句话描述这个方法的作用) @param: @param keyword2 @return:
	 * void @throws
	 */
	private List<Weixin> getDetailByElement(String keyword2, Element element) {
		String title = null;
		String titleurl = null;
		String brief = null;
		String author = null;
		String authorurl = null;
		String date = null;
		List<Weixin> lists = new ArrayList<Weixin>();
		Weixin weixin = null;
		weixin = new Weixin();
		Elements titleEl = element.select("div.txt-box>h3>a");
		Elements briefEl = element.select("div.txt-box>p.txt-info");
		Elements authorEl = element.select("div.txt-box>div.s-p>a.account");
		Elements dateEl = element.select("div.txt-box > div > span.s2");

		if (titleEl.size() > 0) {
			title = titleEl.text().trim();
			titleurl = titleEl.attr("href").trim();
		}
		if (briefEl.size() > 0) {
			brief = briefEl.text().trim();
		}

		if (authorEl.size() > 0) {
			author = authorEl.text().trim();
			authorurl = authorEl.attr("href");
		}
		if (dateEl.toString().contains("timeConvert")) {
			date = dateEl.toString().split("timeConvert")[1].toString().trim();
			date = date.replaceAll("[^0-9]", "").trim();
			date = new DateTool().getDateByLong(date);
		}
		weixin.setTitle(title);
		weixin.setBrief(brief);
		weixin.setDate(date);
		weixin.setAuthor(author);
		weixin.setAuthorurl(authorurl);
		weixin.setTitleurl(titleurl);
		weixin.setAuthor(author);
		lists.add(weixin);

		return lists;
	}

	/**
	 * @description: 模拟浏览器登录搜狗微信 @param: @param username @param: @param
	 * password @param: @return @return: boolean @throws
	 */
	public boolean login(String name, String pwd) {
		String token = null;
		String firstUrl = "https://account.sogou.com/web/webLogin";
		String loginUrl = "https://account.sogou.com/web/login";
		String cookieUrl = "https://pb.sogou.com/pv.gif?uigs_productid=ufo&ufoid=passport&rdk=1479803139020&img=pv.gif&b=ff&v=49&o=win6.1&s=1920x1080&l=zh-CN&bi=64&ls=1_1&refer=&page=搜狗通行证&pageUrl=https://account.sogou.com/web/webLogin&productid=passport&ptype=web&pcode=index";
		String callUrl = "https://account.sogou.com/static/api/jump.htm?status=0&needcaptcha=0&msg=";
		String ssologinUrl = "https://account.sogou.com/";
		HashMap<String, String> params = null;
		params = new HashMap<String, String>();
		params.put("username", name);
		params.put("password", pwd);
		params.put("captcha", "");
		params.put("autoLogin", "0");
		params.put("client_id", "1120");
		params.put("xd", "https://account.sogou.com/static/api/jump.htm");
		params.put("token", token);
		try {
			fetcher.cookieStore.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fetcher.getHtml(httpClient, firstUrl, "");
		fetcher.getHtml(httpClient, cookieUrl, "");
		long dateTime = new Date().getTime();
		String checkUrl = "https://account.sogou.com/web/login/checkNeedCaptcha?username=" + name + "&client_id=1120&t="
				+ dateTime;
		fetcher.getHtml(httpClient, checkUrl, fetcher.getCookiesString());
		token = new Token4Sogou().getToken();
		fetcher.post(httpClient, loginUrl, params, "utf-8", fetcher.getCookiesString());
		// 校验
		fetcher.getHtml(httpClient, callUrl, fetcher.getCookiesString());
		String result = fetcher.getHtml(httpClient, ssologinUrl, fetcher.getCookiesString());
		if (result.contains("我的帐号")) {
			System.out.println("登录成功！");
			return true;
		}
		System.out.println("您已连续登录十次失败，请检查您的程序！");
		return false;

	}

	/**
	 * @description: 休眠 @param: @return: void @throws
	 */
	/*
	 * private void sleep() { try { Random rand = new Random(); int randNum =
	 * rand.nextInt(1) + 2; Thread.sleep(500 * randNum); } catch
	 * (InterruptedException e) { e.printStackTrace(); } }
	 */

}
