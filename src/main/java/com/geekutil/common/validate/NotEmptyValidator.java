package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Asens
 */

public class NotEmptyValidator  extends ValidatorHandler<String> implements Validator<String> {

    private String name;

    public NotEmptyValidator(String name) {
        this.name = name;
    }

    @Override
    public boolean validate(ValidatorContext context, String t) {
        if(StringUtils.isEmpty(t)){
            context.addError(ValidationError
                    .create(name+"不能为空")
                    .setField(name)
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }
}
