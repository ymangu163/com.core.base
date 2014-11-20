package com.itheima.fish;

import com.itheima.alipay.ISafePay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ISafePay iSafePay;
	private MyConn conn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		Intent intent = new Intent();
//		intent.setAction("com.itheima.alipay");
//		startService(intent);
		//��֤�����ں�̨���С�
	
	}
	
	public void start(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.alipay");
		startService(intent);
	}
	
	public void stop(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.alipay");
		stopService(intent);
	}
	public void bind(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.alipay");
		conn = new MyConn();
		bindService(intent, conn, BIND_AUTO_CREATE);//�첽�Ĳ���
	}
	public void unbind(View view){
		unbindService(conn);
	}
	
	
	
	public void click(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.alipay");
		conn = new MyConn();
		bindService(intent, conn, BIND_AUTO_CREATE);//�첽�Ĳ���
		//�󶨷�����÷���ķ�����
		
	}
	
	private class MyConn implements ServiceConnection{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iSafePay = ISafePay.Stub.asInterface(service);
			try {
				boolean result = iSafePay.callPay(System.currentTimeMillis(), "123", 3.52f);
				if(result){
					Toast.makeText(getApplicationContext(), "֧���ɹ�����ȡ���ڵ�", 0).show();
				}else{
					Toast.makeText(getApplicationContext(), "֧��ʧ�ܣ�������", 0).show();
				}
//				unbindService(conn);
//				conn = null;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
	}
}
