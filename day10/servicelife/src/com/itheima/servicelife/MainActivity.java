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
    	//通知框架开启服务。
    	startService(intent);
    }
    public void stop(View view){
    	Intent intent = new Intent(this,MyService.class);
    	stopService(intent);
    }
    
    @Override
    protected void onDestroy() {
    	System.out.println("啊啊啊，我是activity，我挂了");
    	super.onDestroy();
    }
    //调用服务里面的方法。不可以自己new服务，调用的服务的方法，必须通过框架得到服务的引用。
    public void call(View view){
    
    }
}
