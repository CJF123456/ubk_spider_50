package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComYuanLin extends BaseFilter
{
	private String domain="news.yuanlin.com";
	
	public ComYuanLin(){
		FilterLocator.getInstance().register(domain, this);
	}
	
	@Override
	public Element removeNoNeedHtmlElement(String url, Document document, String title)
	{
		
		Element element = document.select("#ArticleCnt").first(); 
		return element;
//		return super.removeNoNeedHtmlElement(url, document, title);
	}

	@Override
	public void formatElements(Element maxTextElement)
	{
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url)
	{
		super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content)
	{
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content)
	{
		String str=super.replaceSpechars(content);
		int index=str.indexOf("推荐阅读：");
		if(index>0){
			return str.substring(0, index);
		}
		else return str;
		
	}

	@Override
	public String addTag(String content)
	{
		return super.addTag(content);
	}

}
