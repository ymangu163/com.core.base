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

    //�󶨷���
    public void bind(View view){
    	//3.activity���ð󶨵ķ�ʽȥ��������
    	Intent intent = new Intent(this,MyService.class);
    	conn = new MyConn();
    	bindService(intent, conn, BIND_AUTO_CREATE);
    	
    }
    //����󶨷���
    public void unbind(View view){
    	unbindService(conn);
    }
    
    @Override
    protected void onDestroy() {
    	System.out.println("������������activity���ҹ���");
    	super.onDestroy();
    }
    //���÷�������ķ�����
    public void call(View view){
    	//5.ͨ���м��˵��÷�������ķ�����
    	mp.callMethodInService(55);
    }
    
    private class MyConn implements ServiceConnection{
    	//4. ���������ӵ�ʱ����� �����ɹ� �󶨵�ʱ�����
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("��activity����ɹ��õ����м���");
			mp = (IMiddlePerson) service;
		}
		//������ʧȥ���ӵ�ʱ����ã�һ����̹��ˣ������쳣ɱ����
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
    }
}
