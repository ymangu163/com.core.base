package com.itheima.servicelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){
    	Intent intent = new Intent(this,MyService.class);
    	//֪ͨ��ܿ�������
    	startService(intent);
    }
    public void stop(View view){
    	Intent intent = new Intent(this,MyService.class);
    	stopService(intent);
    }
    
    @Override
    protected void onDestroy() {
    	System.out.println("������������activity���ҹ���");
    	super.onDestroy();
    }
    //���÷�������ķ������������Լ�new���񣬵��õķ���ķ���������ͨ����ܵõ���������á�
    public void call(View view){
    
    }
}
