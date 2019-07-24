package com.geekutil.modules.sys.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Asens
 * create 2019-07-21 16:34
 **/
@Data
public class RoleVo {
    private Long id;

    private String name;

    private String describe;

    private Integer status;

    private Date createDate;

    private List<PermissionVO> permissions;
}
