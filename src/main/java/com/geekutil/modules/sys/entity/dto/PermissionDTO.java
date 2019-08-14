package com.geekutil.modules.sys.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Asens
 * create 2019-07-17 22:55
 * 权限
 **/

@Data
public class PermissionDTO{

    private Long id;

    @NotEmpty(message = "菜单名不能为空")
    @Length(min = 1, max = 20,message = "菜单名最小长度1,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z\4e00-\u9fa5]+",message = "菜单名包含不支持的字符")
    private String name;

    @NotEmpty(message = "菜单编码不能为空")
    @Length(min = 1, max = 50,message = "菜单编码最小长度1,最大长度50")
    @Pattern(regexp = "[0-9a-zA-Z]+",message = "菜单编码包含不支持的字符")
    private String code;

    @NotNull(message = "状态不能为空")
    private Integer status;

    @NotEmpty(message = "父菜单编码不能为空")
    @Length(min = 1, max = 50,message = "父菜单编码最小长度1,最大长度50")
    @Pattern(regexp = "[0-9a-zA-Z]+",message = "父菜单编码包含不支持的字符")
    private String parentCode;

    @Length(min = 1, max = 20,message = "图标最小长度1,最大长度20")
    @Pattern(regexp = "[0-9a-zA-Z-]+",message = "图标包含不支持的字符")
    private String icon;

    @Length(min = 1, max = 20,message = "图标最小长度1,最大长度20")
    private String component;

    @Pattern(regexp = "[0-9]{1,5}",message = "排序格式错误")
    private String sortNum;

    @NotNull(message = "菜单状态不能为空")
    private Integer isMenu;

    @Length(min = 1, max = 80,message = "路径编码最小长度1,最大长度80")
    private String realPath;

    private Object children;

    private Object key;
}
