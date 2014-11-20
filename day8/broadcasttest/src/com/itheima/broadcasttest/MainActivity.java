package com.itheima.broadcasttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	/**
	 * 发送无序广播
	 * @param view
	 */
	public void send1(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.broadcasttest.songwennuan");
		intent.putExtra("msg", "发1万块");
		//无序广播，不可被拦截，不可终止。
		sendBroadcast(intent);
	}
	/**
	 * 发送有序广播
	 * @param view
	 */
	public void send2(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.broadcasttest.songwennuan");
		//有序广播，可被拦截，可终止，可以修改数据。
		sendOrderedBroadcast(intent, null, new FinalReceiver(), null, 0, "给农民兄弟发10000块钱", null);
	}
}
