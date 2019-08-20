

package com.geekutil.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.ScheduleJobLog;
import com.geekutil.modules.sys.service.ScheduleJobService;
import com.geekutil.modules.sys.service.ScheduleJobLogService;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.HashMap;
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
	public Object list(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
					   Long jobId, String start, String end, Integer status){
		Map<String,Object> map = new HashMap<>();
		map.put("jobId",jobId);
		map.put("start",start);
		map.put("end",end);
		map.put("status",status);
		IPage<ScheduleJobLog> page = scheduleJobLogService
				.page(pageNo,pageSize(),map);
		return Result.success().data(pageResult(page));
	}


	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info")
	public Object info(@RequestParam Long id){
	    return Result.success().data(scheduleJobLogService.getById(id));
	}


}
