package com.itheima.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("���ŵ����ˡ� ������");
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		for (Object obj : objs) {
			// �õ����Ŷ���
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			System.out.println("body:" + body);
			System.out.println("sender:" + sender);
			// ��ֹ����ǰ�Ĺ㲥��
			if ("5556".equals(sender)) {
				abortBroadcast();
			}
		}
	}

}
