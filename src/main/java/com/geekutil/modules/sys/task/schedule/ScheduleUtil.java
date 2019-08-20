package com.geekutil.modules.sys.task.schedule;

import com.geekutil.modules.sys.entity.ScheduleJob;
import org.quartz.*;

/**
 * 定时任务工具类
 *
 * @author Asens
 */
public class ScheduleUtil {
    private final static String JOB_NAME = "TASK_";
    
    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }
    
    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
             return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new IllegalStateException("cron error "+e.getMessage());
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
        	//构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJobBean.class).withIdentity(getJobKey(scheduleJob.getId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getId())).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob.getId());

            scheduler.scheduleJob(jobDetail, trigger);
            
            //暂停任务
            if(scheduleJob.getStatus() == ScheduleConfig.ScheduleStatus.PAUSE.getValue()){
            	pauseJob(scheduler, scheduleJob.getId());
            }
        } catch (SchedulerException e) {
            //throw new RRException("创建定时任务失败", e);
            throw new IllegalStateException("创建定时任务失败");
        }
    }
    
    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
            		.withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getId());
            
            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            
            //参数
            trigger.getJobDataMap().put(ScheduleJob.JOB_PARAM_KEY, scheduleJob.getId());
            
            scheduler.rescheduleJob(triggerKey, trigger);
            
            //暂停任务
            if(scheduleJob.getStatus() == ScheduleConfig.ScheduleStatus.PAUSE.getValue()){
            	pauseJob(scheduler, scheduleJob.getId());
            }
            
        } catch (SchedulerException e) {
            throw new IllegalStateException("更新定时任务失败 :"+e.getMessage());
        }
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, ScheduleJob scheduleJob) {
        try {
        	//参数
        	JobDataMap dataMap = new JobDataMap();
        	dataMap.put(ScheduleJob.JOB_PARAM_KEY, scheduleJob.getId());
        	
            scheduler.triggerJob(getJobKey(scheduleJob.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new IllegalStateException("立即执行定时任务失败 :"+e.getMessage());
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new IllegalStateException("暂停定时任务失败 :"+e.getMessage());
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new IllegalStateException("恢复定时任务失败 :"+e.getMessage());
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new IllegalStateException("删除定时任务失败 :"+e.getMessage());
        }
    }
}
