package com.dutao.zhihui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 抽取ListView公用部分，子类只需实现getView方法即可
 * com.dutao.zhihui
 * Created by dutao on 2015/7/17.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public Context context;
    public List<T> list;

    public MyBaseAdapter(Context context,List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
