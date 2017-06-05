package com.unbank.mybatis.mapper;

import java.util.List;

import com.unbank.mybatis.entity.SQLAdapter;
import com.unbank.mybatis.entity.TreeModel;

public interface TreeModelMapper {

	public List<TreeModel> readTreeBySQLAdapter(SQLAdapter sqlAdapter);
}
