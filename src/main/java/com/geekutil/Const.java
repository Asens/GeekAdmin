package com.geekutil;

/**
 * @author Asens
 * create 2019-07-17 22:55
 **/

public interface Const {

    /**
     * 对于一般只有0,1或null的数值型状态，统一使用
     */
    int DATABASE_INTEGER_YES = 1;
    int DATABASE_INTEGER_NO = 0;
    int DATABASE_INTEGER_NULL = -1;

    String SUCCESS = "success";
    String ERROR = "error";

    String USER_ID_KEY = "userId";
    String EXPIRE_KEY = "expire";
    String USER_SALT = "user_salt  ";
    String SECRET = "1d1d23d2rt23f23f";
    int TOKEN_EXPIRY_TIME = 7 * 24 * 60 * 60;

    String CURRENT_USER_ID = "CURRENT_USER_ID";
}
