package com.unbank.robotspider.action.model.normal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.entity.WebsiteParser;

public class MulDBFilter extends BaseFilter {

	private List<WebsiteParser> list = new ArrayList<WebsiteParser>();

	
	public MulDBFilter(List<WebsiteParser> list ){
		this.list=list;
	}
	
	// private WebsiteParser parser;

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document, String title) {
		System.out.println("从数据库中读取的解析规则，解析页面：" + url);
		remove(document.body());
		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}

		for (WebsiteParser parser : list) {
			// 移除多余的元素杂质
			String needlessElementPaths = parser.getNeedlesselementsPath();
			if (!StringUtils.isBlank(needlessElementPaths)) {
				String[] paths = needlessElementPaths.split("\\|\\|");
				for (String path : paths) {
					removeNoNeedElement(bodyElement, path);
				}
			}
		}

		for (WebsiteParser parser : list) {
			// 只要找到结果就返回（可能存在BUG）
			Elements aimElements = bodyElement.select(parser.getNewscontentPath());
			if (aimElements != null && aimElements.size() > 0) {
				return aimElements.first();
			}
		}

		return super.removeNoNeedHtmlElement(url, document, title);
	}

	@Override
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

	@Override
	public String removeStockCode(String content) {
		// TODO Auto-generated method stub
		return super.removeStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		
		for (WebsiteParser parser : list) {
		
		String needlessStr = parser.getNeedlesscharsPath();

		if (!StringUtils.isBlank(needlessStr)) {
			String[] needlessChars = needlessStr.split("\\|\\|");

			for (String needlessChar : needlessChars) {
				content = content.replace(needlessChar, "");
			}
		}
		String needlessTailStr = parser.getNeedlesstailsPath();

		if (!StringUtils.isBlank(needlessTailStr)) {

			String[] needlessTailChars = needlessTailStr.split("\\|\\|");

			for (String tail : needlessTailChars) {
				if (content.contains(tail)) {
					int index = content.indexOf(tail);
					content = content.substring(0, index);
				}
			}
		}
		}
		return super.replaceSpechars(content);
	}

	@Override
	public String getContent(String url, Document document, String title) {
		return super.getContent(url, document, title);
	}

	@Override
	public String getContent(String url, String htmlString, String title) {
		return super.getContent(url, htmlString, title);
	}

}
