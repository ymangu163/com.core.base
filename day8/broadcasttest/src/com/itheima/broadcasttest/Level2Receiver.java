package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level2Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("�м����ŵõ�ʡ������Ϣ��"+message);
		setResultData("��ũ���ֵܷ�2000��Ǯ");

	}

}
