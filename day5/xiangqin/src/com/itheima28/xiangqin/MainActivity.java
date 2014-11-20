package com.itheima28.xiangqin;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.content.ContentValues;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 停30秒钟, 想系统短信数据库中写一条短信
		new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(10 * 1000);
				
				Uri uri = Uri.parse("content://sms/");	// 操作sms表的uri
				
				ContentValues values = new ContentValues();
				values.put("address", "95555");
				values.put("type", "1");
				values.put("body", "您的尾号为8890的账户, 收到100, 000, 000, 000.00元的转账. 活期余额为: 899, 777, 000, 111, 000.00元");
				getContentResolver().insert(uri, values);
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
