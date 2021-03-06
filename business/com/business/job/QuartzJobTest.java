
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.business.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.business.service.BaseService;
import com.kernal.annotation.QuartzJob;
import com.kernal.utils.DateUtil;
import com.kernal.utils.WebUtil;


 /**
 * 类名称：     QuartzJobTest
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-2-21 上午11:00:16
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="测试")
public class QuartzJobTest implements Job {

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
        //使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
	}

}
