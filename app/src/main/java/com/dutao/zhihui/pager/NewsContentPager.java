package com.dutao.zhihui.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dutao.zhihui.base.BasePager;

/**
 * 每个小标题下页面Pager
 * com.dutao.zhihui.pager
 * Created by dutao on 2015/7/22.
 */
public class NewsContentPager extends BasePager {

    public String url;
    private TextView textView;

    public NewsContentPager(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        return textView;
    }

    @Override
    public void initData() {

        textView.setText(url);
    }
}
