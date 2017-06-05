package com.unbank.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.MapFileHomeModel;
import com.unbank.mybatis.entity.MapFileModel;
import com.unbank.mybatis.entity.MapFileTypeModel;
import com.unbank.mybatis.entity.SQLAdapter;
import com.unbank.mybatis.entity.TreeModel;
import com.unbank.mybatis.factory.BaseDao;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.MapFileModelMapper;

public class MapFileDataStorer {

	
	public void updateVidAndPid(){
		String sql = "update map_field set sub_id =0,v_id =0";
		new BaseDao().executeSQL(sql);
		
	}
	
	public List<MapFileModel> readMapFileModels() {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<MapFileModel> dataModels = null;
		try {
			MapFileModelMapper dataModelMapper = sqlSession
					.getMapper(MapFileModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from map_field";
			sqlAdapter.setSql(sql);
			dataModels = dataModelMapper.readMapFileBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return dataModels;

	}

	public MapFileTypeModel readMapFileTypeById(int type) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<MapFileTypeModel> typeModels = null;
		try {
			MapFileModelMapper dataModelMapper = sqlSession
					.getMapper(MapFileModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from map_field_type where id =" + type;
			sqlAdapter.setSql(sql);
			typeModels = dataModelMapper
					.readMapFileTypeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return typeModels.get(0);
	}

	public void updateMapFileModel(int id, TreeModel treeModel, String fileName) {
		String sql = "update map_field set " + fileName + " = "
				+ treeModel.getId() + " , unit = '" + treeModel.getUnit()
				+ "' where id =" + id;
		new BaseDao().executeSQL(sql);
	}

	public void updateMapFileHomeModel(int id, TreeModel treeModel,
			String fileName) {
		String sql = "update map_field_homepage set " + fileName + " = "
				+ treeModel.getId() + " , unit = '" + treeModel.getUnit()
				+ "' where id =" + id;
		new BaseDao().executeSQL(sql);
	}

	//
	public List<MapFileHomeModel> readMapFileHomeModels() {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<MapFileHomeModel> dataModels = null;
		try {
			MapFileModelMapper dataModelMapper = sqlSession
					.getMapper(MapFileModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from map_field_homepage";
			sqlAdapter.setSql(sql);
			dataModels = dataModelMapper
					.readMapFileHomeBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		return dataModels;

	}

}
