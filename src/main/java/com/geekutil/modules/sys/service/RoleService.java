package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.User;

import java.util.List;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/

public interface RoleService extends IService<Role> {

    List<Integer> getListByUser(Long userId);
}
