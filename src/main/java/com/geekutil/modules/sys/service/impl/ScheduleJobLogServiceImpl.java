package com.geekutil.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekutil.modules.sys.entity.ScheduleJobLog;
import com.geekutil.modules.sys.service.ScheduleJobLogService;
import com.geekutil.modules.sys.mapper.ScheduleJobLogMapper;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 定时任务日志
 * @author Asens
 */
@Service
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

    @Override
    public IPage<ScheduleJobLog> page(Integer pageNo, int pageSize, Map<String, Object> paraMap) {
        return lambdaQuery()
                .eq(paraMap.containsKey("jobId") && paraMap.get("jobId")!=null,
                        ScheduleJobLog::getJobId,paraMap.get("jobId"))
                .gt(paraMap.containsKey("start") && paraMap.get("start")!=null,
                        ScheduleJobLog::getJobId,paraMap.get("start"))
                .lt(paraMap.containsKey("end") && paraMap.get("end")!=null,
                        ScheduleJobLog::getJobId,paraMap.get("end"))
                .eq(paraMap.containsKey("status") && paraMap.get("status")!=null,
                        ScheduleJobLog::getJobId,paraMap.get("status"))
                .page(new Page<>(pageNo,pageSize));
    }
}
