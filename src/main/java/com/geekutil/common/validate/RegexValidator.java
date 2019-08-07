package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Asens
 */

public class RegexValidator extends ValidatorHandler<String> implements Validator<String> {

    private String name;

    private String pattern;

    public RegexValidator(String name,String pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    @Override
    public boolean validate(ValidatorContext context, String t) {
        if(StringUtils.isEmpty(t) || !t.matches(pattern)){
            context.addError(ValidationError
                    .create(name+"格式不正确")
                    .setField(name)
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }
}
