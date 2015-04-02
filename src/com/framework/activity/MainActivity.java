package com.framework.activity;

import java.util.HashMap;
import java.util.Map;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.framework.R;
import com.framework.bean.Test;
import com.framework.dao.TestDAO;
import com.framework.request.ImageRequest;
import com.framework.request.StringRequest;
import com.framework.util.VolleyTools;

/**
 * <b>AndroidAnnotation's usage:</b><br/>
 * http://blog.csdn.net/limb99/article/details/9067827<br/>
 * <b>You also can study it by:</b><br/>
 * https://github.com/excilys/androidannotations/wiki<br/>
 * <br/>
 * <b>Volley's usage:</b><br/>
 * You can get it by the example below.<br/>
 * <br/>
 * 
 * @author Jerry
 *
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
	String url;
	@ViewById(R.id.test)
	TextView textView;
	@ViewById(R.id.image)
	NetworkImageView imageView;
	@ViewById(R.id.imageview)
	ImageView img;

	VolleyTools tool;

	// AfterViewsע�Ͷ���ķ�������OnCreate������setContentView��ִ��
	@AfterViews
	void ini() {

		dao = new TestDAO(this);

		// �õ�VolleyTools��ʵ�����Ѷ�����Application����BaseActivity��ȡ��
		tool = getVolleyTools();

		// ��������
		// post
		url = "http://114.215.178.134:8080/tyc/app/Product_getProducts";
		Test value = new Test(1, 2);

		Map<String, String> param = new HashMap<String, String>();
		param.put("input", getGson().toJson(value));
		tool.getQueue().add(new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				textView.setText(arg0);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println(arg0.toString());
			}
		}, param));

		// get
		url = "http://114.215.178.134:8080/tyc/app/Product_getProducts?input={%22state%22:1,%22userprofileId%22:2}";
		tool.getQueue().add(new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
				textView.setText(arg0);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				System.out.println(arg0.toString());
			}
		}));

		// ͼƬ����
		url = "http://news.baidu.com/resource/img/logo_news_137_46.png";
		// ����һ��ImageRequest�ܹ�������ͼƬ
		tool.getQueue().add(new ImageRequest(url, new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				// img.setImageBitmap(arg0);
			}
		}, 300, 200, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.toString());
			}
		}));

		// ����������Ӧimageview�󶨶�Ӧ������ͼƬ
		// tool.getImageLoader().get(url, ImageLoader.getImageListener(img,
		// R.drawable.ic_launcher, 0));

		// ��������ʹ��NetworkImageView
		imageView.setImageUrl(url, tool.getImageLoader());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}