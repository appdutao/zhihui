package com.dutao.zhihui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BaseFragment;
import com.dutao.zhihui.base.BasePager;
import com.dutao.zhihui.pager.FunctionPager;
import com.dutao.zhihui.pager.GovAffairsPager;
import com.dutao.zhihui.pager.NewsCenterPager;
import com.dutao.zhihui.pager.SettingPager;
import com.dutao.zhihui.pager.SmartServicePager;
import com.dutao.zhihui.view.LazyViewPager;
import com.dutao.zhihui.view.MyViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * com.dutao.zhihui.fragment
 * Created by dutao on 2015/7/10.
 */
public class HomeFragment extends BaseFragment {

    @ViewInject(R.id.layout_content)
    private MyViewPager layout_content;
    @ViewInject(R.id.main_radio)
    private RadioGroup main_radio;

    private List<BasePager> pagerList;
    private Context context;

    @Override
    public View initView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.frag_home, null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_function:
                        layout_content.setCurrentItem(0);
                        break;
                    case R.id.rb_news_center:
                        layout_content.setCurrentItem(1);
                        break;
                    case R.id.rb_smart_service:
                        layout_content.setCurrentItem(2);
                        break;
                    case R.id.rb_gov_affairs:
                        layout_content.setCurrentItem(3);
                        break;
                    case R.id.rb_setting:
                        layout_content.setCurrentItem(4);
                        break;
                }
            }
        });

        main_radio.check(R.id.rb_function);

        context = getActivity();
        pagerList = new ArrayList<BasePager>();
        pagerList.add(new FunctionPager(context));
        pagerList.add(new NewsCenterPager(context));
        pagerList.add(new SmartServicePager(context));
        pagerList.add(new GovAffairsPager(context));
        pagerList.add(new SettingPager(context));

        layout_content.setAdapter(new MyAdapter());

        layout_content.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BasePager basePager = pagerList.get(position);
                basePager.initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //默认展示新闻中心
        layout_content.setCurrentItem(1);
        pagerList.get(1).initData();
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((MyViewPager)container).addView(pagerList.get(position).getRootView());
            return pagerList.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((MyViewPager)container).removeView((View)object);
        }
    }

}
