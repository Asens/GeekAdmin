package com.geekutil.modules.sys.mapper;

import com.geekutil.modules.sys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getListByRoleIds(@Param("roleList") List<Integer> roleList);

    List<Permission> getListByRoleId(@Param("id") Long id);
}
