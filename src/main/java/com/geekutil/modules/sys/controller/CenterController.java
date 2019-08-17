package com.geekutil.modules.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekutil.common.auth.Auth;
import com.geekutil.common.util.FrontUtils;
import com.geekutil.common.util.Result;
import com.geekutil.common.validate.UniqueUsernameValidator;
import com.geekutil.common.validate.ValidateUtils;
import com.geekutil.common.validate.group.AddGroup;
import com.geekutil.common.validate.group.UpdateGroup;
import com.geekutil.modules.sys.entity.Role;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.UserRole;
import com.geekutil.modules.sys.entity.dto.UserDTO;
import com.geekutil.modules.sys.service.RoleService;
import com.geekutil.modules.sys.service.UserRoleService;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

import static com.geekutil.common.util.PageUtils.pageResult;
import static com.geekutil.common.validate.ValidateUtils.validator;
import static java.util.stream.Collectors.toSet;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Asens
 * @since 2019-07-20
 */
@RestController
@RequestMapping("/api/sys/center")
@Log4j2
public class CenterController {
    @Resource
    private UserService userService;

    /**
     * 用户中心设置
     */
    @GetMapping("/info")
    public Object list() {
        Long userId = FrontUtils.getCurrentUserId();
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error();
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO, "password");
        return Result.success("result",userDTO);
    }
}
