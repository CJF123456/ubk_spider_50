package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class CnGovAgriTitleFilter extends TitleBaseFilter {

	private String domain1 = "www.agri.gov.cn";

	public CnGovAgriTitleFilter() {
		TitleFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("td.hui_15_cu").first().text();
	}

}
