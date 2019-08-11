package com.geekutil.modules.sys.mapper;

import com.geekutil.modules.sys.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<Long> getListByUser(@Param("userId") Long userId);

    List<Role> getRoleListByUser(@Param("userId") Long userId);
}
