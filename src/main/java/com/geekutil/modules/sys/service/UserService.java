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
     * 创建授权令牌
     * @param userId 用户id
     * @return 创建的token
     */
    String createToken(Long userId);


    /**
     * 通过token获取登录用户
     * @param token token
     * @return 用户id
     */
    Long getUserIdByToken(String token);

    /**
     * 查找用户
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);

    /**
     * 检查用户名密码
     * @param user 用户
     * @param password 输入密码
     * @return 正确返回true
     */
    boolean checkPassword(User user, String password);

    /**
     * 保存或更新,根据id
     * @param userDTO 编辑的user信息
     */
    void saveOrUpdateUser(UserDTO userDTO);


    /**
     * 授予角色
     * @param id 用户id
     * @param roles 角色id集合
     */
    void doAuth(Long id, Long[] roles);
}
