package com.geekutil.modules.sys.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekutil.common.log.SystemLog;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.modules.sys.entity.ScheduleJob;
import com.geekutil.modules.sys.entity.dto.ScheduleJobDTO;
import com.geekutil.modules.sys.service.ScheduleJobService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import static com.geekutil.common.util.HttpUtils.pageSize;
import static com.geekutil.common.util.PageUtils.pageResult;
import static com.geekutil.common.validate.ValidateUtils.validator;

/**
 * 定时任务
 * @author Asens
 *
 */
@RestController
@RequestMapping("/api/sys/schedule")
public class ScheduleJobController{

	@Resource
	private ScheduleJobService scheduleJobService;

	/**
	 * 定时任务列表
	 */
	@GetMapping("/list")
	@SystemLog
	public Object list(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
					   Integer status, String remark, String beanName, String methodName){
		IPage<ScheduleJob> page = scheduleJobService.lambdaQuery()
				.eq(status!=null,ScheduleJob::getStatus,status)
				.like(StringUtils.isNotBlank(remark),ScheduleJob::getRemark,remark)
				.like(StringUtils.isNotBlank(beanName),ScheduleJob::getBeanName,beanName)
				.like(StringUtils.isNotBlank(methodName),ScheduleJob::getMethodName,methodName)
				.page(new Page<>(pageNo, pageSize()));
		return Result.success().data(pageResult(page));
	}

	/**
	 * 编辑定时任务
	 */
	@GetMapping("/edit")
	@SystemLog(message = "定时任务 编辑")
	public Object edit(@RequestParam Long id){
		return Result.success().data(scheduleJobService.getById(id));
	}
	
	/**
	 * 保存定时任务
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Object save(ScheduleJobDTO scheduleJobDTO){
		ComplexResult result = FluentValidator.checkAll()
				.on(scheduleJobDTO, new HibernateSupportedValidator<ScheduleJobDTO>().setHiberanteValidator(validator()))
				.doValidate()
				.result(ResultCollectors.toComplex());
		if(!result.isSuccess()){
			return Result.error(ValidateUtils.getErrorMessage(result.getErrors()));
		}
		scheduleJobService.saveOrUpdateUser(scheduleJobDTO);
		return Result.success();
	}
	
	/**
	 * 删除定时任务
	 * 暂时先删除一个,保留删除多个的方式
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(@RequestParam Long id){
		Long[] l = new Long[]{id};
		scheduleJobService.deleteBatch(l);
		return Result.success();
	}
	
	/**
	 * 立即执行任务
	 */
	@RequestMapping("/run")
	@ResponseBody
	public Object run(@RequestParam Long id){
	    Long[] l = new Long[]{id};
		scheduleJobService.run(l);
		return Result.success();
	}

    /**
     * 立即执行全部任务
     */
    @RequestMapping("/runAll")
    @ResponseBody
    public Object runAll(){
        scheduleJobService.run(scheduleJobService
                .list().stream()
                .map(ScheduleJob::getId)
                .toArray(Long[]::new));
        return Result.success();
    }
	
	/**
	 * 暂停定时任务
	 */
	@RequestMapping("/pause")
	@ResponseBody
	public Result pause(@RequestParam Long id){
        Long[] l = new Long[]{id};
		scheduleJobService.pause(l);
		return Result.success();
	}
	
	/**
	 * 恢复定时任务
	 */
	@RequestMapping("/resume")
	@ResponseBody
	public Result resume(@RequestParam Long id){
        Long[] l = new Long[]{id};
		scheduleJobService.resume(l);
		return Result.success();
	}

}
