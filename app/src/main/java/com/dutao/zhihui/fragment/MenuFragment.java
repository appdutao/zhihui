package com.dutao.zhihui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * com.dutao.zhihui.fragment
 * Created by dutao on 2015/7/10.
 */
public class MenuFragment extends BaseFragment {

    public List<String> tittleList;

    @ViewInject(R.id.lv_menu_news_center)
    public ListView lv_menu_news_center;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.layout_left_menu,null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    /**
     * 根据数据加载对应侧拉栏标题
     * @param tittleList    标题集合
     */
    public void initMenu(List<String> tittleList) {
        this.tittleList = tittleList;
    }
}
