package com.dutao.zhihui.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/7/15.
 */
public class NewsCenter {
    public List<NewsCenterItem> data;
    public List<String> extend;
    public String retcode;

    public class NewsCenterItem{
        public List<Chidren> children;
        public Integer id;
        public String title;
        public Integer type;
        public String url;
        public String url1;
        public String dayurl;
        public String excurl;
        public String weekurl;
    }

    public class Chidren{
        public Integer id;
        public String title;
        public Integer type;
        public String url;
    }
}
