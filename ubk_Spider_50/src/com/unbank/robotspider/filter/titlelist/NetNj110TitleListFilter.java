package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NetNj110TitleListFilter extends BaseTitleListFilter {
	private String domain = "kh.nj110.net";

	public NetNj110TitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("tr#PAGE_PLAY>td.news_Content>a");
	}

}
