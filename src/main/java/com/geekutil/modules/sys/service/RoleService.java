package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.dto.RoleDTO;

import java.util.List;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/

public interface RoleService extends IService<Role> {

    /**
     * 获取用户的角色id列表
     * @param userId 用户id
     * @return 用户的角色id列表
     */
    List<Integer> getListByUser(Long userId);

    /**
     * 有id时更新,没id保存
     * @param roleDTO 角色
     */
    void saveOrUpdate(RoleDTO roleDTO);

    /**
     * 删除角色,以及用户角色管理,角色权限关联
     * @param id 角色id
     */
    void deleteRole(Long id);
}
