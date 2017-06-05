package com.unbank.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.SQLAdapter;
import com.unbank.mybatis.entity.TreeModel;
import com.unbank.mybatis.factory.BaseDao;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.TreeModelMapper;

public class StatsTreeStorer extends BaseDao {

	public int saveTrees(String tableName, Map<String, Object> colums) {
		String sql = "insert into  " + tableName;
		return insertReturnPriKey(sql, colums);
	}

	public void updateZbCode(String tableName, int id, String code) {
		String sql = "update  " + tableName + " set code= '" + code
				+ "'  where id=" + id;
		executeSQL(sql);
	}

	public int checkTreeExit(String tableName, String id, String name) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<TreeModel> trees = null;
		try {
			TreeModelMapper treeModelMapper = sqlSession
					.getMapper(TreeModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = null;
			if (name.isEmpty()) {
				sql = "select * from " + tableName + " where cid ='" + id + "'";
			} else {

				sql = "select * from " + tableName + " where cid ='" + id
						+ "' and value ='" + name + "'";
			}
			sqlAdapter.setSql(sql);
			trees = treeModelMapper.readTreeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		if (trees.size() > 0) {
			return trees.get(0).getId();
		} else {
			return 0;
		}

	}
}
