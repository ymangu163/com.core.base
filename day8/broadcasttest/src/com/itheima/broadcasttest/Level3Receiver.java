package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level3Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("�缶���ŵõ��е���Ϣ��"+message);
		setResultData("��ũ���ֵܷ��������");

	}

}
