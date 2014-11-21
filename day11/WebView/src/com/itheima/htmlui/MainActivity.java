package com.itheima.htmlui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView wv = (WebView) findViewById(R.id.wv);
		wv.loadUrl("http://192.168.1.100:8080/haha.html");
	}


}
