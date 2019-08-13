package com.geekutil.common.exception;

import com.geekutil.common.util.Result;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局的异常拦截器
 * @author Asens
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Object noPermission(HttpServletRequest request, Exception e) {
        return Result.error("没有对应的数据/接口权限,请联系管理员");
    }
}
