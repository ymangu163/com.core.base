package com.itheima.taskstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		System.out.println("01activity被创建了。任务栈id："+getTaskId());
	}

	public void open01(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void open02(View view){
		Intent intent = new Intent(this,SecondActivity.class);
		startActivity(intent);
	}
}
