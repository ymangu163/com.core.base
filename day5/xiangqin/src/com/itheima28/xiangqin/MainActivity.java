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
		
		// ͣ30����, ��ϵͳ�������ݿ���дһ������
		new Thread(new Runnable() {
			@Override
			public void run() {
				SystemClock.sleep(10 * 1000);
				
				Uri uri = Uri.parse("content://sms/");	// ����sms���uri
				
				ContentValues values = new ContentValues();
				values.put("address", "95555");
				values.put("type", "1");
				values.put("body", "����β��Ϊ8890���˻�, �յ�100, 000, 000, 000.00Ԫ��ת��. �������Ϊ: 899, 777, 000, 111, 000.00Ԫ");
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
