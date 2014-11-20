package com.itheima.servicelife;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
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
	 * 这是服务里面的一个方法
	 */
	public void methodInService(){
		Toast.makeText(this, "哈哈，我是服务里面的方法", 0).show();
	}

}
