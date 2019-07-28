package com.geekutil.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geekutil.Const;
import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.RolePermission;
import com.geekutil.modules.sys.entity.dto.RoleDTO;
import com.geekutil.modules.sys.mapper.RoleMapper;
import com.geekutil.modules.sys.service.PermissionService;
import com.geekutil.modules.sys.service.RolePermissionService;
import com.geekutil.modules.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public List<Integer> getListByUser(Long userId) {
        return roleMapper.getListByUser(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(RoleDTO roleDTO) {
        Role role;
        if(roleDTO.getId()==null){
            role = saveDTO(roleDTO);
        }else{
            role = updateDTO(roleDTO);
            rolePermissionService.remove(new QueryWrapper<RolePermission>().lambda()
                    .eq(RolePermission::getRoleId,roleDTO.getId()));
        }

        List<Permission> permissionList = permissionService.lambdaQuery()
                .in(Permission::getCode,
                        new ArrayList<>(Arrays.asList(roleDTO.getPermissions())))
                .list();

        List<RolePermission> rolePermissionList = new ArrayList<>();
        Set<String> parentSet = new HashSet<>();
        List<Permission> list = permissionService.list();

        for(Permission p:permissionList){
            if(StringUtils.isNotEmpty(p.getParentCode()) &&
                    Objects.equals(p.getIsMenu(), Const.DATABASE_INTEGER_YES)){
                parentSet.addAll(getParentsCodes(p,list));
            }
            RolePermission rp = new RolePermission();
            rp.setRoleId(role.getId());
            rp.setPermissionId(p.getId());
            rolePermissionList.add(rp);
        }

        for(String code:parentSet){
            Optional<Permission> p = list.stream().filter(t->code.equals(t.getCode()))
                    .findFirst();
            if(p.isPresent()){
                RolePermission rp = new RolePermission();
                rp.setRoleId(role.getId());
                rp.setPermissionId(p.get().getId());
                rolePermissionList.add(rp);
            }
        }

        rolePermissionService.saveBatch(rolePermissionList);
    }

    private List<String> getParentsCodes(Permission p, List<Permission> list) {
        List<String> result = new ArrayList<>();
        do{
            String parentCode = p.getParentCode();
            result.add(parentCode);
            Optional<Permission> po = list.stream().filter(t->parentCode.equals(t.getCode()))
                    .findFirst();
            if(!po.isPresent()){
                break;
            }
            p = po.get();
        }while (StringUtils.isNotEmpty(p.getParentCode()));
        return result;
    }

    private Role updateDTO(RoleDTO roleDTO) {
        Role role = getById(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role.setStatus(role.getStatus());
        updateById(role);
        return role;
    }

    private Role saveDTO(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role.setCreateDate(new Date());
        role.setStatus(role.getStatus());
        save(role);
        return role;
    }
}
