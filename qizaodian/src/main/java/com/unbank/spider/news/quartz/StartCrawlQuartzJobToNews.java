package com.unbank.spider.news.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unbank.spider.news.task.NewsSpider;


@Component
public class StartCrawlQuartzJobToNews {
	
	private static Log logger = LogFactory
			.getLog(StartCrawlQuartzJobToNews.class);

	@Autowired
	NewsSpider newsSpider;

	/**
	 * 定时启动任务
	 */
	public void executeInternal() {
		try {
			newsSpider.spider();
		} catch (Exception e) {
			logger.error("采集定时任务出错", e);
		}
	}

}
