package com.geekutil.common.exception;

import com.geekutil.common.util.Result;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.naming.NoPermissionException;

/**
 * 全局的异常拦截器
 * @author Asens
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    @ExceptionHandler(NoPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Object notFount(NoPermissionException e) {
        return Result.error("没有权限");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object notFount(RuntimeException e) {
        return Result.error("服务异常");
    }
}
