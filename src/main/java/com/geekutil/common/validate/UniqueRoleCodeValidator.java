package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.service.RoleService;
import com.geekutil.modules.sys.service.UserService;

/**
 * @author Asens
 */

public class UniqueRoleCodeValidator extends ValidatorHandler<Object> implements Validator<Object> {

    @Override
    public boolean validate(ValidatorContext context, Object t) {
        RoleService roleService = (RoleService)context.getAttribute("roleService");
        int count = roleService.lambdaQuery().eq(Role::getCode,t.toString()).count();
        if(count>=1){
            context.addError(ValidationError
                    .create("角色代码已存在")
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }
}
