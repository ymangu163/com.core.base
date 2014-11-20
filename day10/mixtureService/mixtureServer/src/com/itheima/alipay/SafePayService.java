package com.itheima.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class SafePayService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("���񱻰� onbind");
		return new MyBinder();
	}
	/**
	 * ��ȫ֧���ķ���
	 */
	private boolean pay(long time,String pwd,double money){
		if("123".equals(pwd)){
			return true;
		}else{
			return false;
		}
	}
	
	private class MyBinder extends ISafePay.Stub{
		/**
		 * ���ð�ȫ֧�����߼�
		 */
		@Override
		public boolean callPay(long time, String pwd, double money)
				throws RemoteException {
			return pay(time, pwd, money);
		}
		
	}
	
	@Override
	public void onCreate() {
		System.out.println("oncreate֧�������񱻴�����һֱ�ں�̨���У�����ֻ��İ�ȫ״̬");
		super.onCreate();
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("����onstart");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("onunbind");
		return super.onUnbind(intent);

	}
	@Override
	public void onDestroy() {
		System.out.println("ondestory֧������������");
		super.onDestroy();
	}
	
}
