package com.unbank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.client.BhiPPPMapper;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.vo.BhiPPPExample;
import com.unbank.mybatis.vo.BhiPPPWithBLOBs;

public class BhiPppReader {
	public List<BhiPPPWithBLOBs> readBhiPppReader(String area) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<BhiPPPWithBLOBs> bhiPPPs = new ArrayList<BhiPPPWithBLOBs>();
		try {
			BhiPPPMapper bhiPPPMapper = sqlSession
					.getMapper(BhiPPPMapper.class);
			BhiPPPExample bhiPppExample = new BhiPPPExample();
			if (!area.isEmpty()) {
				bhiPppExample.or().andProviceEqualTo(area);
			}
			bhiPppExample.setOrderByClause("id desc ");
			bhiPPPs = bhiPPPMapper.selectByExampleWithBLOBs(bhiPppExample);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return bhiPPPs;

	}

}
