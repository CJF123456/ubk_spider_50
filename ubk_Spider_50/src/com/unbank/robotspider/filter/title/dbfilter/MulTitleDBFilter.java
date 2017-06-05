package com.unbank.robotspider.filter.title.dbfilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.filter.title.TitleBaseFilter;

public class MulTitleDBFilter extends TitleBaseFilter {

	private List<WebsiteParser> list = new ArrayList<WebsiteParser>();

	public MulTitleDBFilter(List<WebsiteParser> list) {
		this.list = list;
	}

	@Override
	public String getTitle(Document document) {
		for (WebsiteParser parser : list) {
			String titlePath = parser.getNewstitlePath();
			System.out.println("titlePath:"+titlePath);
			if (!StringUtils.isBlank(titlePath)) {
				Elements titleElements = document.select(titlePath);
				if (titleElements != null && titleElements.size() > 0) {
					Element titleElement = titleElements.first();
					return titleElement.text();
				}
			}
		}
		return super.getTitle(document);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		for (WebsiteParser parser : list) {
			String titlePath = parser.getNewstitlePath();
			System.out.println("titlePath-:"+titlePath);
			if (!StringUtils.isBlank(titlePath)) {
				Elements titleElements = document.select(titlePath);
				if (titleElements != null && titleElements.size() > 0) {
					Element titleElement = titleElements.first();
					return titleElement.text();
				}
			}
		}
		return super.getTitle(document, Alternativetitle);
	}
}
