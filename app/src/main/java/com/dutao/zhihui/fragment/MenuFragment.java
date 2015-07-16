package com.dutao.zhihui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

    public View layout_item_menu;

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
        lv_menu_news_center.setAdapter(new MyAdapter());
    }

    /**
     * ListView适配器
     */
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return tittleList.size();
        }

        @Override
        public Object getItem(int position) {
            return tittleList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
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
            viewHolder.tv_menu_item.setText(tittleList.get(position));
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
