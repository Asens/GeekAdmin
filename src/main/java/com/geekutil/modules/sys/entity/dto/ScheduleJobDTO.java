package com.geekutil.modules.sys.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geekutil.common.validate.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 定时任务
 * @author Asens
 */

@Data
public class ScheduleJobDTO {
    /**
     * 任务id
     */
    private Long id;

    /**
     * spring bean名称
     */
    @NotEmpty(message = "bean名称不能为空")
    @Length(min = 2, max = 20,message = "bean名称最小长度2,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z]+",message = "bean名称包含不支持的字符")
    private String beanName;

    /**
     * 方法名
     */
    @NotEmpty(message = "方法名不能为空")
    @Length(min = 2, max = 20,message = "方法名最小长度2,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z]+",message = "方法名包含不支持的字符")
    private String methodName;

    /**
     * 参数
     */
    @Length(max = 50,message = "方法名最大长度50")
    private String params;

    /**
     * cron表达式
     */
    @NotEmpty(message = "cron表达式不能为空", groups = AddGroup.class)
    @Length(max = 15,message = "cron表达式最大长度15")
    private String cronExpression;

    /**
     * 任务状态
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 备注
     */
    @NotEmpty(message = "备注不能为空", groups = AddGroup.class)
    @Length(min = 2, max = 20,message = "备注最小长度2,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z\u4e00-\u9fa5]+",message = "备注包含不支持的字符")
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;
}
