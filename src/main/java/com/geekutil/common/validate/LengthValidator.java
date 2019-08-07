package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * @author Asens
 */

public class LengthValidator extends ValidatorHandler<String> implements Validator<String> {

    private int min;

    private int max;

    private String name;

    public LengthValidator(int min, int max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    @Override
    public boolean validate(ValidatorContext context, String t) {
        if (t == null || t.length() > max || t.length() < min) {
            context.addError(ValidationError.create(String.format("%s最小长度%s,最大长度%s", name, min, max))
                    .setErrorCode(-1)
                    .setField(name)
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }

}
