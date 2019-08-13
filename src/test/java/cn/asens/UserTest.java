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
        System.out.println(DigestUtils.md5DigestAsHex(("aaa" + Const.USER_SALT).getBytes()));
    }

    @Test
    public void validate() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("asdadafawasd");
        ComplexResult result = FluentValidator.checkAll()
                .failOver()
                .on(userDTO, new HibernateSupportedValidator<UserDTO>().setHiberanteValidator(validator()))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            System.out.println(Result.error(ValidateUtils.getErrorMessage(result.getErrors())));
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void m() {
        ListNode node1 = new ListNode(9);

        ListNode node2 = new ListNode(1);
        node2.next = new ListNode(9);
        node2.next.next = new ListNode(9);
        node2.next.next.next = new ListNode(9);
        node2.next.next.next.next = new ListNode(9);
        node2.next.next.next.next.next = new ListNode(9);
        node2.next.next.next.next.next.next = new ListNode(9);
        node2.next.next.next.next.next.next.next = new ListNode(9);
        node2.next.next.next.next.next.next.next.next = new ListNode(9);
        node2.next.next.next.next.next.next.next.next.next = new ListNode(9);

        //342 + 465
        //807
        //7 -> 0 -> 8

        ListNode result = addTwoNumbers(node1,node2);
        System.out.println(result.val);
        System.out.println(result.next.val);
        System.out.println(result.next.next.val);
        System.out.println(result.next.next.next.val);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long i = 1;
        long i1 = 0;
        do{
            i1 += l1.val*i;
            l1 = l1.next;
            i*=10;
        }while (l1 != null);
        i = 1;
        long i2 = 0;
        do{
            i2 += l2.val*i;
            l2 = l2.next;
            i*=10;
        }while (l2 != null);

        long result = i1+i2;
        ListNode r = new ListNode((int)(result % 10));
        if(result<10){
            return r;
        }
        result /= 10;
        ListNode t = r;
        do{
            t.next = new ListNode((int)(result % 10));
            t = t.next;
            result /= 10;
        }while ( result > 0);
        return r;
    }
}
