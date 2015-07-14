package com.dutao.zhihui;

import android.os.Bundle;

import com.dutao.zhihui.base.BaseFragment;
import com.dutao.zhihui.fragment.HomeFragment;
import com.dutao.zhihui.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        setBehindContentView(R.layout.menu_frame);

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);

        BaseFragment menuFragment = new MenuFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.menu,menuFragment,"MENU")
                .commit();


        BaseFragment homeFragment = new HomeFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame,homeFragment,"HOME")
                .commit();
    }

}
