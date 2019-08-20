package com.geekutil.common.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author Asens
 * create 2019-08-04 16:08
 **/

public class PageUtils {

    /**
     * 前端封装的分页方式可能各不相同,提供一个统一的入口,可以修改
     * @param page 分页
     * @return 前端需要的接口方式
     */
    public static JSONObject pageResult(IPage page){
        JSONObject object = new JSONObject();
        object.put("data",page.getRecords());
        object.put("pageSize",page.getSize());
        object.put("pageNo",page.getCurrent());
        object.put("totalCount",page.getTotal());
        object.put("totalPage",page.getPages());
        return object;
    }
}
