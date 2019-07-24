package com.geekutil.common.util;

import com.geekutil.Const;

/**
 * @author Asens
 * create 2019-07-21 16:22
 **/

public class FrontUtils {
    public static void setCurrentUserId(Long userId){
        HttpUtils.getRequest().setAttribute(Const.CURRENT_USER_ID,userId);
    }

    public static Long getCurrentUserId(){
        Object o = HttpUtils.getRequest().getAttribute(Const.CURRENT_USER_ID);
        try{
            return Long.valueOf(o.toString());
        }catch (Throwable t){
            return null;
        }
    }
}
