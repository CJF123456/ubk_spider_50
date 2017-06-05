package com.unbank.process;

import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class Task extends TimerTask {

	LinkedBlockingQueue<Object> resoucreQueue;
	static int num = 0;

	public Task(LinkedBlockingQueue<Object> resoucreQueue) {
		this.resoucreQueue = resoucreQueue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (resoucreQueue.size() == 0) {
			num++;
		} else {
			num = 0;
		}
		if (num >= 10) {
			System.out.println("已经检测完成了=================================");
		}
	}

}
