package com.unbank.quartz;

import com.unbank.quartz.task.QuartzTask;

public class StartExpendQuartzJobBean {

	public String trees;

	public String datas;

	public StartExpendQuartzJobBean(String trees, String datas) {
		super();
		this.trees = trees;
		this.datas = datas;
	}

	public void executeInternal() {
		new QuartzTask().dotask(trees, datas);
	}
}
