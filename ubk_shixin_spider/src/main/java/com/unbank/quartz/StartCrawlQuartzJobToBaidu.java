package com.unbank.quartz;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unbank.spider.ShixinAllSpider;




@Component
public class StartCrawlQuartzJobToBaidu {
	
	private static Log logger = LogFactory
			.getLog(StartCrawlQuartzJobToBaidu.class);

	@Autowired
	ShixinAllSpider shixinAllSpider;

	/**
	 * 定时启动任务
	 */
	public void executeInternal() {
		try {
			shixinAllSpider.shixinGetbybaidu();
		} catch (Exception e) {
			logger.error("失信人员启动采集定时任务出错", e);
		}
	}

}
