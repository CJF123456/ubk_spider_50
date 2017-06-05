package com.unbank.robotspider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.entity.WebsiteParser;
import com.unbank.robotspider.processor.ArticleWorkShop;
import com.unbank.robotspider.processor.NewsListWorkShop;
import com.unbank.robotspider.processor.StoreWorkShop;
import com.unbank.robotspider.processor.WebPageWorkShop;
import com.unbank.robotspider.processor.WebsiteInfoQuartzByTimeWorkShop;

public class UnbankQuartzByTimeCrawler implements UnbankCrawlerMBean {

	private static Log logger = LogFactory.getLog(UnbankQuartzByTimeCrawler.class);
	public Integer task;

	LinkedBlockingQueue<Object> webSiteInfoQueue = null;
	LinkedBlockingQueue<Object> newsListQueue = null;
	LinkedBlockingQueue<Object> newsPageQueue = null;
	LinkedBlockingQueue<Object> articleInfoQueue = null;
	public static Map<Integer, WebsiteParser> websitepaserlist = null;

	public void init() {
		System.out.println("++++++++++++++");
		webSiteInfoQueue = new LinkedBlockingQueue<Object>();

		newsListQueue = new LinkedBlockingQueue<Object>();

		newsPageQueue = new LinkedBlockingQueue<Object>();

		articleInfoQueue = new LinkedBlockingQueue<Object>();
		websitepaserlist = new HashMap<Integer, WebsiteParser>();
		ExecutorService executor = Executors.newCachedThreadPool();
		// 32 2
		for (int i = 0; i < 2; i++) {
			executor.execute(new NewsListWorkShop(webSiteInfoQueue, newsListQueue));
		}
		// 128 2
		for (int i = 0; i < 2; i++) {
			executor.execute(new WebPageWorkShop(newsListQueue, newsPageQueue));
		}
		// 32 2
		for (int i = 0; i < 2; i++) {
			executor.execute(new ArticleWorkShop(newsPageQueue, articleInfoQueue));
		}
		// 8 2
		for (int i = 0; i < 2; i++) {
			executor.execute(new StoreWorkShop(articleInfoQueue));
		}
		executor.shutdown();
	}

	public void start() {
		new WebsiteInfoQuartzByTimeWorkShop(webSiteInfoQueue, task, websitepaserlist).run();
	}

	@Override
	public int getWebSiteInfoQueueSize() {
		return webSiteInfoQueue.size();
	}

	@Override
	public int getNewsListQueueSize() {
		return newsListQueue.size();
	}

	@Override
	public int getNewsPageQueueSize() {
		return newsPageQueue.size();
	}

	@Override
	public int getArticleInfoQueueSize() {
		return articleInfoQueue.size();
	}

	public Integer getTask() {
		return task;
	}

	public void setTask(Integer task) {
		this.task = task;
	}

}
