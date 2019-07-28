package com.geekutil.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Asens
 * create 2019-07-17 22:55
 * 权限
 **/

@TableName("sys_permission")
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends Model<Permission> {
    @TableId(type= IdType.AUTO)
    private Long id;

    private String name;

    private String code;

    private Integer status;

    @TableField("parent_code")
    private String parentCode;

    private String icon;

    private String component;

    @TableField("sort_num")
    private String sortNum;

    @TableField("is_menu")
    private Integer isMenu;

    @TableField(exist = false)
    private Object children;

    @TableField(exist = false)
    private Object key;

}
