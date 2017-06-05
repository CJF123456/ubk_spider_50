package com.unbank.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.SQLAdapter;
import com.unbank.mybatis.entity.TreeModel;
import com.unbank.mybatis.factory.BaseDao;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.TreeModelMapper;

public class StatsTreeStorer {

	public List<TreeModel> getTrees(String tableName) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<TreeModel> trees = null;
		try {
			TreeModelMapper treeModelMapper = sqlSession
					.getMapper(TreeModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from " + tableName
					+ " where updatetask =0 limit 1000";
			sqlAdapter.setSql(sql);
			trees = treeModelMapper.readTreeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return trees;

	}

	public void updateTask(String tableName, String ids) {
		String sql = "update " + tableName
				+ " set updatetask =1 where id  in ( " + ids + ") ";
		new BaseDao().executeSQL(sql);
	}

	public void updateTask(String tableName) {
		String sql = "update " + tableName + " set updatetask =0";
		new BaseDao().executeSQL(sql);
	}

	public List<TreeModel> getTreesByName(String tableName, String name) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<TreeModel> trees = null;
		try {
			TreeModelMapper treeModelMapper = sqlSession
					.getMapper(TreeModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from " + tableName + " where value ='"
					+ name + "'";
			sqlAdapter.setSql(sql);
			trees = treeModelMapper.readTreeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return trees;
	}

	public List<TreeModel> getTreesByPid(String tableName, int pid) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<TreeModel> trees = null;
		try {
			TreeModelMapper treeModelMapper = sqlSession
					.getMapper(TreeModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from " + tableName + " where pid =" + pid;
			sqlAdapter.setSql(sql);
			trees = treeModelMapper.readTreeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return trees;
	}

	public List<TreeModel> getTreesByid(String tableName, int id) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<TreeModel> trees = null;
		try {
			TreeModelMapper treeModelMapper = sqlSession
					.getMapper(TreeModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from " + tableName + " where id =" + id;
			sqlAdapter.setSql(sql);
			trees = treeModelMapper.readTreeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return trees;
	}

	public int getLastTreeIdByName(String tableName, String names[]) {
		if (names.length > 0) {
			List<TreeModel> trees = getTreesByName(tableName, names[0]);
			int index = 0;
			for (TreeModel treeModel : trees) {
				int id = getLastIdByPidAndLength(treeModel.getId(), names,
						tableName, index);
				if (id != 0) {
					return id;
				}
			}
		}
		return 0;

	}

	private int getLastIdByPidAndLength(int pid, String[] names,
			String tableName, int index) {
		List<TreeModel> trees = getTreesByPid(tableName, pid);
		if (trees.size() == 0) {
			// 如果没有下一级，则是最后一级的id
			return pid;
		} else {
			index++;
			if (index == names.length) {
				// 如果 还有子节点 返回 当前的id 或者 0
				return 0;
			}
			for (TreeModel treeModel : trees) {
				String name = treeModel.getValue();
				if (name.trim().equals(names[index])) {
					int id = getLastIdByPidAndLength(treeModel.getId(), names,
							tableName, index);
					if (id != 0) {
						return id;
					}
				}
			}
		}
		return 0;

	}
}
