package com.geekutil.entity;

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

@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Data
public class User extends Model<User> {
    @TableId(value="id", type= IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    @TableField("create_date")
    private Date createDate;
}
