

package com.geekutil.modules.sys.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 定时任务日志
 *
 * @author Asens
 */
@TableName("sys_schedule_job_log")
@Data
public class ScheduleJobLog {
	
	/**
	 * 日志id
	 */
	@TableId
	private Long id;
	
	/**
	 * 任务id
	 */
	@TableField("job_id")
	private Long jobId;
	
	/**
	 * spring bean名称
	 */
	@TableField("bean_name")
	private String beanName;
	
	/**
	 * 方法名
	 */
	@TableField("method_name")
	private String methodName;

	/**
	 * 任务描述
	 */
	private String remark;
	
	/**
	 * 参数
	 */
	private String params;
	
	/**
	 * 任务状态
	 */
	private Integer status;
	
	/**
	 * 失败信息
	 */
	private String error;
	
	/**
	 * 耗时(单位：毫秒)
	 */
	private Integer times;
	
	/**
	 * 创建时间
	 */
	@TableField("create_date")
	private Date createDate;

	/**
	 * 创建时间
	 */
	@TableField("end_date")
	private Date endDate;
}
