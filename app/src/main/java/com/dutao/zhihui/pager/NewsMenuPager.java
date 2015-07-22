package com.dutao.zhihui.pager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BasePager;
import com.dutao.zhihui.bean.NewsCenter;
import com.dutao.zhihui.view.pagerindicator.TabPageIndicator;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dutao.zhihui.pager
 * Created by dutao on 2015/7/17.
 */
public class NewsMenuPager extends BasePager {

    public NewsCenter.NewsCenterItem newsCenterItem;//传递过来的数据
    public List<BasePager> newsContentPageList;//存放每个小标题下对应的BasePager
    public List<String> newsContentTitleList;//存放滑动小标题

    private MyViewPagerAdapter pagerAdapter;

    @ViewInject(R.id.indicator)
    private TabPageIndicator indicator;
    @ViewInject(R.id.pager)
    private ViewPager pager;

    public NewsMenuPager(Context context,NewsCenter.NewsCenterItem newsCenterItem) {
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
        view = View.inflate(context, R.layout.frag_news,null);
        ViewUtils.inject(this,view);
//        TextView textView = new TextView(context);
//        textView.setText("新闻");
        return view;
    }

    /**
     * 抽象初始化数据方法，需子类实现
     */
    @Override
    public void initData() {
        newsContentPageList = new ArrayList<BasePager>();
        newsContentTitleList = new ArrayList<String>();
        for (int i = 0; i < newsCenterItem.children.size(); i++) {
            newsContentPageList.add(new NewsContentPager(context,newsCenterItem.children.get(i).url));
            newsContentTitleList.add(newsCenterItem.children.get(i).title);
        }
        pager = (ViewPager)view.findViewById(R.id.pager);
        if(pagerAdapter == null){
            pagerAdapter = new MyViewPagerAdapter();
            pager.setAdapter(pagerAdapter);
        }else{
            pagerAdapter.notifyDataSetChanged();
        }

        indicator.setViewPager(pager);

        indicator.setCurrentItem(0);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
                indicator.setCurrentItem(position);
                BasePager basePager = newsContentPageList.get(position);
                basePager.initData();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        BasePager basePager = newsContentPageList.get(0);
        basePager.initData();

    }

    private class MyViewPagerAdapter extends PagerAdapter{

        //返回指针对应页面的标题头目
        @Override
        public CharSequence getPageTitle(int position) {
            return newsContentTitleList.get(position);
        }

        @Override
        public int getCount() {
            return newsContentPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(newsContentPageList.get(position).getRootView());
            return newsContentPageList.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View)object);
        }
    }
}
