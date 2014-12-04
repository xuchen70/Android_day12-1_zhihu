package com.android.day12_1_zhihu.activity;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.day12_1_zhihu.R;
import com.android.day12_1_zhihu.custom.CustomHead;
import com.android.day12_1_zhihu.utils.AppFinal;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
	private final String TAG ="MainActivity";
	
	//�ؼ����
	private CustomHead cHead;
	private ViewPager vp;
	private TextView txt;
	private LinearLayout dotGroup;
	
	//�������
	private RequestQueue mQueue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
		
		initData();
	}
	
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void initUI() {
		cHead = (CustomHead) findViewById(R.id.head);
		cHead.setTitle("��ҳ");
		
		vp = (ViewPager) findViewById(R.id.viewpage_forpic_main);
		
		txt = (TextView) findViewById(R.id.txt_imgtitle_vp);
		dotGroup = (LinearLayout) findViewById(R.id.dotGroup);
	}
	
	/**
	 * ��ʼ������
	 */
	private void initData() {
		
		mQueue = Volley.newRequestQueue(getApplicationContext());
		mQueue.add(new JsonObjectRequest(Method.GET, AppFinal.LATEST, null, new Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				Log.i(TAG,response.toString());
			}
		}, null));
	}

}
