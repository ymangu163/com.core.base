package com.itheima.smshelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_content;
	private EditText et_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_content = (EditText) findViewById(R.id.et_content);
		et_number = (EditText) findViewById(R.id.et_number);
	}

	public void selectSms(View view) {
		Intent intent = new Intent(this, ListSmsActivity.class);
		// 开启一个新的界面，并且获取界面的返回值
		// startActivity(intent);
		// int requestCode 请求码
		startActivityForResult(intent, 0);
	}

	public void selectNumber(View view) {
		Intent intent = new Intent(this, ListNumberActivity.class);
		// 开启一个新的界面，并且获取界面的返回值
		// startActivity(intent);
		// int requestCode 请求码
		startActivityForResult(intent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			String smsinfo = data.getStringExtra("smsinfo");
			if (resultCode == 0) {
				et_content.setText(smsinfo);
			} else if (resultCode == 1) {
				et_number.setText(smsinfo);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	
	public void sendSms(View view){
		String content = et_content.getText().toString().trim();
		String number = et_number.getText().toString().trim();
		SmsManager.getDefault().sendTextMessage(number, null, content, null, null);
		Toast.makeText(this, "发送成功", 0).show();
	}
}
