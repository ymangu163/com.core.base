package com.itheima.taskstack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		System.out.println("02activity�������ˡ�����ջid��"+getTaskId());
	}
	public void open01(View view){
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	public void open02(View view){
		Intent intent = new Intent(this,SecondActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		System.out.println("02activityonnew intnet������ջid��"+getTaskId());
		super.onNewIntent(intent);
	}
	
}
