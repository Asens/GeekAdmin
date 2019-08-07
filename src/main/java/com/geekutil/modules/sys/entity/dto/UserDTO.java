package com.geekutil.modules.sys.entity.dto;

import lombok.Data;

/**
 * @author Asens
 * create 2019-08-08 5:57
 **/
@Data
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String name;

    private String avatar;

    private String phone;

    private Integer status;
}
