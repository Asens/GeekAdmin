package com.geekutil.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekutil.Const;
import com.geekutil.modules.sys.entity.User;
import com.geekutil.modules.sys.mapper.UserMapper;
import com.geekutil.modules.sys.service.UserService;
import freemarker.template.SimpleHash;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Objects;

/**
 * @author Asens
 * create 2019-07-17 22:04
 **/
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Long checkUser(String username, String password) {
        User user= findByUsername(username);
        if (user==null) {
            return null;
        }
        if (Objects.equals(DigestUtils.md5DigestAsHex((password+Const.USER_SALT).getBytes())
                ,user.getPassword())) {
            return user.getId();
        }
        return null;
    }

    @Override
    public String createToken(Long userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(now)
                .claim(Const.USER_ID_KEY, userId)
                .claim(Const.EXPIRE_KEY, System.currentTimeMillis() +
                        Const.TOKEN_EXPIRY_TIME * 1000)
                .setIssuer("lwl")
                .signWith(signatureAlgorithm, key());
        String jwt = builder.compact();
        log.info("jwt:" + jwt);
        return jwt;
    }

    @Override
    public Long getUserIdByToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(key())
                    .parseClaimsJws(token)
                    .getBody();
            if (!claims.containsKey(Const.USER_ID_KEY)) {
                return null;
            }

            if (System.currentTimeMillis() > Long.parseLong(claims.get(Const.EXPIRE_KEY).toString())) {
                return null;
            }

            return Long.valueOf(claims.get(Const.USER_ID_KEY).toString());
        }catch (Exception e){
            log.error("token [{}] is error because : {}",
                    token,e.getMessage());
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        return getOne(new QueryWrapper<User>().lambda().eq(User::getUsername,username));
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return Objects.equals(DigestUtils.md5DigestAsHex((password+Const.USER_SALT).getBytes()),
                user.getPassword());
    }

    private SecretKey key() {
        byte[] encodedKey = Base64.decodeBase64(
                Const.secret);
        return new SecretKeySpec(encodedKey, 0,
                encodedKey.length, "AES");
    }
}
