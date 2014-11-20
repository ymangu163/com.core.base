package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FinalReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("农民兄弟得到乡的消息："+message);
	}

}
