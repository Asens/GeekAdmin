package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;

import java.util.List;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/

public interface PermissionService extends IService<Permission> {

    List<Permission> getListByRoleIds(List<Integer> roleList);
}
