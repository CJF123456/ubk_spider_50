package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class NetNj110TitleFilter extends TitleBaseFilter {

	private String domain1 = "kh.nj110.net";

	public NetNj110TitleFilter() {
		TitleFilterLocator.getInstance().register(domain1, this);
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("h1").first().text();
	}

}
