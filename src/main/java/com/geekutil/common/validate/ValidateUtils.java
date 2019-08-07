package com.geekutil.common.validate;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.geekutil.common.util.CollectionUtils;

import java.util.List;

/**
 * @author Asens
 */

public class ValidateUtils {
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
