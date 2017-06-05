package com.unbank.spider.news.task;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unbank.spider.mybatis.client.ShowPoetryMapper;
import com.unbank.spider.mybatis.client.ShowPoetryTextMapper;
import com.unbank.spider.mybatis.vo.ShowPoetry;
import com.unbank.spider.mybatis.vo.ShowPoetryText;
import com.unbank.spider.news.fetcher.BaseFilter;
import com.unbank.spider.news.fetcher.HttpClientBuilder;
import com.unbank.spider.news.fetcher.NewsFetcher;
import com.unbank.spider.news.fetcher.URLBaseFilter;
import com.unbank.spider.news.fetcher.URLFilter;
import com.unbank.spider.tool.DateTool;
import com.unbank.spider.tool.MD5;


@Component
public class NewsSpider {

	@Autowired
	ShowPoetryMapper showPoetryMapper;
	@Autowired
	ShowPoetryTextMapper showPoetryTextMapper;

	public static void main(String[] args) {
		new NewsSpider().spider();
	}

	private static URLFilter urlFilter = new URLBaseFilter();
	private String first_url = "http://cul.qq.com/shuzhai.htm";
	private static CloseableHttpClient httpClient = null;
	private static NewsFetcher fetcher = new NewsFetcher();

	public void spider() {
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		HttpClientBuilder httpClientBuilder = new HttpClientBuilder(false, poolingHttpClientConnectionManager,
				fetcher.cookieStore);
		httpClient = httpClientBuilder.getHttpClient();
		String html = fetcher.getHtml(httpClient, first_url, fetcher.getCookiesString());
		getListUrlByHtml(html, first_url);
	}

	private void getListUrlByHtml(String html, String first_url) {
		Document document = Jsoup.parse(html, first_url);
		Elements elements = document.select("#szlb > div.bd > ul.listUl");
		if (elements.size() > 0) {
			Elements eles = elements.select("#listZone1 > li > h3 > a");
			for (Element element : eles) {
				String url = "http://cul.qq.com" + element.attr("href");
				String title = element.text().trim();
				saveDataByUrl(url, title);
			}
		}
	}

	private void saveDataByUrl(String url, String title) {
		String html = fetcher.getHtml(httpClient, url, fetcher.getCookiesString());
		Document document = Jsoup.parse(html, url);
		Elements elements = document.select("div.qq_article");
		if (elements.size() > 0) {
			String source = document.select("div.hd > div > div.a_Info > span.a_source > a").text().trim();
			String newstime = document.select("div.hd > div > div.a_Info > span.a_time").text().trim();
			String author = document.select("div.hd > div > div.a_Info > span.a_author").text().trim();
			String brief = document.select("#Cnt-Main-Article-QQ > p.titdd-Article").text().trim();
			Element content = document.select("#Cnt-Main-Article-QQ").first();
			content.select("p.titdd-Article").remove();
			Elements srm = content.select(
					"strong[style=FONT-SIZE: 16px; FONT-FAMILY: 'Microsoft Yahei', Helvetica, sans-serif; WHITE-SPACE: normal; TEXT-TRANSFORM: none; WORD-SPACING: 0px; COLOR: rgb(0,0,0); FONT-STYLE: normal; ORPHANS: 2; WIDOWS: 2; LETTER-SPACING: normal; BACKGROUND-COLOR: rgb(255,255,255); TEXT-INDENT: 32px; font-variant-ligatures: normal; font-variant-caps: normal; -webkit-text-stroke-width: 0px]");
			srm.remove();
			Elements srms=content.select("#Cnt-Main-Article-QQ > p > strong");
			srms.remove();
			new BaseFilter().formatElements(content);
			String cont = content.toString();
			String detailurl = MD5.GetMD5Code(url);
			if (urlFilter.checkNewsURL(detailurl)) {
				try {
					ShowPoetry showPoetry = new ShowPoetry();
					showPoetry.setTitle(title);
					showPoetry.setNewstime(DateTool.getStringToDate(newstime));
					showPoetry.setAuthor(author);
					showPoetry.setBrief(brief);
					showPoetry.setSource(source);
					showPoetry.setDetailurl(detailurl);
					showPoetry.setRe(url);
					showPoetryMapper.insertSelective(showPoetry);
					int id = showPoetry.getId();
					ShowPoetryText newsText = new ShowPoetryText();
					newsText.setId(id);
					newsText.setText(cont);
					showPoetryTextMapper.insertSelective(newsText);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
