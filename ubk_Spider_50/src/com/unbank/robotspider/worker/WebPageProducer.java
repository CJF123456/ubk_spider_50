package com.unbank.robotspider.worker;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.unbank.robotspider.entity.NewsInfoMiddleWare;
import com.unbank.robotspider.filter.url.URLBaseFilter;
import com.unbank.robotspider.parser.WebPagePaser;

public class WebPageProducer {
	public static Log logger = LogFactory.getLog(WebPageProducer.class);

	public NewsInfoMiddleWare fillNewsPageQueue(
			NewsInfoMiddleWare newsInfoMiddleWare) {

		boolean success = newsInfoMiddleWareURLFilter(newsInfoMiddleWare
				.getUrl());
		if (success) {
			return new WebPagePaser().paser(newsInfoMiddleWare);
		} else {
			// logger.info("已经存在相同的网址" + newsInfoMiddleWare.getWebsiteId()
			// + "=========" + newsInfoMiddleWare.getUrl());
			return null;
		}
	}

	private boolean newsInfoMiddleWareURLFilter(String url) {
		URLBaseFilter urlBaseFilter = new URLBaseFilter();
		return urlBaseFilter.checkNewsURL(url);
	}

}
