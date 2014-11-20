package com.itheima.bindremote;

import com.itheima.remoteservice.IMiddlePerson;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

public class MainActivity extends Activity {
	private MyConn conn;
	private IMiddlePerson iMp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 绑定远程服务
	 * @param view
	 */
	public void bind(View view){
		Intent intent = new Intent();
		intent.setAction("com.itheima.remoteservice");
		conn = new MyConn();
		bindService(intent, conn, BIND_AUTO_CREATE);
	}
	
	private class MyConn implements ServiceConnection{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			iMp = IMiddlePerson.Stub.asInterface(service);
		}
 
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
	}
	
	public void call(View view){
		try {
			iMp.callMethodInService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void onDestroy() {
		unbindService(conn);
		super.onDestroy();
	}
}
