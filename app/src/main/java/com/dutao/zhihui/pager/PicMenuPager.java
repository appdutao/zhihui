package com.dutao.zhihui.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dutao.zhihui.base.BasePager;
import com.dutao.zhihui.bean.NewsCenter;

/**
 * com.dutao.zhihui.pager
 * Created by dutao on 2015/7/17.
 */
public class PicMenuPager extends BasePager {

    public NewsCenter.NewsCenterItem newsCenterItem;

    public PicMenuPager(Context context,NewsCenter.NewsCenterItem newsCenterItem) {
        super(context);
        this.newsCenterItem = newsCenterItem;
    }

    /**
     * 抽象初始化UI方法，需子类实现
     *
     * @return 对应view
     */
    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("组图");
        return textView;
    }

    /**
     * 抽象初始化数据方法，需子类实现
     */
    @Override
    public void initData() {

    }
}
