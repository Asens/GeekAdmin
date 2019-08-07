package cn.asens;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.geekutil.Const;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.NotEmptyValidator;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.modules.sys.entity.Permission;
import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * @author Asens
 * create 2019-07-21 15:44
 **/

public class UserTest {
    @Test
    public void pwd(){
        System.out.println(DigestUtils.md5DigestAsHex(("aaa"+ Const.USER_SALT).getBytes()));
    }

    @Test
    public void validate(){
        Permission permission = new Permission();
        ComplexResult result = FluentValidator.checkAll()
                .on(permission.getName(), new NotEmptyValidator("名称"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if(!result.isSuccess()){
            System.out.println(Result.error(ValidateUtils.getErrorMessage(result.getErrors())));
        }
    }
}
