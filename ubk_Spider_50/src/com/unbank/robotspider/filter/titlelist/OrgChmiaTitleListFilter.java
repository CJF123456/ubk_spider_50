package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class OrgChmiaTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.chmia.org";

	public OrgChmiaTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Element element= document.getElementById("GridView1");
		if(element!=null){
			return element.select("a");
		}
		return null;
	}

}
