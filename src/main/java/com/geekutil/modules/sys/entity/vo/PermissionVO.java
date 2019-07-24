package com.geekutil.modules.sys.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Asens
 * create 2019-07-17 22:55
 * 权限
 **/

@Data
public class PermissionVO{

    private String describe;

    private String action;

    private String permissionId;

    private String permissionName;
}
