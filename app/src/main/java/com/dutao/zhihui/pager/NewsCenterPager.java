package com.dutao.zhihui.pager;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dutao.zhihui.MainActivity;
import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BasePager;
import com.dutao.zhihui.bean.NewsCenter;
import com.dutao.zhihui.constants.UrlConstants;
import com.dutao.zhihui.fragment.MenuFragment;
import com.dutao.zhihui.util.GsonUtil;
import com.dutao.zhihui.util.SharepreferenceUtil;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

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
        //先查看本地是否有数据，有的话先从本地取出进行加载，没有再向网络发送请求
        String result = SharepreferenceUtil.getDataFromSp(context, UrlConstants.NEWS_CENTER_CATEGORIES, null);
        if(TextUtils.isEmpty(result)){
            getNewsCenterData();
        }else{
            processData(result);
        }

    }

    private void getNewsCenterData() {
        RequestParams requestParams = new RequestParams(UrlConstants.BASE_ENCODING);
        requestParams.addBodyParameter("key","value");
        getDataFromUrl(HttpRequest.HttpMethod.GET, UrlConstants.NEWS_CENTER_CATEGORIES, requestParams, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                processData(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * 公用处理数据方法
     * @param result
     */
    private void processData(String result) {
        NewsCenter newsCenter = GsonUtil.json2Bean(result,NewsCenter.class);
        List<String> tittleList = new ArrayList<String>();
        for (int i = 0; i < newsCenter.data.size(); i++) {
            tittleList.add(newsCenter.data.get(i).tittle);
        }
        MenuFragment menuFragment = (MenuFragment)((MainActivity) context).switchFragment("MENU");
        menuFragment.initMenu(tittleList);
    }
}
