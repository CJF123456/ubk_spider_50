package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CnGovFjdpcTitleFilter extends TitleBaseFilter {

	private String domain1 = "www.fjdpc.gov.cn";

	public CnGovFjdpcTitleFilter() {
		TitleFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("div.showTitle").first().text();
	}

}
