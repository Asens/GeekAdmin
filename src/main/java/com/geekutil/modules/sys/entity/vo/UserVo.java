package com.geekutil.modules.sys.entity.vo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Asens
 * create 2019-07-21 15:32
 **/
@Data
public class UserVo {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String avatar;

    private String phone;

    private Date createDate;

    private Date lastLoginDate;

    private String lastLoginIp;

    private Integer status;

    private Integer deleted;

    private String token;

    private JSONObject role;
}
