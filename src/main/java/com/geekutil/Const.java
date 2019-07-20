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
}
