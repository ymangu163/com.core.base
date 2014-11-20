package com.itheima.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("短信到来了。 。。。");
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for (Object obj : objs) {
			// 得到短信对象
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			System.out.println("body:" + body);
			System.out.println("sender:" + sender);
			// 终止掉当前的广播。
			if ("5556".equals(sender)) {
				abortBroadcast();
			}
		}
	}

}
