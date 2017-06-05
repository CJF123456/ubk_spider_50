package com.unbank.mybatis.entity;

public class DataModel {
	private int id;
	private int sub_id;
	private String date_year;
	private String date_month;
	private double datavalue;
	private int area_id;
	private int industry_id;
	private String charascope;
	private int date_year_int;
	private int date_month_int;
	private int insert_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public String getDate_year() {
		return date_year;
	}

	public void setDate_year(String date_year) {
		this.date_year = date_year;
	}

	public String getDate_month() {
		return date_month;
	}

	public void setDate_month(String date_month) {
		this.date_month = date_month;
	}

	public double getDatavalue() {
		return datavalue;
	}

	public void setDatavalue(double datavalue) {
		this.datavalue = datavalue;
	}

	public int getArea_id() {
		return area_id;
	}

	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}

	public int getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(int industry_id) {
		this.industry_id = industry_id;
	}

	public String getCharascope() {
		return charascope;
	}

	public void setCharascope(String charascope) {
		this.charascope = charascope;
	}

	public int getDate_year_int() {
		return date_year_int;
	}

	public void setDate_year_int(int date_year_int) {
		this.date_year_int = date_year_int;
	}

	public int getDate_month_int() {
		return date_month_int;
	}

	public void setDate_month_int(int date_month_int) {
		this.date_month_int = date_month_int;
	}

	public int getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(int insert_time) {
		this.insert_time = insert_time;
	}

}
