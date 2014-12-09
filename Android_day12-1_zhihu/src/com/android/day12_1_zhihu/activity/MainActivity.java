package com.android.day12_1_zhihu.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.android.day12_1_zhihu.R;
import com.android.day12_1_zhihu.adapter.MyPagerAdapter;
import com.android.day12_1_zhihu.cache.BitmapCache;
import com.android.day12_1_zhihu.custom.CustomHead;
import com.android.day12_1_zhihu.entity.Latest;
import com.android.day12_1_zhihu.entity.Stories;
import com.android.day12_1_zhihu.entity.TopStories;
import com.android.day12_1_zhihu.utils.AppFinal;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity implements Listener<JSONObject> {
	private final String TAG = "MainActivity";

	// �ؼ����
	private CustomHead cHead;
	private ViewPager vp;
	private TextView txt;
	private LinearLayout dotGroup;

	// �������
	private RequestQueue mQueue;
	private List<ImageView> imageviews;

	private Latest latest = null;
	private List<Stories> stories = null;
	private List<TopStories> topStories = null;

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
		mQueue.add(new JsonObjectRequest(Method.GET, AppFinal.LATEST, null,
				this, null));
	}

	public void onResponse(JSONObject response) {
		// ����json
		latest = new Latest();

		try {
			parseJson(response);
			initSildePic();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private void parseJson(JSONObject response) throws JSONException {

		String data = response.getString("date");
		
		JSONArray arrayStories = response.getJSONArray("stories");
		if (arrayStories != null && arrayStories.length() > 0) {
			stories = new ArrayList<Stories>();
			for (int i = 0; i < arrayStories.length(); i++) {
				
				JSONObject jsonObject = arrayStories.getJSONObject(i);
				if(jsonObject.has("theme_name")){
					
				}else{
					Stories storie = new Stories();
					storie.setGa_prefix(jsonObject.getString("ga_prefix"));
					storie.setId(jsonObject.getLong("id"));
					//��ȡͼƬ����
					JSONArray arrayImages = jsonObject.getJSONArray("images");
					if(arrayImages!=null&&arrayImages.length()>0){
						String[] img = new String[arrayImages.length()];
						for(int j=0;j<arrayImages.length();j++){
							img[j]=arrayImages.getString(j);
						}
						storie.setImages(img);
					}
					storie.setShare_url(jsonObject.getString("share_url"));
					storie.setTitle(jsonObject.getString("title"));
					storie.setType(jsonObject.getInt("type"));
					stories.add(storie);
				}
				
			}
		}
		
		//��װTopStores
		 JSONArray arrayTopStories = response.getJSONArray("top_stories");
		 if(arrayTopStories!=null&&arrayTopStories.length()>0){
			 topStories = new ArrayList<TopStories>();
			 for(int i=0;i<arrayTopStories.length();i++){
				 TopStories topStory = new TopStories();
				 JSONObject obj = arrayTopStories.getJSONObject(i);
				 topStory.setGa_prefix(obj.getString("ga_prefix"));
				 topStory.setId(obj.getLong("id"));
				 topStory.setShare_url(obj.getString("share_url"));
				 topStory.setTitle(obj.getString("type"));
				 topStory.setImage(obj.getString("image"));
				 topStory.setType(obj.getInt("type"));
				 topStories.add(topStory);
				 
			 }
		 }
		 
		 latest.setStory(stories);
		 latest.setTopStory(topStories);
	}

	/**
	 * ��ʼ��viewpager
	 */
	public void initSildePic(){
		imageviews = new ArrayList<ImageView>();
		for(int i=0;i<latest.getTopStory().size();i++){
			TopStories tStory = latest.getTopStory().get(i);
			NetworkImageView imageView = new NetworkImageView(getApplicationContext());
			imageView.setScaleType(ScaleType.CENTER_CROP);
			//���ñ���ͼƬ 
			imageView.setImageUrl(tStory.getImage(),new ImageLoader(mQueue, new BitmapCache()));
			Log.i(TAG, tStory.getImage());
			imageviews.add(imageView);
			MyPagerAdapter adapter = new MyPagerAdapter(imageviews);
			vp.setAdapter(adapter);
			
		}
	}
}
