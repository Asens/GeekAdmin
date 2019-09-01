package com.geekutil.common.log;

import com.geekutil.common.auth.Auth;
import com.geekutil.modules.sys.service.SystemLogService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.naming.NoPermissionException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Asens
 * create 2019-09-01 18:55
 **/
@Aspect
@Component
@Order(99)
@Log4j2
public class SystemLogAop {
    @Resource
    private SystemLogService systemLogService;

    @Pointcut(value = "@annotation(com.geekutil.common.log.SystemLog)")
    private void logPoint() {

    }

    @Around("logPoint()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        int type = systemLog.type();
        String message = systemLog.message();
        Parameter[] parameters = method.getParameters();
        Object[] objects = point.getArgs();
        Map<String,String> map = new LinkedHashMap<>();
        for (int i = 0; i < objects.length; i++) {
            map.put(parameters[i].getName(),objects[i]==null?"":objects[i].toString());
        }
        systemLogService.saveLog(type,message,method.toString(),map.toString());
        return point.proceed();
    }
}
