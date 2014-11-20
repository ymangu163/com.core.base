package com.itheima.servicelife;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	//2.ʵ�ַ���ɹ��󶨵Ĵ��� ������һ���м��ˡ�
	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("���񱻳ɹ����ˡ�������");
		return new MiddlePerson();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("onunbind");
		return super.onUnbind(intent);
	}
	
	@Override
	public void onCreate() {
		System.out.println("oncreate");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onstartcommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		System.out.println("ondestory");
		super.onDestroy();
	}
	
	
	
	
	/**
	 * ���Ƿ��������һ������
	 */
	public void methodInService(){
		Toast.makeText(this, "�������������������ס֤��", 0).show();
	}
	
	//1.��һ������Ҫ��¶���� ����Ҫ��һ���м���
	private class MiddlePerson extends Binder implements IMiddlePerson{
		/**
		 * ������ס֤
		 * @param money ��Ǯ 50��Ǯ���ϲŸ��졣
		 */
		public void callMethodInService(int money){
			if(money>=50){
				methodInService();
			}else{
				Toast.makeText(getApplicationContext(), "��׼����Ǯ��", 0).show();
			}
		}
		/**
		 * ���쵼���齫
		 */
		public void playMajiang(){
			System.out.println("���쵼���齫��");
		}
	}
}
