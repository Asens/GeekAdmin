package com.geekutil.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekutil.Const;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.dto.ScheduleJobDTO;
import com.geekutil.modules.sys.service.ScheduleJobService;
import com.geekutil.modules.sys.task.schedule.ScheduleConfig;
import com.geekutil.modules.sys.mapper.ScheduleJobMapper;
import com.geekutil.modules.sys.task.schedule.ScheduleUtil;
import org.quartz.Scheduler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.*;

import static com.geekutil.Const.DATABASE_INTEGER_NO;

/**
 * @author Asens
 */


@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
	@Resource
    private Scheduler scheduler;

	@Override
    public void deleteBatch(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtil.deleteScheduleJob(scheduler, jobId);
    	}
    	removeByIds(Arrays.asList(jobIds));
	}

	@Override
    public void run(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtil.run(scheduler, getById(jobId));
    	}
    }

	@Override
    public void pause(Long[] jobIds) {
        for(Long jobId : jobIds){
    		ScheduleUtil.pauseJob(scheduler, jobId);
    	}
    	updateBatch(jobIds, ScheduleConfig.ScheduleStatus.PAUSE.getValue());
    }

	private void updateBatch(Long[] jobIds, int value) {
		Collection<ScheduleJob> list = listByIds(Arrays.asList(jobIds));
		for(ScheduleJob scheduleJob:list){
			scheduleJob.setStatus(value);
		}
		updateBatchById(list);
	}

	@Override
    public void resume(Long[] jobIds) {
    	for(Long jobId : jobIds){
    		ScheduleUtil.resumeJob(scheduler, jobId);
    	}
    	updateBatch(jobIds, ScheduleConfig.ScheduleStatus.NORMAL.getValue());
    }

	@Override
	public void saveOrUpdateUser(ScheduleJobDTO scheduleJobDTO) {
		if(scheduleJobDTO.getId() == null){
			ScheduleJob scheduleJob = new ScheduleJob();
			BeanUtils.copyProperties(scheduleJobDTO,scheduleJob);
			scheduleJob.setCreateDate(new Date());
			save(scheduleJob);
			return;
		}
		ScheduleJob scheduleJob = getById(scheduleJobDTO.getId());
		BeanUtils.copyProperties(scheduleJobDTO,scheduleJob,"id","createDate");
		updateById(scheduleJob);
	}
}
