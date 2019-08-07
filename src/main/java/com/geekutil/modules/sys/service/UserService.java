package com.geekutil.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.entity.dto.UserDTO;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/

public interface UserService extends IService<User> {

    /**
     * 验证账号密码是否正确(验证成功返回用户id,验证失败返回null)
     */
    Long checkUser(String username, String password);

    /**
     * 创建授权令牌
     */
    String createToken(Long userId);

    /**
     * 通过token获取登录用户
     */
    Long getUserIdByToken(String token);

    User findByUsername(String username);

    boolean checkPassword(User user, String password);

    /**
     * 保存或更新,根据id
     * @param userDTO 编辑的user信息
     */
    void saveOrUpdateUser(UserDTO userDTO);
}
