package com.dutao.zhihui.util;

import com.google.gson.Gson;

/**
 * com.dutao.zhihui.util
 * Created by dutao on 2015/7/16.
 */
public class GsonUtil {

    public static <T> T json2Bean(String json,Class<T> clazz){
        Gson gson = new Gson();
         return gson.fromJson(json, clazz);
    }

}
