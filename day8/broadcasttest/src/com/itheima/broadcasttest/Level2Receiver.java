package com.itheima.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Level2Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = getResultData();
		System.out.println("市级部门得到省级的消息："+message);
		setResultData("给农民兄弟发2000块钱");

	}

}
