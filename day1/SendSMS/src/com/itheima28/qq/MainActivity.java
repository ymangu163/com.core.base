package com.itheima28.qq;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ȥ������, ������setContentView ����ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);	// ȡ������
		
		setContentView(R.layout.activity_main);
		
		
		// ����һ�����߳�. while(true) ѭ�����Ͷ���
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
//				while(true) {
					// ѭ�����Ͷ���

//					Thread.sleep(1000);
					
					SystemClock.sleep(5000);
					
					SmsManager smsManager = SmsManager.getDefault();		// ���Ź�����
					smsManager.sendTextMessage(
							"18511619290", 	// �ռ��˵ĺ���
							null,			// �������ĺ��� 
							"����С����, ������ɢ.", 
							null, 	// ������ͳɹ�, �ص��˹㲥, ֪ͨ����.
							null);	// ���Է����ճɹ�, �ص��˹㲥.
//				}
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
