package com.unbank.spider.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unbank.spider.common.CommonController;
import com.unbank.spider.mybatis.vo.ShowPoetry;
import com.unbank.spider.mybatis.vo.ShowPoetryText;
import com.unbank.spider.mybatis.vo.ShowTag;
import com.unbank.spider.mybatis.vo.ShowText;
import com.unbank.spider.mybatis.vo.ShowTitle;
import com.unbank.spider.mybatis.vo.Weixin;
import com.unbank.spider.mybatis.vo.WinxinNumber;
import com.unbank.spider.service.ShowService;

@Controller
@RequestMapping(value = "/")
public class ShowController extends CommonController {

	@Autowired
	ShowService service;

	// 所有列表
	@RequestMapping(value = "readShowTitles")
	public String readShowTitles(HttpServletResponse response, HttpSession session, Model model, Integer pageNo) {
		int pageSize = 10;
		if (null == pageNo || pageNo == 0) {
			pageNo = 1;
		}
		Integer count = service.getTitlesCount();
		Integer pageCount = ((count % pageSize == 0) ? (count / pageSize) : ((count / pageSize) + 1));
		List<ShowTitle> showtitles = service.readShowTitles(pageNo, pageSize);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("pageSize", pageSize);
		map.put("pageNo", pageNo);
		map.put("pageCount", pageCount);
		model.addAttribute("page", map);
		model.addAttribute("newsList", showtitles);
		List<ShowTag> shows = service.readerShowTagsbyconuts();
		Map<Integer, List<ShowTag>> tagmap = new HashMap<Integer, List<ShowTag>>();
		for (ShowTitle title : showtitles) {
			String[] ids = title.getTid().split(",");
			List<ShowTag> tags = new ArrayList<ShowTag>();
			for (String str : ids) {
				for (ShowTag tag : shows) {
					if (Integer.parseInt(str) == tag.getId()) {
						tags.add(tag);
					}
				}
			}
			tagmap.put(title.getId(), tags);
		}
		model.addAttribute("single",1);
		model.addAttribute("righttag", shows);
		model.addAttribute("tags", tagmap);
		return "/index";
	}

	// 通过ID查询标题
	@RequestMapping(value = "readShowTitleById")
	public String readShowTitleById(Integer id, Model model) {
		List<ShowTitle> showtitles = service.readShowTitleById(id);
		model.addAttribute("newsList", showtitles);
	
		return "/index";
	}

	// 通过Tid查询标题 标签查标题
	@RequestMapping(value = "readShowTitleByTid")
	public String readShowTitleByTid(String tid, Model model) {
//		long start = System.currentTimeMillis();
		List<ShowTitle> showtitles = service.readShowTitleByTid(tid);
		List<ShowTag> shows = service.readerShowTagsbyconuts();
		model.addAttribute("newsList", showtitles);
		model.addAttribute("righttag", shows);
//		long end = System.currentTimeMillis();
//		System.out.println("====================="+(end-start)+"MS");
		return "/index";
	}

	// 通过ID查询内容
	@RequestMapping(value = "readerShowTextById")
	public String readerShowTextById(HttpServletResponse response, HttpSession session, Integer id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<ShowTitle> showtitles = service.readShowTitleById(id);
		List<ShowText> shows = service.readerShowTextById(id);
		List<ShowTag> showtag = service.readerShowTagsbyconuts();

		String title = showtitles.get(0).getTitle();
		String picurl = showtitles.get(0).getPicurl();
		Date pubdate = showtitles.get(0).getPubdate();
		String text = shows.get(0).getText();
		map.put("title", title);
		map.put("picurl", picurl);
		map.put("pubdate", pubdate);
		map.put("text", text);
		model.addAttribute("title", title);
		model.addAttribute("picurl", picurl);
		model.addAttribute("pubdate", pubdate);
		model.addAttribute("text", text);
		model.addAttribute("righttag", showtag);
		return "/showdetail";
	}

	// 通过ID查询标签
	@RequestMapping(value = "readerShowTagById")
	public String readerShowTagById(HttpServletResponse response, HttpSession session, Integer id, Model model) {
//		long start = System.currentTimeMillis();
		List<ShowTag> shows = service.readerShowTagById(id);
		model.addAttribute("newsList1", shows);
//		long end = System.currentTimeMillis();
//		System.out.println("====================="+(end-start)+"MS");
		return "/index";
	}

	// 全部标签
	@RequestMapping(value = "readerShowTags")
	public String readerShowTags(HttpServletResponse response, HttpSession session, Model model) {
		List<ShowTag> shows = service.readerShowTags();
		model.addAttribute("tag", shows);
		return "/tag";
	}

	// 只获取前15条标签
	@RequestMapping(value = "readerShowTagsbyconuts")
	public String readerShowTagsbyconuts(HttpServletResponse response, HttpSession session, Model model) {
		List<ShowTag> shows = service.readerShowTagsbyconuts();
		model.addAttribute("righttag", shows);
		return "/righttag";
	}

	// 微信数据 文章数据
	@RequestMapping(value = "readerWinxinByKeyword")
	public void readerWinxinByKeyword(HttpServletResponse response, HttpSession session, Model model, String keyword) {
		List<Weixin> weixins = service.readerWinxinByKeyword(keyword);
		JSONArray array = JSONArray.fromObject(weixins);
		try {
			response.getWriter().write(array.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	// 微信公众号
	@RequestMapping(value = "readerWinxinNumberByKeyword")
	public void readerWinxinNumberByKeyword(HttpServletResponse response, HttpSession session, Model model,
			String keyword) {
		List<WinxinNumber> winxinNumbers = service.readerWinxinNumberByKeyword(keyword);
		JSONArray array = JSONArray.fromObject(winxinNumbers);
		try {
			response.getWriter().write(array.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	// 诗词
	@RequestMapping(value = "readerPoetrys")
	public String readerPoetrys(HttpServletResponse response, HttpSession session, Model model) {
		List<ShowPoetry> shows = service.readerPoetrys();
		model.addAttribute("poetry", shows);
		return "/poetry";
	}

	// 只获取前15条诗词
	@RequestMapping(value = "readerShowPoetryByconuts")
	public String readerShowPoetryByconuts(HttpServletResponse response, HttpSession session, Model model) {
		List<ShowPoetry> shows = service.readerPoetrysbyconuts();
		model.addAttribute("rightpoetry", shows);
		return "/rightpoetry";
	}

	// 通过ID查询标题
	@RequestMapping(value = "readShowPoetryById")
	public String readShowPoetryById(Integer id, Model model) {
		List<ShowPoetry> showpoetrys = service.readShowPoetryById(id);
		model.addAttribute("newsList", showpoetrys);
		return "/index";
	}

	// 通过ID查询内容
	@RequestMapping(value = "readerShowPoetryTextById")
	public String readerShowPoetryTextById(HttpServletResponse response, HttpSession session, Integer id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ShowPoetry> showpoetrys = service.readShowPoetryById(id);
		List<ShowPoetryText> showpoetrytexts = service.readShowPoetryTextById(id);
		String title = showpoetrys.get(0).getTitle();
		Date newstime = showpoetrys.get(0).getNewstime();
		String author = showpoetrys.get(0).getAuthor();
		String text = showpoetrytexts.get(0).getText();
		String brief = showpoetrys.get(0).getBrief();
		String source=showpoetrys.get(0).getSource();
		String url=showpoetrys.get(0).getRe();
		map.put("brief", brief);
		map.put("source", source);
		map.put("title", title);
		map.put("newstime", newstime);
		map.put("author", author);
		map.put("url", url);
		map.put("text", text);
		model.addAttribute("title", title);
		model.addAttribute("author", author);
		model.addAttribute("newstime", newstime);
		model.addAttribute("text", text);
		model.addAttribute("source", source);
		model.addAttribute("url", url);
		return "/poetrydetail";
	}

}
