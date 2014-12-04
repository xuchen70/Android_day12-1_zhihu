package com.android.day12_1_zhihu.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;

import com.android.day12_1_zhihu.R;
import com.android.day12_1_zhihu.cache.BitmapCache;
import com.android.day12_1_zhihu.utils.AppFinal;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class WelcomeActivity extends Activity implements AnimationListener{
	//控件相关
	private NetworkImageView imgStart;
	
	private RequestQueue mQueue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		initData();
	}


	private void initView() {
		imgStart = (NetworkImageView) findViewById(R.id.imgs_welcome);
	}
	

	private void initData() {
		mQueue=Volley.newRequestQueue(getApplicationContext());
		mQueue.add(new JsonObjectRequest(Method.GET, AppFinal.STARTIMAGE, null, new Listener<JSONObject>() {

			public void onResponse(JSONObject response) {
				try {
					String imgUrl = response.getString("img");
					imgStart.setImageUrl(imgUrl, new ImageLoader(mQueue, new BitmapCache()));
					
					//添加图片动画
					Animation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
					animation.setDuration(2000);
					
					animation.setFillAfter(true);//动画结束后停留在结束位置
					
					animation.setAnimationListener(WelcomeActivity.this);
					imgStart.startAnimation(animation);
					
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, null) );
	}


	@Override
	public void onAnimationEnd(Animation arg0) {
		
		startActivity(new Intent(this,MainActivity.class));
		finish();
	}


	@Override
	public void onAnimationRepeat(Animation arg0) {
		
	}


	@Override
	public void onAnimationStart(Animation arg0) {
		
	}
}
