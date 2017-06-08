package com.unbank.spider.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unbank.spider.mybatis.vo.ShowPoetry;
import com.unbank.spider.mybatis.vo.ShowPoetryText;
import com.unbank.spider.mybatis.vo.ShowTag;
import com.unbank.spider.mybatis.vo.ShowText;
import com.unbank.spider.mybatis.vo.ShowTitle;
import com.unbank.spider.mybatis.vo.Weixin;
import com.unbank.spider.mybatis.vo.WinxinNumber;

@Service
public interface ShowService {

	List<ShowTitle> readShowTitles();

	// 通过ID查询内容
	List<ShowText> readerShowTextById(Integer id);
	
	// 总数
	Integer getTitlesCount();

	// 获取全部的标题
	List<ShowTitle> readShowTitles(Integer pageNo, Integer pageSize);

	// 查询标题通过ID
	List<ShowTitle> readShowTitleById(Integer id);

	// 全部标签
	List<ShowTag> readerShowTags();

	// 通过ID查询标签
	List<ShowTag> readerShowTagById(Integer id);

	// 通过Tid查询标题 标签查标题
	List<ShowTitle> readShowTitleByTid(String tid);

	// 只显示20条标签
	List<ShowTag> readerShowTagsbyconuts();

	// 搜狗微信文章采集
	List<Weixin> readerWinxinByKeyword(String keyword);

	// 搜狗微信公众号采集
	List<WinxinNumber> readerWinxinNumberByKeyword(String keyword);

	// 全部诗词
	List<ShowPoetry> readerPoetrys();

	// 获取前10条
	List<ShowPoetry> readerPoetrysbyconuts();
	
	//通过ID获取诗词内容
	List<ShowPoetryText> readShowPoetryTextById(Integer id);
	
	//通过ID获取诗词标题
	List<ShowPoetry> readShowPoetryById(Integer id);

	int updatePraise(Integer id, Integer praise);

	

}
