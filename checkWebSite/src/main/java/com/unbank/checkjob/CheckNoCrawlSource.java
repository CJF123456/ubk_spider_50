package com.unbank.checkjob;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.fetch.Fetcher;
import com.unbank.mybatis.entity.ArticleInfo;
import com.unbank.mybatis.entity.ArticleInfoExample;
import com.unbank.mybatis.entity.WebSiteInfo;
import com.unbank.mybatis.entity.WebSiteInfoExample;
import com.unbank.mybatis.entity.WebsitePaser;
import com.unbank.mybatis.entity.WebsitePaserExample;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.ArticleInfoMapper;
import com.unbank.mybatis.mapper.WebSiteInfoMapper;
import com.unbank.mybatis.mapper.WebsitePaserMapper;

public class CheckNoCrawlSource {

	private static Log logger = LogFactory.getLog(CheckNoCrawlSource.class);

	public String checkUrl(String url, int webisteid) {
		if (webisteid > 5744) {
			// 首先检查是否能够打开
			// WebSiteInfo webSiteInfo = getWebsiteInfoByWebisteid(webisteid);
			// url = webSiteInfo.getUrlHome();
			Fetcher fetcher = Fetcher.getInstance();
			String html = fetcher.getHtmlWithCookie(url);
			if (html == null || html.endsWith("异常")) {
				return "网址读取异常";
			}

			Document document = Jsoup.parse(html, url);
			// 监测列表页是否能够获得

			WebsitePaser websiteParser = getWebsitePaserByWebisteid(webisteid);
			String listPath = websiteParser.getListPath();
			boolean isexit = isExitElement(document, listPath);
			if (!isexit) {
				return "列表页规则出错" + "@@@" + listPath;
			}
			Elements listElements = document.select(listPath);
			if (listElements.size() < 5) {
				return "列表页规则出错,a链接小于5" + "@@@" + listPath;
			}
			// for (Element element : listElements) {
			Element element = listElements.get((int) Math.random() * 5);
			String contenturl = element.absUrl("href");
			String contenthtml = fetcher.getHtmlWithCookie(contenturl);
			if (contenthtml == null || contenthtml.endsWith("异常")) {
				return "新闻页网络异常";
			}
			Document contentDocument = Jsoup.parse(contenthtml, contenturl);
			// System.out.println(contentDocument);
			String newscontentPath = websiteParser.getNewscontentPath();
			isexit = isExitElement(contentDocument, newscontentPath);
			if (!isexit) {
				return "内容页规则有错" + "@@@" + newscontentPath;
			}
			String content = contentDocument.select(newscontentPath).first()
					.toString();
			if (isTextShort(contenthtml, 100)) {
				return "获取新闻内容少于100字" + "@@@" + newscontentPath;
			}
			if (isTextMessyCode(content)) {
				return "获取到的内容有乱码" + "@@@" + content;
			}

			String titlePath = websiteParser.getNewstitlePath();
			// if (titlePath.equals("title")) {
			// return "标题规则出错，是title";
			// }
			isexit = isExitElement(contentDocument, titlePath);
			if (!isexit) {
				return "标题规则出错" + "@@@" + titlePath;
			}
			String title = contentDocument.select(titlePath).first().toString();
			if (isTextShort(title, 5)) {
				return "获取新闻标题少于5个字";
			}
			if (isTextMessyCode(title)) {
				return "获取到的标题有乱码" + "@@@" + title;
			}

			int websiteid = isCrawlUrl(contenturl);
			if (websiteid != 0) {
				if (websiteid == webisteid) {
					return "原网址最近没有更新";
				}
				return "有相同的板块网址存在   板块编号" + websiteid;
			}
			// 都对了，可能是时间提取有问题
			if (!new NewsDatePaser().analyzeWebDate(contentDocument)) {
				return "最近一周没有更新";
			}

		} else {
			return "网址Id小不支持的校验";
		}
		return "没有查出原因";

	}

	private WebSiteInfo getWebsiteInfoByWebisteid(int webisteid) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		WebSiteInfo webSiteInfo = null;
		try {
			WebSiteInfoMapper webSiteInfoMapper = sqlSession
					.getMapper(WebSiteInfoMapper.class);
			WebSiteInfoExample example = new WebSiteInfoExample();
			example.or().andWebsiteIdEqualTo(webisteid);
			webSiteInfo = webSiteInfoMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return webSiteInfo;
	}

	public boolean isTextShort(String html, int num) {
		Document document = Jsoup.parse(html);
		String text = document.text();
		if (text.trim().length() < num) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isTextMessyCode(String html) {
		Document document = Jsoup.parse(html);
		String text = document.text();
		return checkMessyCode(text);
	}

	public boolean checkMessyCode(String source) {
		if (source.contains("�") || source.contains("熶")
				|| source.contains("銆") || source.contains("為")
				|| source.contains("鹫") || source.contains("")
				|| source.contains("???")) {
			return true;
		}
		return false;

	}

	private int isCrawlUrl(String contenturl) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		int num = 0;
		try {
			ArticleInfoMapper articleInfoMapper = sqlSession
					.getMapper(ArticleInfoMapper.class);
			ArticleInfoExample articleInfoExample = new ArticleInfoExample();
			articleInfoExample.or().andUrlEqualTo(contenturl);
			List<ArticleInfo> crawls = articleInfoMapper
					.selectByExample(articleInfoExample);
			if (crawls.size() > 0) {
				num = crawls.get(0).getWebsiteId();
			}
		} catch (Exception e) {
			// TODO: handle exceptione
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return num;

	}

	private boolean isExitElement(Document document, String cssPath) {
		if (cssPath == null || cssPath.trim().isEmpty()) {
			return false;
		}
		Elements listElements = document.select(cssPath);
		if (listElements.size() == 0) {
			return false;
		}
		return true;
	}

	private WebsitePaser getWebsitePaserByWebisteid(Integer webisteid) {

		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		WebsitePaser websiteParser = null;
		try {
			WebsitePaserMapper websitePaserMapper = sqlSession
					.getMapper(WebsitePaserMapper.class);
			WebsitePaserExample example = new WebsitePaserExample();
			example.or().andWebsiteIdEqualTo(webisteid);
			websiteParser = websitePaserMapper.selectByExample(example).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return websiteParser;
	}

}
