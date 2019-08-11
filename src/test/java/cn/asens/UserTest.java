package cn.asens;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.geekutil.Const;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.LengthValidator;
import com.geekutil.common.validate.NotEmptyValidator;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.dto.UserDTO;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Locale;

import static com.geekutil.common.validate.ValidateUtils.validator;

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
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("asdadafawasd");
        ComplexResult result = FluentValidator.checkAll()
                .failOver()
                .on(userDTO, new HibernateSupportedValidator<UserDTO>().setHiberanteValidator(validator()))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if(!result.isSuccess()){
            System.out.println(Result.error(ValidateUtils.getErrorMessage(result.getErrors())));
        }
    }
}
