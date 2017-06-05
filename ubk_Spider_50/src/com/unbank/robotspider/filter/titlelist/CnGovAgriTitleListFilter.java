package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnGovAgriTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.agri.gov.cn";

	public CnGovAgriTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("a.link03");
	}

}
