package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * @author Asens
 */

public class NumberValidator extends ValidatorHandler<Integer> implements Validator<Integer> {

    private int min;

    private int max;

    private String name;

    public NumberValidator(int min, int max, String name) {
        this.min = min;
        this.max = max;
        this.name = name;
    }

    @Override
    public boolean validate(ValidatorContext context, Integer t) {
        if (t == null || t > max || t < min) {
            context.addError(ValidationError.create(String.format("%s最小%s,最大%s", name, min, max))
                    .setErrorCode(-1)
                    .setField(name)
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }

}
