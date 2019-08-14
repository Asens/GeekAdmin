package com.geekutil.modules.sys.entity.dto;

import com.alibaba.fastjson.JSONObject;
import com.geekutil.common.validate.group.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Asens
 * create 2019-08-08 5:57
 **/
@Data
public class UserDTO {
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 2, max = 20,message = "用户名最小长度2,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z\4e00-\u9fa5]+",message = "用户名包含不支持的字符")
    private String username;

    @NotEmpty(message = "密码不能为空" , groups = AddGroup.class)
    @Length(min = 1, max = 20,message = "密码最小长度6,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z]+",message = "密码包含不支持的字符")
    private String password;

    @Length(min = 2, max = 20,message = "昵称最小长度2,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z\4e00-\u9fa5]+",message = "昵称最包含不支持的字符")
    private String name;

    @Length(max = 200,message = "头像地址最大长度200")
    private String avatar;

    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "昵称最包含不支持的字符")
    private String phone;

    @NotNull(message = "状态不能为空")
    private Integer status;

    private String token;

    private JSONObject role;
}
