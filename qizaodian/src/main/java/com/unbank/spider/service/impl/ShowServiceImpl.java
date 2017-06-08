package com.unbank.spider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unbank.spider.mybatis.client.ShowPoetryMapper;
import com.unbank.spider.mybatis.client.ShowPoetryTextMapper;
import com.unbank.spider.mybatis.client.ShowTagMapper;
import com.unbank.spider.mybatis.client.ShowTextMapper;
import com.unbank.spider.mybatis.client.ShowTitleMapper;
import com.unbank.spider.mybatis.vo.ShowPoetry;
import com.unbank.spider.mybatis.vo.ShowPoetryExample;
import com.unbank.spider.mybatis.vo.ShowPoetryText;
import com.unbank.spider.mybatis.vo.ShowPoetryTextExample;
import com.unbank.spider.mybatis.vo.ShowTag;
import com.unbank.spider.mybatis.vo.ShowTagExample;
import com.unbank.spider.mybatis.vo.ShowText;
import com.unbank.spider.mybatis.vo.ShowTextExample;
import com.unbank.spider.mybatis.vo.ShowTitle;
import com.unbank.spider.mybatis.vo.ShowTitleExample;
import com.unbank.spider.mybatis.vo.Weixin;
import com.unbank.spider.mybatis.vo.WinxinNumber;
import com.unbank.spider.service.ShowService;
import com.unbank.spider.weixin.task.NumberSpider;
import com.unbank.spider.weixin.task.WeixinSpider;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	ShowTitleMapper showTitleMapper;
	@Autowired
	ShowTextMapper showTextMapper;
	@Autowired
	ShowTagMapper showTagMapper;
	@Autowired
	ShowPoetryMapper showPoetryMapper;
	@Autowired
	ShowPoetryTextMapper showPoetryTextMapper;

	public List<ShowTitle> readShowTitles() {
		ShowTitleExample example = new ShowTitleExample();
		example.setOrderByClause("pubdate desc");
		return showTitleMapper.selectByExample(example);
	}

	public List<ShowText> readerShowTextById(Integer id) {
		ShowTextExample example = new ShowTextExample();
		example.or().andIdEqualTo(id);
		return showTextMapper.selectByExampleWithBLOBs(example);
	}

	public Integer getTitlesCount() {
		ShowTitleExample example = new ShowTitleExample();
		return showTitleMapper.countByExample(example);
	}

	public List<ShowTitle> readShowTitles(Integer pageNo, Integer pageSize) {
		ShowTitleExample example = new ShowTitleExample();
		int start = (pageNo - 1) * pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append("pubdate desc limit ");
		sb.append(start + "");
		sb.append(",10");
		example.setOrderByClause(sb.toString());
		return showTitleMapper.selectByExample(example);
	}

	public List<ShowTag> readerShowTagById(Integer id) {
		ShowTagExample example = new ShowTagExample();
		example.or().andIdEqualTo(id);
		return showTagMapper.selectByExample(example);
	}

	public List<ShowTag> readerShowTags() {
		ShowTagExample example = new ShowTagExample();
		return showTagMapper.selectByExample(example);
	}

	public List<ShowTitle> readShowTitleById(Integer id) {
		ShowTitleExample example = new ShowTitleExample();
		example.or().andIdEqualTo(id);
		return showTitleMapper.selectByExample(example);

	}

	public List<ShowTitle> readShowTitleByTid(String tid) {
		ShowTitleExample example = new ShowTitleExample();
		example.or().andTidEqualTo(tid);
		return showTitleMapper.selectByExample(example);
	}

	public List<ShowTag> readerShowTagsbyconuts() {
		ShowTagExample example = new ShowTagExample();
		example.setOrderByClause("id limit 10 ");
		return showTagMapper.selectByExample(example);
	}

	public List<Weixin> readerWinxinByKeyword(String keyword) {
		List<Weixin> datas = new WeixinSpider(keyword).Spider();
		return datas;
	}

	public List<WinxinNumber> readerWinxinNumberByKeyword(String keyword) {
		List<WinxinNumber> datas = new NumberSpider(keyword).Spider();
		return datas;
	}

	public List<ShowPoetry> readerPoetrys() {
		ShowPoetryExample example = new ShowPoetryExample();
		example.setOrderByClause("newstime desc ");
		return showPoetryMapper.selectByExample(example);
	}

	public List<ShowPoetry> readerPoetrysbyconuts() {
		ShowPoetryExample example = new ShowPoetryExample();
		example.setOrderByClause("newstime desc limit 10 ");
		return showPoetryMapper.selectByExample(example);
	}

	public List<ShowPoetryText> readShowPoetryTextById(Integer id) {
		ShowPoetryTextExample example = new ShowPoetryTextExample();
		example.or().andIdEqualTo(id);
		return showPoetryTextMapper.selectByExampleWithBLOBs(example);
	}

	public List<ShowPoetry> readShowPoetryById(Integer id) {
		ShowPoetryExample example = new ShowPoetryExample();
		example.or().andIdEqualTo(id);
		return showPoetryMapper.selectByExample(example);
	}

	@Override
	public int updatePraise(Integer id, Integer praise) {
		
		ShowTitle st = new ShowTitle();
		st.setId(id);
		st.setPraise(praise);
		return showTitleMapper.updateByPrimaryKeySelective(st);
	}

}
