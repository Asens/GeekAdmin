package com.geekutil.modules.sys.service.impl;

import com.geekutil.common.util.FrontUtils;
import com.geekutil.common.util.HttpUtils;
import com.geekutil.modules.sys.entity.SystemLog;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.mapper.SystemLogMapper;
import com.geekutil.modules.sys.service.SystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekutil.modules.sys.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Asens
 * @since 2019-09-01
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLogMapper, SystemLog> implements SystemLogService {
    @Resource
    private UserService userService;

    @Override
    public void saveLog(int type, String message,String methodInfo,String params) {
        SystemLog log = new SystemLog();
        log.setType(type);
        log.setMessage(message);
        log.setMethodInfo(methodInfo);
        log.setParams(params);
        log.setCreateDate(new Date());
        log.setUserId(FrontUtils.getCurrentUserId());
        User user = userService.getById(FrontUtils.getCurrentUserId());
        log.setUsername(user.getUsername());
        save(log);
    }
}
