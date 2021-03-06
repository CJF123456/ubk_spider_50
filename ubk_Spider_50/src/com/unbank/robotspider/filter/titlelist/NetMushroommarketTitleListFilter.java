package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NetMushroommarketTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "zixun.mushroommarket.net" };

	public NetMushroommarketTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.con1 > ul > li> a");
	}

}
