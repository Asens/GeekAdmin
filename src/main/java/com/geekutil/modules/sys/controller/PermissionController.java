package com.geekutil.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/api/sys/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/menuTree")
    public Object menuTree(){
        List<Permission> list = permissionService.list();
        List<Permission> rootList = list.stream().filter(t->StringUtils.isBlank(t.getParentCode()))
                .collect(Collectors.toList());
        for(Permission permission:rootList){
            permission.setKey(permission.getId());
            if(hasChild(permission.getCode(),list)){
                addChildren(permission,list);
            }
        }
        return Result.success("result",rootList);
    }

    @PostMapping("/save")
    public Object save(Permission permission){
        permissionService.saveOrUpdate(permission);
        return Result.success();
    }

    @GetMapping("/info")
    public Object info(String code){
        Permission permission = permissionService.getOne(new QueryWrapper<Permission>()
                .lambda().eq(Permission::getCode,code));
        return Result.success("result",permission);
    }

    @GetMapping("/delete")
    public Object delete(Long id){
        List<Permission> list = permissionService.list();
        List<Long> toDelete = new ArrayList<>();
        Permission permission = list.stream().filter(t->Objects.equals(id,t.getId()))
                .findFirst().get();
        if(!hasChild(permission.getCode(),list)){
            permissionService.removeById(permission.getId());
            return Result.success();
        }

        toDelete.add(id);
        addToDelete(permission,list,toDelete);
        permissionService.removeByIds(toDelete);
        return Result.success("result",permission);
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

    private void addChildren(Permission permission,List<Permission> list) {
        List<Permission> children = getChildren(permission,list);
        for(Permission c:children){
            c.setKey(c.getId());
            if(hasChild(c.getCode(),list)) {
                addChildren(c, list);
            }
        }
        permission.setChildren(children);
    }

    private List<Permission> getChildren(Permission permission,List<Permission> list) {
        List<Permission> r = new ArrayList<>();
        for(Permission p:list){
            if(Objects.equals(permission.getCode(),p.getParentCode())){
                r.add(p);
            }
        }
        return r;
    }

    private boolean hasChild(String code,List<Permission> list){
        for(Permission permission:list){
            if(Objects.equals(code,permission.getParentCode())){
                return true;
            }
        }
        return false;
    }

}
