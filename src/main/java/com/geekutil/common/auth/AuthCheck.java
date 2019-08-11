package com.geekutil.common.auth;

import com.geekutil.common.util.FrontUtils;
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

/**
 * @author Asens
 * create 2019-08-11 9:45
 **/
@Aspect
@Component
@Order(99)
@Log4j2
public class AuthCheck {
    @Resource
    private AuthService authService;

    @Pointcut(value = "@annotation(com.geekutil.common.auth.Auth)")
    private void authCheck() {

    }

    @Around("authCheck()")
    public Object check(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Auth auth = method.getAnnotation(Auth.class);
        String[] permissions = auth.value();
        String[] roles = auth.roles();
        if(hasPermission(permissions,roles)){
            return point.proceed();
        }
        throw new NoPermissionException();
    }

    private boolean hasPermission(String[] permissions, String[] roles) {
        Long userId = FrontUtils.getCurrentUserId();
        log.info("hasPermission :{}",userId);
        return authService.hasPermission(userId,permissions,roles);
    }
}
