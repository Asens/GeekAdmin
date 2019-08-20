package com.geekutil.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
        return null;
        //TODO
        //return page();
    }
}
