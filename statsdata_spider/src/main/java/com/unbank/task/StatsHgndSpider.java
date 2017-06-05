package com.unbank.task;

import org.apache.log4j.PropertyConfigurator;

public class StatsHgndSpider extends BaseSpider {

	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(StatsHgndSpider.class
					.getClassLoader().getResource("").toURI().getPath()
					+ "log4j.properties");
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		update = true;
		tablePre = "hgnd";
		tableTreeName = "country_datafield_year";
		tableDataName = "country_year_data";
		frequency = 1;
		new StatsHgndSpider().getQuotasTree();
	}

}
