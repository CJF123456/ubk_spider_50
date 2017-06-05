package com.unbank.mybatis.mapper;

import java.util.List;

import com.unbank.mybatis.entity.AreaModel;
import com.unbank.mybatis.entity.DataModel;
import com.unbank.mybatis.entity.SQLAdapter;

public interface DataModelMapper {

	public List<DataModel> readDataBySQLAdapter(SQLAdapter sqlAdapter);
	
	public List<AreaModel> readAreaBySQLAdapter(SQLAdapter sqlAdapter);
}
