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
	 * ��������㲥
	 * @param view
	 */
	public void send1(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.broadcasttest.songwennuan");
		intent.putExtra("msg", "��1���");
		//����㲥�����ɱ����أ�������ֹ��
		sendBroadcast(intent);
	}
	/**
	 * ��������㲥
	 * @param view
	 */
	public void send2(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.broadcasttest.songwennuan");
		//����㲥���ɱ����أ�����ֹ�������޸����ݡ�
		sendOrderedBroadcast(intent, null, new FinalReceiver(), null, 0, "��ũ���ֵܷ�10000��Ǯ", null);
	}
}
