package com.geekutil.modules.sys.controller;

import com.geekutil.common.util.Result;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.dto.UserDTO;
import com.geekutil.modules.sys.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Asens
 */

@RestController
@RequestMapping("/api")
@Log4j2
public class LoginController {
    @Resource
    private UserService userService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> paramMap) {
        String username = paramMap.get("username");
        String password = paramMap.get("password");
        log.info("username : [{}], password:[{}]", username, password);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.error();
        }
        User user = userService.findByUsername(username);
        if (user != null && userService.checkPassword(user, password)) {
            String token = userService.createToken(user.getId());
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO, "password");
            userDTO.setToken(token);
            return Result.success().data(userDTO);
        }
        return Result.error();
    }
}
