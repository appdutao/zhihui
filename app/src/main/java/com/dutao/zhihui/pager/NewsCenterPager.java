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

    }
}
