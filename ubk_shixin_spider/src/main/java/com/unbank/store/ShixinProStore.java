package com.unbank.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.dao.MyBatisConnectionFactory;
import com.unbank.mybatis.dao.ShixinProMapper;
import com.unbank.mybatis.entity.ShixinPro;

public class ShixinProStore {

	private static Log logger = LogFactory.getLog(ShixinProStore.class);
	
	public void saveShixinPro(ShixinPro shixinPro) {

		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();

		try {
			ShixinProMapper shixinProMapper = sqlSession
					.getMapper(ShixinProMapper.class);
			shixinProMapper.insertSelective(shixinPro);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(shixinPro.getSouceid());
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}

	}
}
