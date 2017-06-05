package com.unbank.robotspider.entity;

import java.util.ArrayList;
import java.util.List;

public class WebsiteParserExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public WebsiteParserExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andParserIdIsNull() {
			addCriterion("parser_id is null");
			return (Criteria) this;
		}

		public Criteria andParserIdIsNotNull() {
			addCriterion("parser_id is not null");
			return (Criteria) this;
		}

		public Criteria andParserIdEqualTo(Integer value) {
			addCriterion("parser_id =", value, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdNotEqualTo(Integer value) {
			addCriterion("parser_id <>", value, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdGreaterThan(Integer value) {
			addCriterion("parser_id >", value, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("parser_id >=", value, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdLessThan(Integer value) {
			addCriterion("parser_id <", value, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdLessThanOrEqualTo(Integer value) {
			addCriterion("parser_id <=", value, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdIn(List<Integer> values) {
			addCriterion("parser_id in", values, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdNotIn(List<Integer> values) {
			addCriterion("parser_id not in", values, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdBetween(Integer value1, Integer value2) {
			addCriterion("parser_id between", value1, value2, "parserId");
			return (Criteria) this;
		}

		public Criteria andParserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("parser_id not between", value1, value2, "parserId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdIsNull() {
			addCriterion("website_id is null");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdIsNotNull() {
			addCriterion("website_id is not null");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdEqualTo(Integer value) {
			addCriterion("website_id =", value, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdNotEqualTo(Integer value) {
			addCriterion("website_id <>", value, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdGreaterThan(Integer value) {
			addCriterion("website_id >", value, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("website_id >=", value, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdLessThan(Integer value) {
			addCriterion("website_id <", value, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdLessThanOrEqualTo(Integer value) {
			addCriterion("website_id <=", value, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdIn(List<Integer> values) {
			addCriterion("website_id in", values, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdNotIn(List<Integer> values) {
			addCriterion("website_id not in", values, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdBetween(Integer value1, Integer value2) {
			addCriterion("website_id between", value1, value2, "websiteId");
			return (Criteria) this;
		}

		public Criteria andWebsiteIdNotBetween(Integer value1, Integer value2) {
			addCriterion("website_id not between", value1, value2, "websiteId");
			return (Criteria) this;
		}

		public Criteria andListPathIsNull() {
			addCriterion("list_path is null");
			return (Criteria) this;
		}

		public Criteria andListPathIsNotNull() {
			addCriterion("list_path is not null");
			return (Criteria) this;
		}

		public Criteria andListPathEqualTo(String value) {
			addCriterion("list_path =", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathNotEqualTo(String value) {
			addCriterion("list_path <>", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathGreaterThan(String value) {
			addCriterion("list_path >", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathGreaterThanOrEqualTo(String value) {
			addCriterion("list_path >=", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathLessThan(String value) {
			addCriterion("list_path <", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathLessThanOrEqualTo(String value) {
			addCriterion("list_path <=", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathLike(String value) {
			addCriterion("list_path like", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathNotLike(String value) {
			addCriterion("list_path not like", value, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathIn(List<String> values) {
			addCriterion("list_path in", values, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathNotIn(List<String> values) {
			addCriterion("list_path not in", values, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathBetween(String value1, String value2) {
			addCriterion("list_path between", value1, value2, "listPath");
			return (Criteria) this;
		}

		public Criteria andListPathNotBetween(String value1, String value2) {
			addCriterion("list_path not between", value1, value2, "listPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathIsNull() {
			addCriterion("list_needlesselements_path is null");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathIsNotNull() {
			addCriterion("list_needlesselements_path is not null");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathEqualTo(String value) {
			addCriterion("list_needlesselements_path =", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathNotEqualTo(String value) {
			addCriterion("list_needlesselements_path <>", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathGreaterThan(String value) {
			addCriterion("list_needlesselements_path >", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathGreaterThanOrEqualTo(
				String value) {
			addCriterion("list_needlesselements_path >=", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathLessThan(String value) {
			addCriterion("list_needlesselements_path <", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathLessThanOrEqualTo(
				String value) {
			addCriterion("list_needlesselements_path <=", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathLike(String value) {
			addCriterion("list_needlesselements_path like", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathNotLike(String value) {
			addCriterion("list_needlesselements_path not like", value,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathIn(List<String> values) {
			addCriterion("list_needlesselements_path in", values,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathNotIn(List<String> values) {
			addCriterion("list_needlesselements_path not in", values,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathBetween(String value1,
				String value2) {
			addCriterion("list_needlesselements_path between", value1, value2,
					"listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andListNeedlesselementsPathNotBetween(String value1,
				String value2) {
			addCriterion("list_needlesselements_path not between", value1,
					value2, "listNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andContenturlHostIsNull() {
			addCriterion("contenturl_host is null");
			return (Criteria) this;
		}

		public Criteria andContenturlHostIsNotNull() {
			addCriterion("contenturl_host is not null");
			return (Criteria) this;
		}

		public Criteria andContenturlHostEqualTo(String value) {
			addCriterion("contenturl_host =", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostNotEqualTo(String value) {
			addCriterion("contenturl_host <>", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostGreaterThan(String value) {
			addCriterion("contenturl_host >", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostGreaterThanOrEqualTo(String value) {
			addCriterion("contenturl_host >=", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostLessThan(String value) {
			addCriterion("contenturl_host <", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostLessThanOrEqualTo(String value) {
			addCriterion("contenturl_host <=", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostLike(String value) {
			addCriterion("contenturl_host like", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostNotLike(String value) {
			addCriterion("contenturl_host not like", value, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostIn(List<String> values) {
			addCriterion("contenturl_host in", values, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostNotIn(List<String> values) {
			addCriterion("contenturl_host not in", values, "contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostBetween(String value1, String value2) {
			addCriterion("contenturl_host between", value1, value2,
					"contenturlHost");
			return (Criteria) this;
		}

		public Criteria andContenturlHostNotBetween(String value1, String value2) {
			addCriterion("contenturl_host not between", value1, value2,
					"contenturlHost");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathIsNull() {
			addCriterion("newstitle_path is null");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathIsNotNull() {
			addCriterion("newstitle_path is not null");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathEqualTo(String value) {
			addCriterion("newstitle_path =", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathNotEqualTo(String value) {
			addCriterion("newstitle_path <>", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathGreaterThan(String value) {
			addCriterion("newstitle_path >", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathGreaterThanOrEqualTo(String value) {
			addCriterion("newstitle_path >=", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathLessThan(String value) {
			addCriterion("newstitle_path <", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathLessThanOrEqualTo(String value) {
			addCriterion("newstitle_path <=", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathLike(String value) {
			addCriterion("newstitle_path like", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathNotLike(String value) {
			addCriterion("newstitle_path not like", value, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathIn(List<String> values) {
			addCriterion("newstitle_path in", values, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathNotIn(List<String> values) {
			addCriterion("newstitle_path not in", values, "newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathBetween(String value1, String value2) {
			addCriterion("newstitle_path between", value1, value2,
					"newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitlePathNotBetween(String value1, String value2) {
			addCriterion("newstitle_path not between", value1, value2,
					"newstitlePath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathIsNull() {
			addCriterion("newstitle_needlesselements_path is null");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathIsNotNull() {
			addCriterion("newstitle_needlesselements_path is not null");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathEqualTo(String value) {
			addCriterion("newstitle_needlesselements_path =", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathNotEqualTo(String value) {
			addCriterion("newstitle_needlesselements_path <>", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathGreaterThan(String value) {
			addCriterion("newstitle_needlesselements_path >", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathGreaterThanOrEqualTo(
				String value) {
			addCriterion("newstitle_needlesselements_path >=", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathLessThan(String value) {
			addCriterion("newstitle_needlesselements_path <", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathLessThanOrEqualTo(
				String value) {
			addCriterion("newstitle_needlesselements_path <=", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathLike(String value) {
			addCriterion("newstitle_needlesselements_path like", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathNotLike(String value) {
			addCriterion("newstitle_needlesselements_path not like", value,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathIn(List<String> values) {
			addCriterion("newstitle_needlesselements_path in", values,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathNotIn(
				List<String> values) {
			addCriterion("newstitle_needlesselements_path not in", values,
					"newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathBetween(String value1,
				String value2) {
			addCriterion("newstitle_needlesselements_path between", value1,
					value2, "newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstitleNeedlesselementsPathNotBetween(
				String value1, String value2) {
			addCriterion("newstitle_needlesselements_path not between", value1,
					value2, "newstitleNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathIsNull() {
			addCriterion("newstime_path is null");
			return (Criteria) this;
		}

		public Criteria andNewstimePathIsNotNull() {
			addCriterion("newstime_path is not null");
			return (Criteria) this;
		}

		public Criteria andNewstimePathEqualTo(String value) {
			addCriterion("newstime_path =", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathNotEqualTo(String value) {
			addCriterion("newstime_path <>", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathGreaterThan(String value) {
			addCriterion("newstime_path >", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathGreaterThanOrEqualTo(String value) {
			addCriterion("newstime_path >=", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathLessThan(String value) {
			addCriterion("newstime_path <", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathLessThanOrEqualTo(String value) {
			addCriterion("newstime_path <=", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathLike(String value) {
			addCriterion("newstime_path like", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathNotLike(String value) {
			addCriterion("newstime_path not like", value, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathIn(List<String> values) {
			addCriterion("newstime_path in", values, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathNotIn(List<String> values) {
			addCriterion("newstime_path not in", values, "newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathBetween(String value1, String value2) {
			addCriterion("newstime_path between", value1, value2,
					"newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimePathNotBetween(String value1, String value2) {
			addCriterion("newstime_path not between", value1, value2,
					"newstimePath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathIsNull() {
			addCriterion("newstime_needlesselements_path is null");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathIsNotNull() {
			addCriterion("newstime_needlesselements_path is not null");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathEqualTo(String value) {
			addCriterion("newstime_needlesselements_path =", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathNotEqualTo(String value) {
			addCriterion("newstime_needlesselements_path <>", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathGreaterThan(String value) {
			addCriterion("newstime_needlesselements_path >", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathGreaterThanOrEqualTo(
				String value) {
			addCriterion("newstime_needlesselements_path >=", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathLessThan(String value) {
			addCriterion("newstime_needlesselements_path <", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathLessThanOrEqualTo(
				String value) {
			addCriterion("newstime_needlesselements_path <=", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathLike(String value) {
			addCriterion("newstime_needlesselements_path like", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathNotLike(String value) {
			addCriterion("newstime_needlesselements_path not like", value,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathIn(List<String> values) {
			addCriterion("newstime_needlesselements_path in", values,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathNotIn(List<String> values) {
			addCriterion("newstime_needlesselements_path not in", values,
					"newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathBetween(String value1,
				String value2) {
			addCriterion("newstime_needlesselements_path between", value1,
					value2, "newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewstimeNeedlesselementsPathNotBetween(
				String value1, String value2) {
			addCriterion("newstime_needlesselements_path not between", value1,
					value2, "newstimeNeedlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathIsNull() {
			addCriterion("newscontent_path is null");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathIsNotNull() {
			addCriterion("newscontent_path is not null");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathEqualTo(String value) {
			addCriterion("newscontent_path =", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathNotEqualTo(String value) {
			addCriterion("newscontent_path <>", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathGreaterThan(String value) {
			addCriterion("newscontent_path >", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathGreaterThanOrEqualTo(String value) {
			addCriterion("newscontent_path >=", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathLessThan(String value) {
			addCriterion("newscontent_path <", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathLessThanOrEqualTo(String value) {
			addCriterion("newscontent_path <=", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathLike(String value) {
			addCriterion("newscontent_path like", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathNotLike(String value) {
			addCriterion("newscontent_path not like", value, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathIn(List<String> values) {
			addCriterion("newscontent_path in", values, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathNotIn(List<String> values) {
			addCriterion("newscontent_path not in", values, "newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathBetween(String value1, String value2) {
			addCriterion("newscontent_path between", value1, value2,
					"newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNewscontentPathNotBetween(String value1,
				String value2) {
			addCriterion("newscontent_path not between", value1, value2,
					"newscontentPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathIsNull() {
			addCriterion("needlesschars_path is null");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathIsNotNull() {
			addCriterion("needlesschars_path is not null");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathEqualTo(String value) {
			addCriterion("needlesschars_path =", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathNotEqualTo(String value) {
			addCriterion("needlesschars_path <>", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathGreaterThan(String value) {
			addCriterion("needlesschars_path >", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathGreaterThanOrEqualTo(String value) {
			addCriterion("needlesschars_path >=", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathLessThan(String value) {
			addCriterion("needlesschars_path <", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathLessThanOrEqualTo(String value) {
			addCriterion("needlesschars_path <=", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathLike(String value) {
			addCriterion("needlesschars_path like", value, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathNotLike(String value) {
			addCriterion("needlesschars_path not like", value,
					"needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathIn(List<String> values) {
			addCriterion("needlesschars_path in", values, "needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathNotIn(List<String> values) {
			addCriterion("needlesschars_path not in", values,
					"needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathBetween(String value1, String value2) {
			addCriterion("needlesschars_path between", value1, value2,
					"needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesscharsPathNotBetween(String value1,
				String value2) {
			addCriterion("needlesschars_path not between", value1, value2,
					"needlesscharsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathIsNull() {
			addCriterion("needlesselements_path is null");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathIsNotNull() {
			addCriterion("needlesselements_path is not null");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathEqualTo(String value) {
			addCriterion("needlesselements_path =", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathNotEqualTo(String value) {
			addCriterion("needlesselements_path <>", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathGreaterThan(String value) {
			addCriterion("needlesselements_path >", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathGreaterThanOrEqualTo(String value) {
			addCriterion("needlesselements_path >=", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathLessThan(String value) {
			addCriterion("needlesselements_path <", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathLessThanOrEqualTo(String value) {
			addCriterion("needlesselements_path <=", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathLike(String value) {
			addCriterion("needlesselements_path like", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathNotLike(String value) {
			addCriterion("needlesselements_path not like", value,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathIn(List<String> values) {
			addCriterion("needlesselements_path in", values,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathNotIn(List<String> values) {
			addCriterion("needlesselements_path not in", values,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathBetween(String value1,
				String value2) {
			addCriterion("needlesselements_path between", value1, value2,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesselementsPathNotBetween(String value1,
				String value2) {
			addCriterion("needlesselements_path not between", value1, value2,
					"needlesselementsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathIsNull() {
			addCriterion("needlesstails_path is null");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathIsNotNull() {
			addCriterion("needlesstails_path is not null");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathEqualTo(String value) {
			addCriterion("needlesstails_path =", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathNotEqualTo(String value) {
			addCriterion("needlesstails_path <>", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathGreaterThan(String value) {
			addCriterion("needlesstails_path >", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathGreaterThanOrEqualTo(String value) {
			addCriterion("needlesstails_path >=", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathLessThan(String value) {
			addCriterion("needlesstails_path <", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathLessThanOrEqualTo(String value) {
			addCriterion("needlesstails_path <=", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathLike(String value) {
			addCriterion("needlesstails_path like", value, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathNotLike(String value) {
			addCriterion("needlesstails_path not like", value,
					"needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathIn(List<String> values) {
			addCriterion("needlesstails_path in", values, "needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathNotIn(List<String> values) {
			addCriterion("needlesstails_path not in", values,
					"needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathBetween(String value1, String value2) {
			addCriterion("needlesstails_path between", value1, value2,
					"needlesstailsPath");
			return (Criteria) this;
		}

		public Criteria andNeedlesstailsPathNotBetween(String value1,
				String value2) {
			addCriterion("needlesstails_path not between", value1, value2,
					"needlesstailsPath");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ptf_website_parser
	 * @mbggenerated  Wed Apr 15 14:56:56 GMT+08:00 2015
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table ptf_website_parser
     *
     * @mbggenerated do_not_delete_during_merge Tue Jan 20 09:57:02 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}