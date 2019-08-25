

package com.geekutil.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.ScheduleJobLog;
import com.geekutil.modules.sys.service.ScheduleJobService;
import com.geekutil.modules.sys.service.ScheduleJobLogService;
import javafx.scene.control.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.geekutil.common.util.HttpUtils.pageSize;
import static com.geekutil.common.util.PageUtils.pageResult;

/**
 * 定时任务
 * @author Asens
 *
 */
@RestController
@RequestMapping("/api/sys/schedule/log")
public class ScheduleJobLogController {

	@Resource
    private ScheduleJobLogService scheduleJobLogService;
    @Resource
    private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	public Object list(@RequestParam(required = false, defaultValue = "1") Integer pageNo, Integer pageSize,
					   Long jobId, @DateTimeFormat(pattern = "yyyy-MM-dd") Date createDateStart,
					   @DateTimeFormat(pattern = "yyyy-MM-dd") Date createDateEnd,Integer times,
					   String remark, String beanName, String methodName, Integer status){
		IPage<ScheduleJobLog> page = scheduleJobLogService
				.lambdaQuery()
				.like(StringUtils.isNotBlank(remark),ScheduleJobLog::getRemark,remark)
				.eq(jobId!=null,ScheduleJobLog::getJobId,jobId)
				.eq(StringUtils.isNotBlank(beanName),ScheduleJobLog::getBeanName,beanName)
				.eq(StringUtils.isNotBlank(methodName),ScheduleJobLog::getMethodName,methodName)
				.gt(createDateStart!=null,ScheduleJobLog::getCreateDate,createDateStart)
				.lt(createDateEnd!=null,ScheduleJobLog::getCreateDate,createDateEnd)
				.gt(times!=null,ScheduleJobLog::getTimes,times)
				.eq(status!=null,ScheduleJobLog::getStatus,status)
				.orderByDesc(ScheduleJobLog::getId)
				.page(new Page<>(pageNo,pageSize(pageSize)));
		return Result.success().data(pageResult(page));
	}


	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info")
	public Object info(@RequestParam Long id){
	    return Result.success().data(scheduleJobLogService.getById(id));
	}

	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("")
	public Object logs(@RequestParam Long jobId){
		List<ScheduleJobLog> logs = scheduleJobLogService.lambdaQuery()
				.eq(ScheduleJobLog::getJobId,jobId)
				.orderByDesc(ScheduleJobLog::getId)
				.last("limit 10")
				.list();
		return Result.success().data(logs);
	}


}
