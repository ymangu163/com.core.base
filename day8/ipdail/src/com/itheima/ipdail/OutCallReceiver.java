package com.itheima.ipdail;

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
		System.out.println("�������е绰���ȥ��"+number);
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String ipnumber = sp.getString("ipnumber", "");
		//�ж��Ƿ��ǳ�;���Ƿ���ǰ׺
		setResultData(ipnumber+number);
	}
}
