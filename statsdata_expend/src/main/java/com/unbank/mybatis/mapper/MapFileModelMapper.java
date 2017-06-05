package com.unbank.mybatis.mapper;

import java.util.List;

import com.unbank.mybatis.entity.MapFileHomeModel;
import com.unbank.mybatis.entity.MapFileModel;
import com.unbank.mybatis.entity.MapFileTypeModel;
import com.unbank.mybatis.entity.SQLAdapter;

public interface MapFileModelMapper {

	public List<MapFileModel> readMapFileBySQLAdapter(SQLAdapter sqlAdapter);

	public List<MapFileTypeModel> readMapFileTypeBySQLAdapter(
			SQLAdapter sqlAdapter);

	public List<MapFileHomeModel> readMapFileHomeBySQLAdapter(
			SQLAdapter sqlAdapter);
}
