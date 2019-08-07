package com.geekutil.modules.sys.entity.dto;

import lombok.Data;

/**
 * @author Asens
 * create 2019-07-27 10:38
 **/
@Data
public class RoleDTO {
    private Long id;

    private String name;

    private String description;

    private Integer status;

    private String[] permissions;
}
