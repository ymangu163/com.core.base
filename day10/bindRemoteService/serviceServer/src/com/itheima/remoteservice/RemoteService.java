package com.itheima.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class RemoteService extends Service {

	@Override
	public void onCreate() {
		System.out.println("Զ�̷��񱻴����ˡ�����");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		System.out.println("Զ�̷��������ˡ�");
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MiddlePerson();
	}

	private void methodInService(){
		System.out.println("����Զ�̷���ķ������ұ������ˡ�������");
	}
	
	//1.����һ���м���  Զ�̷���̳е���ipc��һ��ʵ����
	private class MiddlePerson extends IMiddlePerson.Stub{
		@Override
		public void callMethodInService() {
			methodInService();
		}
		
	}
	
}
