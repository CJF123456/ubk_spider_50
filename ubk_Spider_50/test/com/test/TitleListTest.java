package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unbank.robotspider.action.model.normal.ContentBySQLFilter;
import com.unbank.robotspider.action.model.normal.Filter;
import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.fetch.JsoupNetFetcher;
import com.unbank.robotspider.filter.titlelist.TitleListFilter;
import com.unbank.robotspider.filter.titlelist.TitleListFilterLocator;
import com.unbank.robotspider.parser.NewsDatePaser;
import com.unbank.robotspider.tools.DateTools;

public class TitleListTest {

	public static void main(String[] args) {

//		new ClassPathXmlApplicationContext(
//				new String[] { "applicationContext.xml" });
		String url = "http://www.chinaisa.org.cn/gxportal/DispatchAction.do?efFormEname=ECTM40&key=A2BaZVkyB2YEZQM0VjFRMFczB2cFYVZgAjUCNAdhBDZXRA1CDBcAMAobAUYFEg1v";
		Document document = new JsoupNetFetcher().fetchText(url);
//		System.out.println(document);
		Element element = document.select(".Section1").first();
//		Elements elements = element.children();
//		for (Element string : elements) {
//			System.out.println(string.);
//			System.out.println("===========");
//		}
		System.out.println(element.text());
//		NewsInfoMiddleWare newsInfoMiddleWare = new NewsInfoMiddleWare();
//		newsInfoMiddleWare.setWebsiteId(11224);
//		Filter filter = new ContentBySQLFilter(newsInfoMiddleWare );
//		String content = filter.getContent(url, document, "");
//		System.out.println(content);

	}
}
