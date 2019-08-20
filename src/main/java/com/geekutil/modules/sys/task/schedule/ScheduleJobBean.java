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

/**
 * 定时任务
 * @author Asens
 */
@Log4j2
public class ScheduleJobBean extends QuartzJobBean {

	private ExecutorService service = new ThreadPoolExecutor(
			1,5,10, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(1),
			new ThreadPoolExecutor.DiscardOldestPolicy());

	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Object object =context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
        ScheduleJobService scheduleJobService = SpringContextHolder.getBean(ScheduleJobService.class);
        ScheduleJob scheduleJob=scheduleJobService.getById(Long.valueOf(object.toString()));
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = SpringContextHolder.getBean(ScheduleJobLogService.class);

		//数据库保存执行记录
        ScheduleJobLog jobLog = new ScheduleJobLog();
        jobLog.setJobId(scheduleJob.getId());
        jobLog.setBeanName(scheduleJob.getBeanName());
        jobLog.setMethodName(scheduleJob.getMethodName());
        jobLog.setParams(scheduleJob.getParams());
        jobLog.setCreateDate(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	log.warn("任务[{}]准备执行，任务ID：[{}],任务：{}[{}]," ,scheduleJob.getRemark(),scheduleJob.getId(),
                    scheduleJob.getBeanName(),scheduleJob.getMethodName());

            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			jobLog.setTimes((int)times);
			jobLog.setEndDate(new Date());
			//任务状态    0：成功    1：失败
			jobLog.setStatus(0);
            log.warn("任务[{}]执行完毕，任务ID：[{}],任务：{}[{}]," ,scheduleJob.getRemark(),scheduleJob.getId(),
                    scheduleJob.getBeanName(),scheduleJob.getMethodName());
		} catch (Exception e) {
            log.warn("任务[{}]执行失败，任务ID：[{}],任务：{}[{}],错误:{}" ,scheduleJob.getRemark(),scheduleJob.getId(),
                    scheduleJob.getBeanName(),scheduleJob.getMethodName(),e.toString());
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			jobLog.setTimes((int)times);
			jobLog.setEndDate(new Date());
			//任务状态    0：成功    1：失败
			jobLog.setStatus(1);
			jobLog.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.save(jobLog);
		}
    }
}
