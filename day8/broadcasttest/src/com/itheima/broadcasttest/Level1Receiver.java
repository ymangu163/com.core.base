package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level1Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("ʡ�����ŵõ��������Ϣ��"+message);
		abortBroadcast();
		setResultData("��ũ���ֵܷ�5000��Ǯ");
	}

}
