package com.unbank.process;

import java.util.concurrent.LinkedBlockingQueue;

import com.unbank.checkjob.CheckNoCrawlSource;
import com.unbank.mybatis.entity.WebSiteInfo;

public class InformationConsume implements Runnable {
	LinkedBlockingQueue<Object> informationQueue;
	LinkedBlockingQueue<Object> resoucreQueue;

	public InformationConsume(LinkedBlockingQueue<Object> informationQueue,
			LinkedBlockingQueue<Object> resoucreQueue) {
		this.informationQueue = informationQueue;
		this.resoucreQueue = resoucreQueue;
	}

	public void run() {
		while (true) {
			try {
				if (informationQueue.size() > 0) {
					WebSiteInfo information = null;
					information = (WebSiteInfo) informationQueue.take();
					if (information != null) {
						checkNoCrawlReson(information);
					}
				}
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}

	}

	private void checkNoCrawlReson(WebSiteInfo webSiteInfo) {
		String reson = new CheckNoCrawlSource().checkUrl(
				webSiteInfo.getUrlHome(), webSiteInfo.getWebsiteId());
		webSiteInfo.setMsg(reson);
		try {
			resoucreQueue.put(webSiteInfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
