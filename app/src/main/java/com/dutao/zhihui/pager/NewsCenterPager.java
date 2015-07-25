package com.dutao.zhihui.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dutao.zhihui.MainActivity;
import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BasePager;
import com.dutao.zhihui.bean.NewsCenter;
import com.dutao.zhihui.constants.Constants;
import com.dutao.zhihui.constants.UrlConstants;
import com.dutao.zhihui.fragment.MenuFragment;
import com.dutao.zhihui.util.GsonUtil;
import com.dutao.zhihui.util.SharepreferenceUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dutao.zhihui.pager
 * Created by dutao on 2015/7/14.
 */
public class NewsCenterPager extends BasePager {
    public List<BasePager> menuPagersList;
    private NewsCenter newsCenter;
    public List<String> tittleList;//侧拉栏标题集合


    @ViewInject(R.id.news_center_fl)
    private FrameLayout news_center_fl;

    public NewsCenterPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        view = (RelativeLayout) View.inflate(context, R.layout.news_center_frame, null);
        ViewUtils.inject(this,view);
        initTitleBar();
//        TextView textView = new TextView(context);
//        textView.setText(R.string.tab_news_center);
        return view;
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

    /**
     * 第一次获取的数据
     * 【新闻中心的JSON数据，解析后得到左侧侧拉栏以及主页面顶部数据】
     */
    private void getNewsCenterData() {
        RequestParams requestParams = new RequestParams(UrlConstants.BASE_ENCODING);
        requestParams.addBodyParameter("key","value");
        getDataFromUrl(HttpRequest.HttpMethod.GET, UrlConstants.NEWS_CENTER_CATEGORIES, null, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                SharepreferenceUtil.saveData2Sp(context, UrlConstants.NEWS_CENTER_CATEGORIES, (String) responseInfo.result);
                processData((String)responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("FAIL",s);
            }
        });
    }
    /**
     * 公用处理数据方法
     * 1.初始化左侧侧拉栏Menu 2.默认点击左侧侧拉栏第一个栏目，主页面显示Title以及下面的FramLayout
     * @param result
     */
    public void processData(String result) {
        newsCenter = GsonUtil.json2Bean(result, NewsCenter.class);
        tittleList = new ArrayList<String>();
        for (int i = 0; i < newsCenter.data.size(); i++) {
            tittleList.add(newsCenter.data.get(i).title);
        }
        MenuFragment menuFragment = (MenuFragment)((MainActivity) context).switchFragment(Constants.TAG_MENU);
        menuFragment.initMenu(tittleList,1);

        menuPagersList = new ArrayList<BasePager>();
        menuPagersList.add(new NewsMenuPager(context,newsCenter.data.get(0)));
        menuPagersList.add(new PicMenuPager(context,newsCenter.data.get(1)));
        menuPagersList.add(new TopicMenuPager(context, newsCenter.data.get(2)));
        menuPagersList.add(new IntMenuPager(context, newsCenter.data.get(3)));

        getMenuPager(0);//默认显示第0个的新闻
    }

    /**
     * 获取对应的getMenuPager
     * @return  当前的MenuPagerList
     */
    public void getMenuPager(int position){
        //设置页面TitleBar
        txt_title.setText(tittleList.get(position));

        news_center_fl.removeAllViews();
        news_center_fl.addView(menuPagersList.get(position).getRootView());
        menuPagersList.get(position).initData();
    }
}
