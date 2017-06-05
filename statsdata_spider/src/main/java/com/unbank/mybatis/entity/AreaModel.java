package com.unbank.mybatis.entity;

import java.util.Date;

public class AreaModel {
	public int id;
	public String area_code;
	public String ind_code;
	public String name;
	public Date effective_date;
	public Date expiry_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getInd_code() {
		return ind_code;
	}

	public void setInd_code(String ind_code) {
		this.ind_code = ind_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(Date effective_date) {
		this.effective_date = effective_date;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

}
