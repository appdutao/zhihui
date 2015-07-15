package com.dutao.zhihui.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BasePager;
import com.dutao.zhihui.bean.NewsCenter;
import com.dutao.zhihui.constants.UrlConstants;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * com.dutao.zhihui.pager
 * Created by dutao on 2015/7/14.
 */
public class NewsCenterPager extends BasePager {
    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText(R.string.tab_news_center);
        return textView;
    }

    @Override
    public void initData() {
        RequestParams requestParams = new RequestParams(UrlConstants.BASE_ENCODING);
        requestParams.addBodyParameter("key","value");
        getDataFromUrl(HttpRequest.HttpMethod.GET, UrlConstants.NEWS_CENTER_CATEGORIES, requestParams, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Gson gson = new Gson();
                NewsCenter newsCenter = gson.fromJson((String)responseInfo.result, NewsCenter.class);
                System.out.print(newsCenter.toString());
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
