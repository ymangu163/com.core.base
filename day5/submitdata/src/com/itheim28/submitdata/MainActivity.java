package com.itheim28.submitdata;

import com.itheim28.submitdata.utils.NetUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText etUserName;
	private EditText etPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etUserName = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
	}

	public void doGet(View v) {
		final String userName = etUserName.getText().toString();
		final String password = etPassword.getText().toString();
		
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						// ʹ��get��ʽץȥ����
						final String state = NetUtils.loginOfGet(userName, password);
						
						// ִ�����������߳���
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								// ���������߳��в���
								Toast.makeText(MainActivity.this, state, 0).show();
							}
						});
					}
				}).start();
	}
	
	public void doPost(View v) {
		final String userName = etUserName.getText().toString();
		final String password = etPassword.getText().toString();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				final String state = NetUtils.loginOfPost(userName, password);
				// ִ�����������߳���
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// ���������߳��в���
						Toast.makeText(MainActivity.this, state, 0).show();
					}
				});
			}
		}).start();
	}
}
