package com.unbank.robotspider.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.dao.WebsiteParserMapper;
import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.entity.WebsiteParserExample;

public class WebsiteParserReader {
	private final Logger logger = Logger.getLogger(WebsiteParserReader.class);

	public WebsiteParser readWebsitePaserById(Integer websiteId) {

		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		WebsiteParserMapper websiteParserMapper = sqlSession
				.getMapper(WebsiteParserMapper.class);
		WebsiteParser websiteParser = null;
		try {
			WebsiteParserExample example = new WebsiteParserExample();
			example.or().andWebsiteIdEqualTo(websiteId);
			List<WebsiteParser> websiteParsers = websiteParserMapper
					.selectByExample(example);
			if (websiteParsers != null && websiteParsers.size() != 0) {
				websiteParser = websiteParsers.get(0);
			}
		} catch (Exception e) {
			logger.info("", e);
		} finally {
			sqlSession.close();
		}
		return websiteParser;
	}

}
