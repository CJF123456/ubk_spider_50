package com.unbank.store;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.dao.MyBatisConnectionFactory;
import com.unbank.mybatis.dao.ShixinUnitMapper;
import com.unbank.mybatis.entity.ShixinUnit;

public class ShixinUnitStore {
	private static Log logger = LogFactory.getLog(ShixinUnitStore.class);
	public void saveShixinUnit(ShixinUnit shixinUnit) {

		SqlSession sqlSession = MyBatisConnectionFactory
				.getInstanceSessionFactory().openSession();

		try {
			ShixinUnitMapper shixinUnitMapper = sqlSession
					.getMapper(ShixinUnitMapper.class);
			shixinUnitMapper.insertSelective(shixinUnit);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(shixinUnit.getSouceid());
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}

	}

}
