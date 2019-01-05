package com.cdsoftware.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdsoftware.activity.service.CdActivityServiceI;

public class ShangkeAccountTokentTask implements Job {
	
	@Autowired
	CdActivityServiceI cdActivityService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		run();
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		org.jeecgframework.core.util.LogUtil.info("===================重置商客Token任务开始===================");
		try {			
			cdActivityService.resetToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		org.jeecgframework.core.util.LogUtil.info("===================重置商客Token定时任务结束===================");
		long end = System.currentTimeMillis();
		long times = end - start;
		org.jeecgframework.core.util.LogUtil.info("总耗时"+times+"毫秒");
	}


}
