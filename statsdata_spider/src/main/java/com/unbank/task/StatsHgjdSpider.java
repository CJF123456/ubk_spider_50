package com.unbank.task;

import org.apache.log4j.PropertyConfigurator;

public class StatsHgjdSpider extends BaseSpider {

	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(StatsHgjdSpider.class
					.getClassLoader().getResource("").toURI().getPath()
					+ "log4j.properties");
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		update = true;
		tablePre = "hgjd";
		tableTreeName = "country_datafield_quarter";
		tableDataName = "country_quarter_data";
		frequency = 2;
		new StatsHgjdSpider().getQuotasTree();
	}

}
