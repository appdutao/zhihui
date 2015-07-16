package com.dutao.zhihui.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 操作SP的工具类
 * com.dutao.zhihui.util
 * Created by dutao on 2015/7/16.
 */
public class SharepreferenceUtil {

    public static final String SP_NAME = "config";
    public static SharedPreferences sharedPreferences;

    /**
     * 保存数据到sp
     * @param context   上下文环境
     * @param key       对应key
     * @param value     对应存入数据值
     */
    public static void saveData2Sp(Context context,String key,String value){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(SP_NAME,context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取SP中对应key的值
     * @param context   上下文环境
     * @param key       对应key
     * @param defValue  默认值
     * @return  对应key的value
     */
    public static String getDataFromSp(Context context,String key,String defValue){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences(SP_NAME,context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key,defValue);
    }
}
