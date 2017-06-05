package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class OrgChmiaTitleFilter extends TitleBaseFilter {

	private String domain1 = "www.chmia.org";

	public OrgChmiaTitleFilter() {
		TitleFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("font>b").first().text();
	}

}
