package com.geekutil.common.util;

/**
 * @author Asens
 */

public class ExceptionUtils {

    public static String exceptionDetail(Throwable ex){
        StringBuilder result = new StringBuilder();
        result.append(ex.toString()).append("<br>");
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            result.append("\tat ").append(s).append("<br>");
        }
        return result.toString();
    }
}
