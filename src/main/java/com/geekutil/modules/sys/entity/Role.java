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
 * create 2019-07-17 22:49
 **/
@TableName("sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {
    @TableId(type= IdType.AUTO)
    private Long id;

    private String name;

    private String describe;

    private Integer status;

    @TableField("create_date")
    private Date createDate;
}
