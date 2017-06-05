package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnGovHljTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.hlj.gov.cn";

	public CnGovHljTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Element element= document.getElementById("content_right");
		element.select("div.blue12").remove();
		
		return element.select("a");
	}

}
