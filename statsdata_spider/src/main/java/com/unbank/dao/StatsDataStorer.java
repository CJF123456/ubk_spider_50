package com.unbank.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.AreaModel;
import com.unbank.mybatis.entity.DataModel;
import com.unbank.mybatis.entity.SQLAdapter;
import com.unbank.mybatis.factory.BaseDao;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.DataModelMapper;

public class StatsDataStorer extends BaseDao {

	public boolean isDataExit(String tableDataName, int id, String year,
			String month, int areaid) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<DataModel> datas = null;
		try {
			DataModelMapper DataModelMapper = sqlSession
					.getMapper(DataModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from " + tableDataName + " where sub_id ="
					+ id + " and date_year='" + year + "' and date_month ='"
					+ month + "' and area_id =" + areaid;
			sqlAdapter.setSql(sql);
			datas = DataModelMapper.readDataBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		if (datas.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int saveDateModel(String tableDataName, Map<String, Object> resultMap) {
		String sql = "insert into  " + tableDataName;
		return insertReturnPriKey(sql, resultMap);
	}

	public int checkArea(String regcode) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<AreaModel> areas = null;
		String id = regcode.substring(0, 2);
		try {
			DataModelMapper dataModelMapper = sqlSession
					.getMapper(DataModelMapper.class);
			SQLAdapter sqlAdapter = new SQLAdapter();
			String sql = "select * from qu_area where ind_code ='" + id + "'";
			sqlAdapter.setSql(sql);
			areas = dataModelMapper.readAreaBySQLAdapter(sqlAdapter);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(true);
		} finally {
			sqlSession.close();
		}
		if (areas.size() > 0) {
			return areas.get(0).getId();
		} else {
			return 1;
		}
	}

}
