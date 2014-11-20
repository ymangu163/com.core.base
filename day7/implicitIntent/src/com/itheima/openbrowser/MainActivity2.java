package com.itheima.openbrowser;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	
	public void click(View view){
//        <action android:name="android.intent.action.VIEW" />
//        <category android:name="android.intent.category.DEFAULT" />
//        <category android:name="android.intent.category.BROWSABLE" />
//        <data android:scheme="http" />
//        <data android:scheme="https" />
//        <data android:scheme="about" />
//        <data android:scheme="javascript" />
		Intent intent = new Intent();
		intent.setAction("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addCategory("android.intent.category.BROWSABLE");
		intent.setData(Uri.parse("http://www.baidu.com"));
		startActivity(intent);
	}

}
