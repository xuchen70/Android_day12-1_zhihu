package com.android.day12_1_zhihu.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.day12_1_zhihu.R;

public class CustomHead extends FrameLayout {
	private TextView txt_title;

	public CustomHead(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(getContext()).inflate(R.layout.custom_title,CustomHead.this);
		initUI();
	}

	private void initUI() {
		txt_title = (TextView) findViewById(R.id.txt_title_custom);

	}

	public void setTitle(String title) {
		txt_title.setText(title);
	}

}
