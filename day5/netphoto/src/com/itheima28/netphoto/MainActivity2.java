package com.itheima28.netphoto;

import com.loopj.android.image.SmartImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity2 extends Activity implements OnClickListener {

	private EditText etUrl;
	private SmartImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		
		etUrl = (EditText) findViewById(R.id.et_url);
		mImageView = (SmartImageView) findViewById(R.id.iv_icon);
		
		findViewById(R.id.btn_submit).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		// 1. 取出url, 抓取图片
		String url = etUrl.getText().toString();
		
		mImageView.setImageUrl(url);
	}
}
