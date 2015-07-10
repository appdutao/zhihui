package com.dutao.zhihui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dutao.zhihui.base.BaseFragment;

/**
 * com.dutao.zhihui.fragment
 * Created by dutao on 2015/7/10.
 */
public class MenuFragment extends BaseFragment {

    @Override
    public View initView(LayoutInflater inflater) {
        TextView textView = new TextView(getActivity());
        textView.setText("侧拉菜单");
        return textView;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
