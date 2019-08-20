package com.geekutil.modules.sys.interceptor;

import com.geekutil.common.util.FrontUtils;
import com.geekutil.common.util.HttpUtils;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Asens
 * create 2019-07-20 22:08
 **/
@Log4j2
public class CheckLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(FrontUtils.getCurrentUserId()==null){
            log.error("[{}] has no userId", HttpUtils.getRequest().getRequestURI());
            throw new AuthenticationException();
        }
        return true;
    }
}
