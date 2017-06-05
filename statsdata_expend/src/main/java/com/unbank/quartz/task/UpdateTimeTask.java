package com.unbank.quartz.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.UpdateTime;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.QuDataMapper;
import com.unbank.util.DateUtil;

public class UpdateTimeTask {
	public static void main(String[] args) {
		String trees = "country_datafield_month";
		String datas = "country_month_data";
		new UpdateTimeTask().doTask(trees, datas);
	}

	public void doTask(String trees, String datas) {
		String treeArray[] = trees.split(",");
		String dataArray[] = datas.split(",");
		for (int i = 0; i < dataArray.length; i++) {
			update(dataArray[i], treeArray[i]);
		}
	}

	public void update(String datatablename, String updatetable) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		try {
			QuDataMapper dataMapper = sqlSession.getMapper(QuDataMapper.class);
			List<Map<String, Object>> data = dataMapper.getData(datatablename);
			if (!data.isEmpty()) {
				List<UpdateTime> list = new ArrayList<UpdateTime>();
				for (Map<String, Object> q : data) {
					UpdateTime up = new UpdateTime();
					up.setId(Integer.parseInt(q.get("sub_id").toString()));
					String[] updatetemp = q.get("update_time").toString()
							.split("-");
					up.setUpdateTime(DateUtil.getyear(
							Integer.parseInt(updatetemp[0]),
							Integer.parseInt(updatetemp[1])));
					String[] starttemp = q.get("start_time").toString()
							.split("-");
					up.setStartTime(DateUtil.getyear(
							Integer.parseInt(starttemp[0]),
							Integer.parseInt(starttemp[1])));
					list.add(up);
				}
				if (!list.isEmpty()) {
					dataMapper.updatetime(updatetable, list);
				}
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
