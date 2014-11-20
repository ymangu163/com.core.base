package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level1Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("省级部门得到中央的消息："+message);
		abortBroadcast();
		setResultData("给农民兄弟发5000块钱");
	}

}
