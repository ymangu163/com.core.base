package com.itheima.rpcalc;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalcActivity extends Activity {
	private TextView tv_result;

	//��activity��������ʱ����õķ���
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		tv_result = (TextView) findViewById(R.id.tv_result);
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		byte[] result = name.getBytes();
		int number = 0;
		for(byte b :result){
			 number += b&0xff;
		}
		int sorce = Math.abs(number)%100;
		tv_result.setText(name+"����Ʒ��"+sorce);
	}
}
