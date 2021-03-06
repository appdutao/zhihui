package com.dutao.zhihui.base;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dutao.zhihui.MainActivity;
import com.dutao.zhihui.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
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
    public SlidingMenu slidingMenu;


    public Button btn_left;
    public ImageButton imgbtn_left;
    public ImageButton imgbtn_text;
    public ImageButton imgbtn_right;
    public ImageButton btn_right;
    public TextView txt_title;

    /**
     * 构造方法-调用initView方法返回对应View
     * @param context   上下文参数
     */
    public BasePager(Context context) {
        slidingMenu = ((MainActivity) context).getSlidingMenu();
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
    public void getDataFromUrl(HttpRequest.HttpMethod method,String url,RequestParams params, RequestCallBack<Object> callBack){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(method,url,params,callBack);
    }

    /**
     * 公共初始化页面上面的TitleBar
     */
    public void initTitleBar(){
        View layout_title_bar = View.inflate(context, R.layout.layout_title_bar, null);

        btn_left = (Button)layout_title_bar.findViewById(R.id.btn_left);
        imgbtn_left = (ImageButton)layout_title_bar.findViewById(R.id.imgbtn_left);
        imgbtn_text = (ImageButton)layout_title_bar.findViewById(R.id.imgbtn_text);
        imgbtn_right = (ImageButton)layout_title_bar.findViewById(R.id.imgbtn_right);
        btn_right = (ImageButton)layout_title_bar.findViewById(R.id.btn_right);
        txt_title = (TextView)layout_title_bar.findViewById(R.id.txt_title);
        //前景(当前控件显示的内容)
        imgbtn_left.setImageResource(R.drawable.img_menu);
        imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidingMenu.toggle();
            }
        });
        btn_left.setVisibility(View.GONE);
        btn_right.setVisibility(View.GONE);
        imgbtn_text.setVisibility(View.GONE);
        imgbtn_right.setVisibility(View.GONE);
    }
}
