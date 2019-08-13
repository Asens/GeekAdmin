package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.service.PermissionService;
import com.geekutil.modules.sys.service.RoleService;

/**
 * @author Asens
 */

public class UniquePermissionCodeValidator extends ValidatorHandler<Object> implements Validator<Object> {

    @Override
    public boolean validate(ValidatorContext context, Object t) {
        PermissionService permissionService = (PermissionService)context.getAttribute("permissionService");
        int count = permissionService.lambdaQuery().eq(Permission::getCode,t.toString()).count();
        if(count>=1){
            context.addError(ValidationError
                    .create("权限代码已存在")
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }
}
