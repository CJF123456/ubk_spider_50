package com.unbank.robotspider.parser;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import org.jsoup.nodes.Document;

import com.unbank.robotspider.action.model.normal.ContentBySQLFilter;
import com.unbank.robotspider.action.model.normal.Filter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.util.Values;

public class ContentPaser extends BasePaser {

	public String doParse(NewsInfoMiddleWare newsInfoMiddleWare,
			Document document) {
		String title = newsInfoMiddleWare.getCrawlTitle();
		String url = newsInfoMiddleWare.getUrl();
		String content = null;
		if (document == null) {
			return null;
		}
		if (newsInfoMiddleWare.getWebsiteId() > Values.v.WEBSITEID) {
			try {
				Filter filter = new ContentBySQLFilter(newsInfoMiddleWare);
				content = filter.getContent(url, document, title);
			} catch (Exception e) {
				logger.info("从数据库获取内容失败   " + newsInfoMiddleWare.getUrl()
						+ "    " + newsInfoMiddleWare.getWebsiteId());
				return null;
			}
		} else {
			Filter filter = FilterLocator.getInstance().getFilter(url);
			content = filter.getContent(url, document, title);
		}
		if (content == null) {
			return null;
		}
		content = removeSingleQuotes(content);
		return content;
	}

	public String extractNewsContent(NewsInfoMiddleWare newsInfoMiddleWare,
			Document document) {
		String url = newsInfoMiddleWare.getUrl();
		NextPageUrlPaser npuf = new NextPageUrlPaser(document, url);
		if (npuf.hasNextPage()) {
			StringBuffer sb = new StringBuffer();
			String content = doParse(newsInfoMiddleWare, document);
			if (content == null) {
				return null;
			}
			sb.append(content);
			TreeMap<Integer, String> urls = npuf.getNextPageUrlMap();
			Set<Integer> set = urls.keySet();
			Iterator<Integer> iterator = set.iterator();
			while (iterator.hasNext()) {
				Integer key = iterator.next();
				String u = urls.get(key);
				Document doc = getDocumentByURL(u);
				if (doc == null) {
					return null;
				} else {
					String content2 = doParse(newsInfoMiddleWare, doc);
					if (content2 == null) {
						return null;
					}
					sb.append(content2);
				}
			}
			return sb.toString();
		} else {
			return doParse(newsInfoMiddleWare, document);
		}
	}

}
