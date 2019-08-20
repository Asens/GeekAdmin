package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.dto.ScheduleJobDTO;

/**
 * 定时任务
 * @author Asens
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

	/**
	 * 批量删除定时任务
	 * @param jobIds 待删除任务集合
	 */
	void deleteBatch(Long[] jobIds);

	/**
	 * 立即执行
	 * @param jobIds 任务集合
	 */
	void run(Long[] jobIds);

	/**
	 * 暂停运行
	 * @param jobIds 任务集合
	 */
	void pause(Long[] jobIds);

	/**
	 * 恢复运行
	 * @param jobIds 任务集合
	 */
	void resume(Long[] jobIds);

	/**
	 * 保存或更新
	 * @param scheduleJobDTO 传入参数
	 */
	void saveOrUpdateUser(ScheduleJobDTO scheduleJobDTO);
}
