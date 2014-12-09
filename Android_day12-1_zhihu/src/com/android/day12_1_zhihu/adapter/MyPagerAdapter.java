package com.android.day12_1_zhihu.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPagerAdapter extends PagerAdapter{
	private List<ImageView> images;
//	private Context context;
	
	public MyPagerAdapter(List<ImageView> images){
//		this.context=context;
		this.images=images;
	}
	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(images.get(position));
		return images.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(images.get(position));
	}


}
