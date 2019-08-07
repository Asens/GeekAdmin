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

    /**
     * 通过角色集合获取这些集合的所有权限
     * @param roleList 角色集合
     * @return 角色集合对应的权限集合
     */
    List<Permission> getListByRoleIds(List<Integer> roleList);

    /**
     * 获取一个角色的所有权限
     * @param id 角色id
     * @return 角色对应的权限集合
     */
    List<Permission> getListByRole(Long id);

    void addChildren(Permission permission,List<Permission> list);

    List<Permission> getChildren(Permission permission,List<Permission> list);

    boolean hasChild(String code,List<Permission> list);

    /**
     * 有子节点且子节点全是功能按钮的
     * @param list 所有权限
     * @return 有视图的菜单集合
     */
    List<Permission> baseMenu(List<Permission> list);


    /**
     * 删除权限以及该权限的子孙权限
     * 删除这些权限和角色的管理关系
     * @param id 权限id
     */
    void delete(Long id);

    /**
     * 权限树形表格,子菜单在父菜单的children内
     * @return 权限集合
     */
    List<Permission> menuTree();
}
