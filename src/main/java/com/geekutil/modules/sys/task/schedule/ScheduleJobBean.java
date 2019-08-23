package com.geekutil.modules.sys.task.schedule;

import com.geekutil.common.util.SpringContextHolder;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.ScheduleJobLog;
import com.geekutil.modules.sys.service.ScheduleJobService;
import com.geekutil.modules.sys.service.ScheduleJobLogService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.concurrent.*;

import static com.geekutil.Const.DATABASE_INTEGER_NO;
import static com.geekutil.Const.DATABASE_INTEGER_YES;

/**
 * 定时任务
 * @author Asens
 */
@Log4j2
public class ScheduleJobBean extends QuartzJobBean {

	@Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Object object =context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJobService scheduleJobService = SpringContextHolder.getBean(ScheduleJobService.class);
        ScheduleJob scheduleJob=scheduleJobService.getById(Long.valueOf(object.toString()));
        ScheduleJobLogService scheduleJobLogService = SpringContextHolder.getBean(ScheduleJobLogService.class);

		ScheduleJobLog jobLog = new ScheduleJobLog();
        jobLog.setJobId(scheduleJob.getId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateDate(new Date());
        
        long startTime = System.currentTimeMillis();
        
        try {
            log.warn("任务[{}]准备执行，任务ID：[{}],任务：{}[{}]" ,scheduleJob.getRemark(),scheduleJob.getId(),
                    scheduleJob.getBeanName(),scheduleJob.getMethodName());

            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
            		scheduleJob.getMethodName(), scheduleJob.getParams());
			try {
				task.run();
				jobLog.setStatus(DATABASE_INTEGER_YES);
			}catch (Throwable t){
				jobLog.setError(StringUtils.substring(t.toString(), 0, 2000));
				jobLog.setStatus(DATABASE_INTEGER_NO);
			}
			
			long times = System.currentTimeMillis() - startTime;
			jobLog.setTimes((int)times);
			jobLog.setEndDate(new Date());

            log.warn("任务[{}]执行完毕，任务ID：[{}],任务：{}[{}],错误:{}" ,scheduleJob.getRemark(),scheduleJob.getId(),
                    scheduleJob.getBeanName(),scheduleJob.getMethodName(),jobLog.getError());
		} catch (Exception e) {
            log.warn("任务[{}]执行失败，任务ID：[{}],任务：{}[{}],错误:{}" ,scheduleJob.getRemark(),scheduleJob.getId(),
                    scheduleJob.getBeanName(),scheduleJob.getMethodName(),e.toString());
			
			long times = System.currentTimeMillis() - startTime;
			jobLog.setTimes((int)times);
			jobLog.setEndDate(new Date());
			jobLog.setStatus(DATABASE_INTEGER_NO);
			jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(jobLog);
		}
    }
}
