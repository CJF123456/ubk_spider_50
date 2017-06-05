package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CnGovHljTitleFilter extends TitleBaseFilter {

	private String domain1 = "www.hlj.gov.cn";

	public CnGovHljTitleFilter() {
		TitleFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("td.title").first().text();
	}

}
