package com.itheima28.contentobserverdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// ����ϵͳ����
		
		ContentResolver resolver = getContentResolver();
		
		// ע��һ�����ݹ۲��߹۲�������ݿ�
		resolver.registerContentObserver(Uri.parse("content://sms/"), true, new MyContentObserver(new Handler()));
	}
	
	/**
	 * @author andong
	 * ���ݹ۲���
	 */
	class MyContentObserver extends ContentObserver {

		private static final String TAG = "MyContentObserver";

		public MyContentObserver(Handler handler) {
			super(handler);
		}

		/**
		 * �������������ݷ����ı�ʱ�ص�
		 */
		@Override
		public void onChange(boolean selfChange) {
			Log.i(TAG, "���Ÿı���");
			Uri uri = Uri.parse("content://sms/outbox");	// �������uri
			
			// ��ѯ�����������
			Cursor cursor = getContentResolver().query(uri, new String[]{"address", "date", "body"}, null, null, null);
			if(cursor != null && cursor.getCount() > 0) {
				
				String address;
				long date;
				String body;
				while(cursor.moveToNext()) {
					address = cursor.getString(0);
					date = cursor.getLong(1);
					body = cursor.getString(2);
					
					Log.i(TAG, "����: " + address + ", ����: " + date + ", ����: " + body);
				}
				cursor.close();
			}
		}
	}
}
