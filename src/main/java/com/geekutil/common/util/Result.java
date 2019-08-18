package com.geekutil.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Asens
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private final static String STATUS = "status";
    private final static String MESSAGE = "message";
    private final static String RESULT = "result";
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    public Result() {
        put(STATUS, SUCCESS);
        put(MESSAGE, "");
    }

    public boolean isSuccess() {
        return super.get(STATUS).equals(SUCCESS);
    }

    public String getMessage() {
        return super.get(MESSAGE).toString();
    }

    public static Result error() {
        return error(ERROR, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(ERROR, msg);
    }

    public static Result error(String code, String msg) {
        Result r = new Result();
        r.put(STATUS, code);
        r.put(MESSAGE, msg);
        return r;
    }

    public static Result success(String msg) {
        Result r = new Result();
        r.put(MESSAGE, msg);
        return r;
    }

    public Result result(Object o) {
        super.put(RESULT, o);
        return this;
    }

    public static Result success(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result success(String key, Object value) {
        Result r = new Result();
        r.put(key, value);
        return r;
    }

    public static Result success() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}