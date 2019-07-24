package com.geekutil.modules.sys.service.impl;

import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.mapper.RoleMapper;
import com.geekutil.modules.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Integer> getListByUser(Long userId) {
        return roleMapper.getListByUser(userId);
    }
}
