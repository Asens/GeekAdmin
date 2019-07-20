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
 * create 2019-07-17 22:00
 **/


@TableName("sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String avatar;

    private String phone;

    @TableField("create_date")
    private Date createDate;

    @TableField("last_login_date")
    private Date lastLoginDate;

    @TableField("last_login_ip")
    private String lastLoginIp;

    private Integer status;

    private Integer deleted;
}
