package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/

public interface PermissionService extends IService<Permission> {

    List<Permission> getListByRoleIds(List<Integer> roleList);

    List<Permission> getListByRole(Long id);

    void addToDelete(Permission permission, List<Permission> list, List<Long> toDelete);

    void addChildren(Permission permission,List<Permission> list);

    List<Permission> getChildren(Permission permission,List<Permission> list);

    boolean hasChild(String code,List<Permission> list);

    /**
     * 有子节点且子节点全是功能按钮的
     */
    List<Permission> baseMenu(List<Permission> list);
}
