package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FinalReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("ũ���ֵܵõ������Ϣ��"+message);
	}

}
