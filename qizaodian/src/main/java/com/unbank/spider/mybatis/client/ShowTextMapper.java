package com.unbank.spider.mybatis.client;

import com.unbank.spider.mybatis.vo.ShowText;
import com.unbank.spider.mybatis.vo.ShowTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShowTextMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int countByExample(ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int deleteByExample(ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int insert(ShowText record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int insertSelective(ShowText record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	List<ShowText> selectByExampleWithBLOBs(ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	List<ShowText> selectByExample(ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	ShowText selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int updateByExampleSelective(@Param("record") ShowText record, @Param("example") ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int updateByExampleWithBLOBs(@Param("record") ShowText record, @Param("example") ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int updateByExample(@Param("record") ShowText record, @Param("example") ShowTextExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int updateByPrimaryKeySelective(ShowText record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table show_text
	 * @mbggenerated  Wed May 31 17:14:13 CST 2017
	 */
	int updateByPrimaryKeyWithBLOBs(ShowText record);
}