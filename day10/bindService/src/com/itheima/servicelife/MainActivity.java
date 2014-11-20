package com.itheima.servicelife;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	private MyConn conn ;
	private IMiddlePerson mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //绑定服务
    public void bind(View view){
    	//3.activity采用绑定的方式去开启服务。
    	Intent intent = new Intent(this,MyService.class);
    	conn = new MyConn();
    	bindService(intent, conn, BIND_AUTO_CREATE);
    	
    }
    //解除绑定服务
    public void unbind(View view){
    	unbindService(conn);
    }
    
    @Override
    protected void onDestroy() {
    	System.out.println("啊啊啊，我是activity，我挂了");
    	super.onDestroy();
    }
    //调用服务里面的方法。
    public void call(View view){
    	//5.通过中间人调用服务里面的方法。
    	mp.callMethodInService(55);
    }
    
    private class MyConn implements ServiceConnection{
    	//4. 当服务被连接的时候调用 服务别成功 绑定的时候调用
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("在activity里面成功得到了中间人");
			mp = (IMiddlePerson) service;
		}
		//当服务失去连接的时候调用（一般进程挂了，服务被异常杀死）
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
    }
}
