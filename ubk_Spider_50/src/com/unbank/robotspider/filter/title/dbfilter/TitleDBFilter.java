package com.unbank.robotspider.filter.title.dbfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.dao.WebsiteParserMapper;
import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.filter.title.TitleBaseFilter;
import com.unbank.robotspider.filter.title.TitleFilter;
import com.unbank.robotspider.filter.titlelist.dbfilter.MulTitleListDBFilter;

public class TitleDBFilter {
	private static Log logger = LogFactory.getLog(TitleDBFilter.class);
	private static TitleDBFilter titleDBFilter=new TitleDBFilter();
	
	private TitleDBFilter(){}
	
	private static HashMap<String, TitleFilter> filtermap = new HashMap<String, TitleFilter>();
	
	
	public void loadFiltersFromDB(){
		//logger.info("========加载TitleDBFilter中========"+filtermap.size());		
		SqlSession sqlSession = MyBatisConnectionFactory.getInstanceSessionFactory().openSession();
		WebsiteParserMapper websiteParserMapper = sqlSession.getMapper(WebsiteParserMapper.class);

		List<WebsiteParser> parserList = websiteParserMapper.selectByExample(null);
		HashMap<String,List<WebsiteParser>> map=new HashMap<String,List<WebsiteParser>>();
		for (WebsiteParser parser : parserList) {
			if(map.containsKey(parser.getContenturlHost())){
				map.get(parser.getContenturlHost()).add(parser);
			}else{
				List<WebsiteParser> list=new ArrayList<WebsiteParser>();
				list.add(parser);
				map.put(parser.getContenturlHost(), list);
			}
		}
		
		for(String key:map.keySet()){
			filtermap.put(key, new MulTitleDBFilter(map.get(key)));
		}
		/*
		
		System.out.println("ParserSize:"+parserList.size());
		for (WebsiteParser parser : parserList) {
			filtermap.put(parser.getContenturlHost(), new InnerFilter(parser));
		}*/
	}
	
	public Map<String,TitleFilter> getFilters(){
		logger.info("========getTitleDBFilter=======size:"+filtermap.size());
		if (filtermap.size() == 0) {
			loadFiltersFromDB();
		}
		
		return filtermap;
	}
	
	public static TitleDBFilter getInstance(){
		return titleDBFilter;
	}
	
}
