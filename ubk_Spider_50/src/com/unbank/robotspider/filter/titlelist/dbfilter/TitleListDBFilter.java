package com.unbank.robotspider.filter.titlelist.dbfilter;

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

import com.unbank.robotspider.action.model.normal.MulDBFilter;
import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.dao.WebsiteParserMapper;
import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.filter.titlelist.BaseTitleListFilter;
import com.unbank.robotspider.filter.titlelist.TitleListFilter;

public class TitleListDBFilter {
	private static Log logger = LogFactory.getLog(TitleListDBFilter.class);
	private static TitleListDBFilter titleListDBFilter=new TitleListDBFilter();
	private TitleListDBFilter(){}
	
	private static Map<String,TitleListFilter> filtermap=new HashMap<String,TitleListFilter>();
	
	
	public void loadFiltersFromDB(){
		//logger.info("========加载TitleListDBFilter中========"+filtermap.size());
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
			filtermap.put(key, new MulTitleListDBFilter(map.get(key)));
		}
		
		/*for (WebsiteParser parser : parserList) {
			filtermap.put(parser.getContenturlHost(), new InnerFilter(parser));
		}*/
	}
	
	public Map<String,TitleListFilter> getFilters(){
		logger.info("========getTitleListDBFilter========"+filtermap.size());
		if (filtermap.size() == 0) {
			loadFiltersFromDB();
		}
		
		return filtermap;
	}
	
	public static TitleListDBFilter getInstance(){
		
		return titleListDBFilter;
		
	}
	

	
}
