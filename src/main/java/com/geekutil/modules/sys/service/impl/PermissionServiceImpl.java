package com.geekutil.modules.sys.service.impl;

import com.geekutil.Const;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.mapper.PermissionMapper;
import com.geekutil.modules.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getListByRoleIds(@Param("roleList") List<Long> roleList) {
        return permissionMapper.getListByRoleIds(roleList);
    }

    @Override
    public List<Permission> getListByRole(@Param("id") Long id) {
        return permissionMapper.getListByRoleId(id);
    }

    private void addToDelete(Permission permission, List<Permission> list, List<Long> toDelete) {
        List<Permission> children = getChildren(permission,list);
        for(Permission p:children){
            toDelete.add(p.getId());
            if(!hasChild(p.getCode(),list)){
                continue;
            }
            addToDelete(p,list,toDelete);
        }
    }

    @Override
    public void addChildren(Permission permission,List<Permission> list) {
        List<Permission> children = getChildren(permission,list);
        children.sort(Comparator.comparingInt(Permission::getSortNum));
        for(Permission c:children){
            c.setKey(c.getId());
            if(hasChild(c.getCode(),list)) {
                addChildren(c, list);
            }
        }
        permission.setChildren(children);
    }

    @Override
    public List<Permission> getChildren(Permission permission,List<Permission> list) {
        List<Permission> r = new ArrayList<>();
        for(Permission p:list){
            if(Objects.equals(permission.getCode(),p.getParentCode())){
                r.add(p);
            }
        }
        return r;
    }

    @Override
    public boolean hasChild(String code,List<Permission> list){
        for(Permission permission:list){
            if(Objects.equals(code,permission.getParentCode())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Permission> baseMenu(List<Permission> list) {
        return list.stream().filter(t->{
//            if(!hasChild(t.getCode(),list)){
//                return false;
//            }
            if(Objects.equals(t.getIsMenu(), Const.DATABASE_INTEGER_NO)){
                return false;
            }
            List<Permission> children = getChildren(t,list);
            for(Permission c:children){
                if(Objects.equals(c.getIsMenu(), Const.DATABASE_INTEGER_YES)){
                    return false;
                }
            }
            return true;
        }).collect(toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        List<Permission> list = list();
        List<Long> toDelete = new ArrayList<>();
        Permission permission = list.stream().filter(t->Objects.equals(id,t.getId()))
                .findFirst().get();
        if(!hasChild(permission.getCode(),list)){
            removeById(permission.getId());
            return;
        }

        toDelete.add(id);
        addToDelete(permission,list,toDelete);
        removeByIds(toDelete);

        //TODO 删除和角色的关联关系
    }

    @Override
    public List<Permission> menuTree() {
        List<Permission> list = list();
        List<Permission> rootList = list.stream().filter(t-> StringUtils.isBlank(t.getParentCode()))
                .collect(toList());
        rootList.sort(Comparator.comparingInt(Permission::getSortNum));
        for(Permission permission:rootList){
            permission.setKey(permission.getId());
            if(hasChild(permission.getCode(),list)){
                addChildren(permission,list);
            }
        }
        return rootList;
    }
}
