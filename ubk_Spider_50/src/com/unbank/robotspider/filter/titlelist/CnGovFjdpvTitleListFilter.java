package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnGovFjdpvTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.fjdpc.gov.cn";

	public CnGovFjdpvTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		//body > center > div > div.boxMain > div > div.midBox.clearfix > div.outFix.oh > div.rightBox.fl.oh.clearfix > div.commList > ul > li:nth-child(1) > a
		return document.select("div.commList > ul > li > a");
	}

}
