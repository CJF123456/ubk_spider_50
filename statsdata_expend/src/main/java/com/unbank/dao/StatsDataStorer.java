package com.unbank.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.DataModel;
import com.unbank.mybatis.entity.SQLAdapter;
import com.unbank.mybatis.factory.BaseDao;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.DataModelMapper;

public class StatsDataStorer {

	public List<DataModel> getDatas(String tableName) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<DataModel> dataModels = null;
		try {
			DataModelMapper dataModelMapper = sqlSession
					.getMapper(DataModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from " + tableName
					+ " where updatetask =0 limit 10000";
			sqlAdapter.setSql(sql);
			dataModels = dataModelMapper.readDataBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return dataModels;

	}

	public void updateTask(String tableName, String ids) {
		String sql = "update " + tableName
				+ " set updatetask =1 where id  in ( " + ids + ") ";
		new BaseDao().executeSQL(sql);
	}

}
