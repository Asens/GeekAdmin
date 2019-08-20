package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.ScheduleJobLog;

import java.util.Map;

/**
 * 定时任务日志
 * @author Asens
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLog> {

	/**
	 * 定时任务日志分页
	 * @param pageNo 页码
	 * @param pageSize 一页数量
	 * @param paraMap 参数map
	 * @return 日志分页
	 */
	IPage<ScheduleJobLog> page(Integer pageNo, int pageSize, Map<String, Object> paraMap);
}
