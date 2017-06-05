package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.UnbankQuartzByTimeCrawler;
import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.store.WebsiteParserReader;

public class TitleBySqlFilter implements TitleFilter {
	private NewsInfoMiddleWare articleInfo;

	public TitleBySqlFilter(NewsInfoMiddleWare articleInfo) {
		this.articleInfo = articleInfo;
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		// int websiteid = articleInfo.getWebsiteId();
		WebsiteParser websiteParser = UnbankQuartzByTimeCrawler.websitepaserlist
				.get(articleInfo.getWebsiteId());
		// = new WebsiteParserReader()
		// .readWebsitePaserById(websiteid);
		String titlePath = websiteParser.getNewstitlePath();
		Element element = document.select(titlePath.trim()).first();
		String needlessPath = websiteParser.getNewstitleNeedlesselementsPath();
		if (needlessPath != null && (!needlessPath.isEmpty())) {
			Elements elements = element.select(needlessPath.trim());
			for (Element element2 : elements) {
				element2.remove();
			}
		}
		return element.text();
	}

	@Override
	public String getTitle(Document document) {
		return null;
	}

}
