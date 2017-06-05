package com.unbank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.unbank.checkjob.NoCrawlReader;
import com.unbank.process.InformationConsume;
import com.unbank.process.ResouceConsume;

public class Spider {

	
	private static Log logger = LogFactory.getLog(Spider.class);

	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(Spider.class.getClassLoader()
					.getResource("").toURI().getPath()
					+ "log4j.properties");
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		LinkedBlockingQueue<Object> informationQueue = new LinkedBlockingQueue<Object>();
		LinkedBlockingQueue<Object> resoucreQueue = new LinkedBlockingQueue<Object>();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 512; i++) {
			executor.execute(new InformationConsume(informationQueue,
					resoucreQueue));
		}
		executor.execute(new ResouceConsume(resoucreQueue));
		executor.shutdown();
		String checkTime = "2017-05-14";
		new NoCrawlReader(informationQueue).getNoCrawlWebsite(checkTime);

	}

}
