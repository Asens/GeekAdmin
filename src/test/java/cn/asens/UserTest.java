package cn.asens;

import com.geekutil.Const;
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
}
