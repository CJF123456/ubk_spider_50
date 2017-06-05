package com.unbank.mybatis.entity;

public class MapFileModel {
	private int id;

	private int type;

	private String name;

	private String unit;

	private String pname;

	private String vname;

	private int proportion;

	private int default_flag;
	private int field_order;
	private int sub_id;
	private int v_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public int getProportion() {
		return proportion;
	}

	public void setProportion(int proportion) {
		this.proportion = proportion;
	}

	public int getDefault_flag() {
		return default_flag;
	}

	public void setDefault_flag(int default_flag) {
		this.default_flag = default_flag;
	}

	public int getField_order() {
		return field_order;
	}

	public void setField_order(int field_order) {
		this.field_order = field_order;
	}

	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public int getV_id() {
		return v_id;
	}

	public void setV_id(int v_id) {
		this.v_id = v_id;
	}

}
