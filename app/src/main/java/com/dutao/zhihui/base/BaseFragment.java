package com.dutao.zhihui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutao.zhihui.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * com.dutao.zhihui.base
 * Created by dutao on 2015/7/10.
 */
public abstract class BaseFragment extends Fragment{

    public View view;
    public Context context;
    public SlidingMenu slidingMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        slidingMenu = ((MainActivity) context).getSlidingMenu();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    public abstract View initView(LayoutInflater inflater);
    public abstract void initData(Bundle savedInstanceState);
}
