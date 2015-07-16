package com.dutao.zhihui.base;

import android.content.Context;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * com.dutao.zhihui.base
 * Created by dutao on 2015/7/14.
 */
public abstract class BasePager {
    public Context context;
    public View view;

    /**
     * 构造方法-调用initView方法返回对应View
     * @param context   上下文参数
     */
    public BasePager(Context context) {
        this.context = context;
        view = initView();
    }

    /**
     * 暴露一个获取view的方法
     * @return  对应view
     */
    public View getRootView(){
        return view;
    }

    /**
     * 抽象初始化UI方法，需子类实现
     * @return  对应view
     */
    public abstract View initView();

    /**
     * 抽象初始化数据方法，需子类实现
     */
    public abstract void initData();

    /**
     * 通用发送请求方法封装
     * @param method    请求方法
     * @param url       访问地址
     * @param params    参数设置
     * @param callBack  回调
     */
    public void getDataFromUrl(HttpRequest.HttpMethod method,String url,RequestParams params, RequestCallBack<String> callBack){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(method,url,params,callBack);
    }
}
