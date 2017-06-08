/**
 * 
 */
package com.unbank.spider.weixin.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.unbank.spider.mybatis.vo.WinxinNumber;
import com.unbank.spider.tool.DateTool;
import com.unbank.spider.weixin.fetcher.HttpClientBuilder;
import com.unbank.spider.weixin.fetcher.WeixinFetcher;
import com.unbank.spider.weixin.login.Token4Sogou;



/**
 * 
 * @ClassName: NumberSpider
 * @Description: 搜狗微信公众号
 * @author: Administrator
 * @version: V1.0
 * @date: 2016-11-24
 */
public class NumberSpider {
	private static CloseableHttpClient httpClient = null;
	private static WeixinFetcher fetcher = new WeixinFetcher();
	private String keyword;
	private List<WinxinNumber> winxinNumbers;

	/**
	 * 
	 * @param keyword
	 */
	public NumberSpider(String keyword) {
		super();
		this.keyword = keyword;
		winxinNumbers = new ArrayList<WinxinNumber>();
	}

	/**
	 * @Title: Spider 程序主入口 @Description: 采集微信公众号 @param @return @return List
	 *         <WinxinNumber> 返回类型 @throws
	 */
	public List<WinxinNumber> Spider() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false, poolingHttpClientConnectionManager,
				fetcher.cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		saveWeixin();
		return winxinNumbers;
	}

	private void saveWeixin() {
		boolean isLogin = login();
		if (isLogin) {
			spiderList(1);
		}

	}

	/**
	 * @description: TODO(这里用一句话描述这个方法的作用) @param: @return @return:
	 *               boolean @throws
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
		for (; i <= 2; i++) {
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
	 *               boolean @throws
	 */
	public boolean spiderDetail(int i) {
		System.out.println("现在采集到第" + i + "页,请耐心等待！");
		String url = "http://weixin.sogou.com/weixin?query=" + keyword + "&_sug_type_=&_sug_=n&type=1&page=" + i
				+ "&ie=utf8";
		String sougouWeixinhtml = fetcher.getHtml(httpClient, url, fetcher.getCookiesString());
		Document document = Jsoup.parse(sougouWeixinhtml);
		Elements resultsElements = document.select("div.news-box");
		if (resultsElements.size() == 0) {
			return false;
		} else {
			Elements aElements = resultsElements.first().select("ul.news-list2 >li");
			for (Element element : aElements) {
				List<WinxinNumber> lists = getDetailByElement(keyword, element);
				winxinNumbers.addAll(lists);
			}
		}
		return true;

	}

	/**
	 * @description: TODO(这里用一句话描述这个方法的作用) @param: @param keyword2 @return:
	 *               void @throws
	 */
	private List<WinxinNumber> getDetailByElement(String keyword2, Element element) {
		// name
		String numname = null;
		String numnameurl = null;
		// 公众号
		String numid = null;
		// 发文数
		// String numdocamount = null;
		// 月阅读数
		// String numread = null;
		// 功能介绍
		String numshow = null;
		// 认证
		String numauthentic = null;
		String date = null;
		// 最近文章
		String numdoc = null;
		String numdocurl = null;
		List<WinxinNumber> lists = new ArrayList<WinxinNumber>();
		WinxinNumber winxinNumbers = null;
		winxinNumbers = new WinxinNumber();
		Elements numnameEl = element.select("div>div.txt-box>p.tit>a");
		Elements numidEl = element.select("div>div.txt-box>p.info>label[name=em_weixinhao]");

		Elements numshowEl = element.select("dl:nth-child(2) > dd");
		Elements numauthenticEl = element.select("dl:nth-child(3) > dd");
		Elements numauthenticElDt = element.select("dl:nth-child(3) > dt");
		Elements numdocEl = element.select("dl:nth-child(4) > dd>a");
		Elements dateEl = element.select("dl:nth-child(4) > dd>span");
		if (numauthenticElDt.toString().contains("认证：")) {
			numauthentic = numauthenticEl.text().trim();
		} else {
			numauthentic = null;
		}
		if (numnameEl.size() > 0) {
			numname = numnameEl.text().trim();
			numnameurl = numnameEl.attr("href").trim();
		}
		if (numidEl.size() > 0) {
			numid = numidEl.text().trim();
		}
		if (numshowEl.size() > 0) {
			numshow = numshowEl.text().trim();
		}
		if (numdocEl.size() > 0) {
			numdoc = numdocEl.text().trim();
			numdocurl = numdocEl.attr("href").trim();
		}
		if (dateEl.toString().contains("timeConvert")) {
			date = dateEl.toString().split("timeConvert")[1].toString().trim();
			date = date.replaceAll("[^0-9]", "").trim();
			date = new DateTool().getDateByLong(date);
		}
		winxinNumbers.setNumname(numname);
		winxinNumbers.setNumnameurl(numnameurl);
		winxinNumbers.setNumid(numid);
		winxinNumbers.setNumshow(numshow);
		winxinNumbers.setNumauthentic(numauthentic);
		winxinNumbers.setNumdoc(numdoc);
		winxinNumbers.setNumdocurl(numdocurl);
		winxinNumbers.setDate(date);
		lists.add(winxinNumbers);
		return lists;
	}

	/**
	 * @description: 模拟浏览器登录搜狗微信 @param: @param username @param: @param
	 *               password @param: @return @return: boolean @throws
	 */
	public boolean login(String name, String pwd) {
		String token = null;
		String cookieUrl = "https://pb.sogou.com/pv.gif?uigs_productid=ufo&ufoid=passport&rdk=1479803139020&img=pv.gif&b=ff&v=49&o=win6.1&s=1920x1080&l=zh-CN&bi=64&ls=1_1&refer=&page=搜狗通行证&pageUrl=https://account.sogou.com/web/webLogin&productid=passport&ptype=web&pcode=index";
		String callUrl = "https://account.sogou.com/static/api/jump.htm?status=0&needcaptcha=0&msg=";
		String ssologinUrl = "https://account.sogou.com/";
		String firstUrl = "https://account.sogou.com/web/webLogin";
		String loginUrl = "https://account.sogou.com/web/login";
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
