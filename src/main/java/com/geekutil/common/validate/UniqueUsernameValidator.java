package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Asens
 */

public class UniqueUsernameValidator extends ValidatorHandler<Object> implements Validator<Object> {

    @Override
    public boolean validate(ValidatorContext context, Object t) {
        //编辑时不传递,不校验
        if(t==null){
            return true;
        }
        UserService userService = (UserService)context.getAttribute("userService");
        int count = userService.lambdaQuery().eq(User::getUsername,t.toString()).count();
        if(count>=1){
            context.addError(ValidationError
                    .create("用户名已存在")
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }
}
