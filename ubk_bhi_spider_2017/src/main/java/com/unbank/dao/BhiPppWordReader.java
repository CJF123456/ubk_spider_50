package com.unbank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.client.BhiPppWordMapper;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.vo.BhiPppWord;
import com.unbank.mybatis.vo.BhiPppWordExample;

public class BhiPppWordReader {

	public List<BhiPppWord> readPPPWordByArea(String name) {
		SqlSession sqlSession = DynamicConnectionFactory.getInstanceSessionFactory("development").openSession();
		List<BhiPppWord> bhipppwords = new ArrayList<BhiPppWord>();
		try {
			BhiPppWordMapper bhiPppWordMapper = sqlSession.getMapper(BhiPppWordMapper.class);
			BhiPppWordExample example = new BhiPppWordExample();
			example.or().andPppAreaEqualTo(name);
			bhipppwords = bhiPppWordMapper.selectByExample(example);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return bhipppwords;
	}

	public List<BhiPppWord> readPPPWord(String name) {
		SqlSession sqlSession = DynamicConnectionFactory.getInstanceSessionFactory("development").openSession();
		List<BhiPppWord> bhipppwords = new ArrayList<BhiPppWord>();
		try {
			BhiPppWordMapper bhiPppWordMapper = sqlSession.getMapper(BhiPppWordMapper.class);
			BhiPppWordExample example = new BhiPppWordExample();
			example.or().andPppNameEqualTo(name);
			bhipppwords = bhiPppWordMapper.selectByExample(example);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return bhipppwords;
	}

	public int deletePPPwordById(int pppId) {
		SqlSession sqlSession = DynamicConnectionFactory.getInstanceSessionFactory("development").openSession();
		try {
			BhiPppWordMapper bhiPppWordMapper = sqlSession.getMapper(BhiPppWordMapper.class);
			bhiPppWordMapper.deleteByPrimaryKey(pppId);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return 0;
	}

}
