/**
 * 
 */
package com.unbank.spider.mybatis.vo;

/**
 * @ClassName: WinxinNumber
 * @Description: 公众号
 * @author: Administrator
 * @version: V1.0
 * @date: 2016-12-6 下午4:46:11
 */
public class WinxinNumber {
	// name
	private String numname;
	private String numnameurl;
	// 公众号
	private String numid;
	// 发文数
	private String numdocamount;
	// 月阅读数
	private String numread;
	// 功能介绍
	private String numshow;
	// 认证
	private String numauthentic;
	// 最近文章
	private String numdoc;
	private String numdocurl;
	private String date;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the numname
	 */
	public String getNumname() {
		return numname;
	}

	/**
	 * @param numname
	 *            the numname to set
	 */
	public void setNumname(String numname) {
		this.numname = numname;
	}

	/**
	 * @return the numnameurl
	 */
	public String getNumnameurl() {
		return numnameurl;
	}

	/**
	 * @param numnameurl
	 *            the numnameurl to set
	 */
	public void setNumnameurl(String numnameurl) {
		this.numnameurl = numnameurl;
	}

	/**
	 * @return the numid
	 */
	public String getNumid() {
		return numid;
	}

	/**
	 * @param numid
	 *            the numid to set
	 */
	public void setNumid(String numid) {
		this.numid = numid;
	}

	/**
	 * @return the numdocamount
	 */
	public String getNumdocamount() {
		return numdocamount;
	}

	/**
	 * @param numdocamount
	 *            the numdocamount to set
	 */
	public void setNumdocamount(String numdocamount) {
		this.numdocamount = numdocamount;
	}

	/**
	 * @return the numread
	 */
	public String getNumread() {
		return numread;
	}

	/**
	 * @param numread
	 *            the numread to set
	 */
	public void setNumread(String numread) {
		this.numread = numread;
	}

	/**
	 * @return the numshow
	 */
	public String getNumshow() {
		return numshow;
	}

	/**
	 * @param numshow
	 *            the numshow to set
	 */
	public void setNumshow(String numshow) {
		this.numshow = numshow;
	}

	/**
	 * @return the numauthentic
	 */
	public String getNumauthentic() {
		return numauthentic;
	}

	/**
	 * @param numauthentic
	 *            the numauthentic to set
	 */
	public void setNumauthentic(String numauthentic) {
		this.numauthentic = numauthentic;
	}

	/**
	 * @return the numdoc
	 */
	public String getNumdoc() {
		return numdoc;
	}

	/**
	 * @param numdoc
	 *            the numdoc to set
	 */
	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	/**
	 * @return the numdocurl
	 */
	public String getNumdocurl() {
		return numdocurl;
	}

	/**
	 * @param numdocurl
	 *            the numdocurl to set
	 */
	public void setNumdocurl(String numdocurl) {
		this.numdocurl = numdocurl;
	}

}
