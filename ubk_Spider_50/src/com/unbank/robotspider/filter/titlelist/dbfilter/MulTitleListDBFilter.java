package com.unbank.robotspider.filter.titlelist.dbfilter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.filter.titlelist.BaseTitleListFilter;

public class MulTitleListDBFilter extends BaseTitleListFilter {

	private List<WebsiteParser> list = new ArrayList<WebsiteParser>();

	public MulTitleListDBFilter(List<WebsiteParser> list) {
		this.list = list;
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		for (WebsiteParser parser : list) {
			String listNeedlessPath = parser.getListNeedlesselementsPath();

			if (!StringUtils.isBlank(listNeedlessPath)) {
				String[] paths = listNeedlessPath.split("\\|\\|");
				for (String path : paths) {
					removeNoNeedElement(document, path);
				}
			}
		}

		for (WebsiteParser parser : list) {
			String listPath = parser.getListPath();
			if (!StringUtils.isBlank(listPath)) {
				Elements listElements = document.select(listPath);
				if (listElements != null && listElements.size() > 0) {
					return listElements;
				}
			}
		}
		return super.getPossibleListElement(document);

	}

	public void removeNoNeedElement(Element element, String string) {
		if (element == null) {
			return;
		}
		Elements elements = element.select(string);
		if (elements == null || elements.size() == 0) {
			return;
		} else {
			for (Element element2 : elements) {
				element2.remove();
			}
		}
		return;
	}
}
