package com.itheima.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * 1.创建一个收音机 继承广播接受者
 *
 */
public class OutCallReceiver extends BroadcastReceiver {
	//当接收到消息对应的方法
	@Override
	public void onReceive(Context context, Intent intent) {
		String number = getResultData();
		if("5556".equals(number)){
			setResultData(null);
		}
	}
}
