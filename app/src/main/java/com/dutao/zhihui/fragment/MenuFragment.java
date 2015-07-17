package com.dutao.zhihui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dutao.zhihui.MainActivity;
import com.dutao.zhihui.MyBaseAdapter;
import com.dutao.zhihui.R;
import com.dutao.zhihui.base.BaseFragment;
import com.dutao.zhihui.constants.Constants;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * com.dutao.zhihui.fragment
 * Created by dutao on 2015/7/10.
 */
public class MenuFragment extends BaseFragment {

    public BaseAdapter adapter;
    public List<String> titleList;

    @ViewInject(R.id.lv_menu_news_center)
    public ListView lv_menu_news_center;

    public View layout_item_menu;

    public int currentPosition = 0;//默认选中侧拉栏第一个标题

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
     * @param titleList    标题集合
     */
    public void initMenu(List<String> titleList,int radioPosition) {
        this.titleList = titleList;
        adapter = new MyAdapter(context,titleList);
        lv_menu_news_center.setAdapter(adapter);
        lv_menu_news_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                adapter.notifyDataSetChanged();

                HomeFragment homeFragment = (HomeFragment) ((MainActivity) context).switchFragment(Constants.TAG_HOME);
//                homeFragment.getPagerList().
            }
        });
    }

    /**
     * ListView适配器
     */
    class MyAdapter extends MyBaseAdapter<String>{

        public MyAdapter(Context context, List<String> list) {
            super(context, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.layout_item_menu, null);
                viewHolder.iv_menu_item = (ImageView)convertView.findViewById(R.id.iv_menu_item);
                viewHolder.tv_menu_item = (TextView)convertView.findViewById(R.id.tv_menu_item);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.tv_menu_item.setText(titleList.get(position));

            if (currentPosition == position){
                viewHolder.iv_menu_item.setImageResource(R.drawable.menu_arr_select);
                viewHolder.tv_menu_item.setTextColor(context.getResources().getColor(R.color.red));
                convertView.setBackgroundResource(R.drawable.menu_item_bg_select);
            }else{
                viewHolder.iv_menu_item.setImageResource(R.drawable.menu_arr_normal);
                viewHolder.tv_menu_item.setTextColor(context.getResources().getColor(R.color.white));
                convertView.setBackgroundResource(R.drawable.transparent);
            }
            return convertView;
        }
    }

    /**
     * ViewHolder，节省内存，防止重复find
     */
    class ViewHolder{
        public ImageView iv_menu_item;
        public TextView tv_menu_item;
    }
}
