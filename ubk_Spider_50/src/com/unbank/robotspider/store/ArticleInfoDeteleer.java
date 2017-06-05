package com.unbank.robotspider.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;

import com.unbank.robotspider.dao.ArticleContentMapper;
import com.unbank.robotspider.dao.ArticleInfoMapper;
import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.util.FinalWord;

public class ArticleInfoDeteleer {
	private static Log logger = LogFactory.getLog(ArticleInfoDeteleer.class);
	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(ArticleInfoDeteleer.class
					.getClassLoader().getResource("").toURI().getPath()
					+ FinalWord.LOGFILE);
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

//	public static void main(String[] args) {
//		for (int i = 0; i < 1000000; i++) {
//			try {
//				new ArticleInfoDeteleer().deleteArticleInfo(i);
//			} catch (Exception e) {
//				e.printStackTrace();
//				continue;
//			}
//		}
//
//	}

	public void deleteArticleInfo(int crawlId) {
		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();
		ArticleInfoMapper articleInfoMapper = sqlSession
				.getMapper(ArticleInfoMapper.class);
		ArticleContentMapper articleContentMapper = sqlSession
				.getMapper(ArticleContentMapper.class);
		try {

			int i = articleContentMapper.deleteByPrimaryKey(crawlId);
			if (i == 1) {
				int j = articleInfoMapper.deleteByPrimaryKey(crawlId);
				System.out.println(j);
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}

	}
}
