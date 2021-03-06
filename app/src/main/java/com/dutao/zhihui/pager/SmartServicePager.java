package com.dutao.zhihui.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BasePager;

/**
 * com.dutao.zhihui.pager
 * Created by dutao on 2015/7/14.
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Context context) {
        super(context);
    }

    /**
     * 抽象初始化数据方法，需子类实现
     */
    @Override
    public void initData() {

    }

    /**
     * 抽象初始化UI方法，需子类实现
     *
     * @return 对应view
     */
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText(R.string.tab_smart_service);
        return textView;
    }
}
