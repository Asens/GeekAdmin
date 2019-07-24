package com.geekutil.modules.sys.service.impl;

import com.geekutil.modules.sys.entity.Permission;
import com.geekutil.modules.sys.mapper.PermissionMapper;
import com.geekutil.modules.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<Permission> getListByRoleIds(@Param("roleList") List<Integer> roleList) {
        return permissionMapper.getListByRoleIds(roleList);
    }
}
