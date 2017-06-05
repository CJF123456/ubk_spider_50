package com.unbank.checkjob;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.ibatis.session.SqlSession;

import com.unbank.mybatis.entity.PtfDayNumWeb;
import com.unbank.mybatis.entity.PtfDayNumWebExample;
import com.unbank.mybatis.entity.WebSiteInfo;
import com.unbank.mybatis.entity.WebSiteInfoExample;
import com.unbank.mybatis.factory.DynamicConnectionFactory;
import com.unbank.mybatis.mapper.PtfDayNumWebMapper;
import com.unbank.mybatis.mapper.WebSiteInfoMapper;

public class NoCrawlReader {
	LinkedBlockingQueue<Object> informationQueue;

	public NoCrawlReader(LinkedBlockingQueue<Object> informationQueue) {
		this.informationQueue = informationQueue;
	}

	/**
	 * 采集有问题的websiteid
	 * 
	 * @param checkTime
	 * @return
	 */
	public List<WebSiteInfo> getNoCrawlWebsite(String checkTime) {

		List<WebSiteInfo> websites = readerWebsiteInfo();
		Set<Integer> websiteids = new HashSet<Integer>();
		List<PtfDayNumWeb> PtfDayNumWebs = readerNoCrawlIds(checkTime);
		for (PtfDayNumWeb ptfDayNumWeb : PtfDayNumWebs) {
			websiteids.add(ptfDayNumWeb.getWebsiteId());

		}
		for (WebSiteInfo webSiteInfo : websites) {
			if (websiteids.contains(webSiteInfo.getWebsiteId())) {
				continue;
			} else {
				informationQueue.add(webSiteInfo);
			}

		}
		return websites;

	}

	private List<WebSiteInfo> readerWebsiteInfo() {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<WebSiteInfo> webSiteInfos = null;
		try {

			WebSiteInfoMapper webSiteInfoMapper = sqlSession
					.getMapper(WebSiteInfoMapper.class);

			WebSiteInfoExample webSiteInfoExample = new WebSiteInfoExample();
			webSiteInfoExample.or().andWebsiteIdGreaterThan(5744)
					.andIstaskEqualTo(2);

			webSiteInfos = webSiteInfoMapper
					.selectByExample(webSiteInfoExample);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return webSiteInfos;
	}

	private List<PtfDayNumWeb> readerNoCrawlIds(String checkTime) {
		SqlSession sqlSession = DynamicConnectionFactory
				.getInstanceSessionFactory("development").openSession();
		List<PtfDayNumWeb> ptfDayNumWebs = null;
		try {
			PtfDayNumWebMapper ptfDayNumWebMapper = sqlSession
					.getMapper(PtfDayNumWebMapper.class);

			PtfDayNumWebExample ptfDayNumWebExample = new PtfDayNumWebExample();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
			java.util.Date date = sdf.parse(checkTime);
			ptfDayNumWebExample.or().andCrawlTimeEqualTo(date)
					.andNumIdGreaterThan(5744);

			ptfDayNumWebs = ptfDayNumWebMapper
					.selectByExample(ptfDayNumWebExample);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return ptfDayNumWebs;
	}


}
