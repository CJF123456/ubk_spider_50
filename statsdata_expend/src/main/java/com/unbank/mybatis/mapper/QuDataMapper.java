package com.unbank.mybatis.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.unbank.mybatis.entity.UpdateTime;

public interface QuDataMapper {
    
    
    
   
    //国家统计局
    //国家年的最大
    List<Map<String, Object>> getNBSMaxYear();
    //国家年的最小
    List<Map<String, Object>> getNBSMinYear();
    
    //国家季度最大
    List<Map<String, Object>> getNBSMaxQuarter();
    //国家季度最小
    List<Map<String, Object>> getNBSMinQuarter();
    //国家月最大
    List<Map<String, Object>> getNBSMaxMonth();
    //国家月最小
    List<Map<String, Object>> getNBSMinMonth();
    //区域年的最大
    List<Map<String, Object>> getNBSqMaxYear();
    //区域年的最小
    List<Map<String, Object>> getNBSqMinYear();
    
    //区域季度最大
    List<Map<String, Object>> getNBSqMaxQuarter();
    //区域季度最小
    List<Map<String, Object>> getNBSqMinQuarter();
    //区域月最大
    List<Map<String, Object>> getNBSqMaxMonth();
    //区域月最小
    List<Map<String, Object>> getNBSqMinMonth();
    
    //批量更新时间
    int newbatchupdateyear(@Param("params")Map<String,Object> params);
    int newbatchupdatestartyear(@Param("params")Map<String,Object> params);
    
    int countryyear(List<UpdateTime> list);
    int countryquarter(List<UpdateTime> list);
    int countrymonth(List<UpdateTime> list);
    int countryyearstart(List<UpdateTime> list);
    int countryquarterstart(List<UpdateTime> list);
    int countrymonthstart(List<UpdateTime> list);
    int regionyear(List<UpdateTime> list);
    int regionquarter(List<UpdateTime> list);
    int regionmonth(List<UpdateTime> list);
    int regionyearstart(List<UpdateTime> list);
    int regionquarterstart(List<UpdateTime> list);
    int regionmonthstart(List<UpdateTime> list);
    
    int initupdateandstarttime(@Param("tablename")String tablename);
    
    
    List<Map<String, Object>> getData(@Param("tablename")String tablename);
    
    int updatetime(@Param("tablename")String tablename,@Param("list")List<UpdateTime> list);
    
    
    
}