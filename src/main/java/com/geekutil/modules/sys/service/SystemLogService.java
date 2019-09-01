package com.geekutil.modules.sys.service;

import com.geekutil.modules.sys.entity.SystemLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Asens
 * @since 2019-09-01
 */
public interface SystemLogService extends IService<SystemLog> {

    void saveLog(int type, String message,String methodInfo,String params);
}
