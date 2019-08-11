package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.geekutil.common.util.CollectionUtils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Locale;

/**
 * @author Asens
 */

public class ValidateUtils {

    static {
        Locale.setDefault(Locale.CHINA);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static Validator validator;

    public static Validator validator(){
        return validator;
    }

    public static String getErrorMessage(List<ValidationError> list){
        if(CollectionUtils.isEmpty(list)){
            return "";
        }
        StringBuilder t = new StringBuilder();
        for(ValidationError error : list){
            t.append(error.getErrorMsg()).append(";");
        }
        String result = t.toString();
        if(result.length()>1){
            return result.substring(0,result.length()-1);
        }
        return result;
    }
}
