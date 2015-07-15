package com.dutao.zhihui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends LazyViewPager {

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyViewPager(Context context) {
		super(context);
	}

	/**
	 * 不进行拦截，使得viewpager不能自己左右滑动
	 * @param ev
	 * @return
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}
}
