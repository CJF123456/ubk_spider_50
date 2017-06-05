package com.unbank.task;

import org.apache.log4j.PropertyConfigurator;

public class StatsHgydSpider extends BaseSpider {

	static {
		// 启动日志
		try {
			PropertyConfigurator.configure(StatsHgydSpider.class
					.getClassLoader().getResource("").toURI().getPath()
					+ "log4j.properties");
			logger.info("---日志系统启动成功---");
		} catch (Exception e) {
			logger.error("日志系统启动失败:", e);
		}
	}

	public static void main(String[] args) {
		update = true;
		tablePre = "hgyd";
		tableTreeName = "country_datafield_month";
		tableDataName = "country_month_data";
		frequency = 3;

		new StatsHgydSpider().getQuotasTree();
	}

}
