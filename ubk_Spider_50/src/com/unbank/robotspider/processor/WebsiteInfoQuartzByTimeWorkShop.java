package com.unbank.robotspider.processor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.robotspider.entity.WebSiteInfo;
import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.store.WebsiteParserReader;
import com.unbank.robotspider.worker.WebSiteInfoProductor;

public class WebsiteInfoQuartzByTimeWorkShop extends BaseWorkShop implements
		Runnable {
	protected LinkedBlockingQueue<Object> webSiteQueue;
	protected Integer task;
	private Map<Integer, WebsiteParser> websitepaserlist;

	public WebsiteInfoQuartzByTimeWorkShop(
			LinkedBlockingQueue<Object> webSiteQueue, Integer task,
			Map<Integer, WebsiteParser> websitepaserlist) {
		this.webSiteQueue = webSiteQueue;
		this.task = task;
		this.websitepaserlist = websitepaserlist;
	}

	@Override
	public void run() {
		try {
			fillQueue();
		} catch (Exception e) {
			logger.info("", e);
		}
	}

	private void fillQueue() {
		System.out.println("sdasdfa");
		List<WebSiteInfo> webSites = new WebSiteInfoProductor()
				.getWebSiteInfoByTask(task);
		for (WebSiteInfo webSiteInfo : webSites) {
			try {
				if (webSiteInfo.getWebsiteId() > 5744) {
					WebsiteParser websiteParser = new WebsiteParserReader()
							.readWebsitePaserById(webSiteInfo.getWebsiteId());
					websitepaserlist.put(webSiteInfo.getWebsiteId(),
							websiteParser);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			} finally {
				put(webSiteQueue, webSiteInfo);
			}
		}
		webSites.clear();
	}

}
