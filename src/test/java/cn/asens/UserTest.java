package cn.asens;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.geekutil.Const;
import com.geekutil.common.util.DateUtils;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.geekutil.common.validate.ValidateUtils.validator;

/**
 * @author Asens
 * create 2019-07-21 15:44
 **/

public class UserTest {
    @Test
    public void pwd() {
        System.out.println(DigestUtils.md5DigestAsHex(("admin" + Const.USER_SALT).getBytes()));
    }

}
