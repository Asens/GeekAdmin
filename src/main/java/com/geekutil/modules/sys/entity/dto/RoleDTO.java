package com.geekutil.modules.sys.entity.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Asens
 * create 2019-07-27 10:38
 **/
@Data
public class RoleDTO {
    private Long id;

    @NotEmpty(message = "角色名不能为空")
    @Length(min = 1, max = 20,message = "角色名最小长度1,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z\4e00-\u9fa5]+",message = "角色名包含不支持的字符")
    private String name;

    @Length(max = 100,message = "角色描述最大长度100")
    private String description;

    @NotNull(message = "状态不能为空")
    private Integer status;

    private String[] permissions;
}
