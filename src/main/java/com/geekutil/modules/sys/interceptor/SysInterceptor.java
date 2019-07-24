package com.geekutil.modules.sys.interceptor;

import com.geekutil.common.util.FrontUtils;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Asens
 * create 2019-07-20 22:08
 **/
@Log4j2
public class SysInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Access-Token");
        log.info("token : [{}]",token);
        if(StringUtils.isNotEmpty(token)){
            Long userId = userService.getUserIdByToken(token);
            FrontUtils.setCurrentUserId(userId);
        }
        return true;
    }
}
