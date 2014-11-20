package com.itheima.ipdail;

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
		System.out.println("哈哈，有电话打出去了"+number);
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String ipnumber = sp.getString("ipnumber", "");
		//判断是否是长途。是否有前缀
		setResultData(ipnumber+number);
	}
}
