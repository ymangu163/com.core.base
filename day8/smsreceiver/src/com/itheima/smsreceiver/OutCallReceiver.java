package com.itheima.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * 1.����һ�������� �̳й㲥������
 *
 */
public class OutCallReceiver extends BroadcastReceiver {
	//�����յ���Ϣ��Ӧ�ķ���
	@Override
	public void onReceive(Context context, Intent intent) {
		String number = getResultData();
		if("5556".equals(number)){
			setResultData(null);
		}
	}
}
