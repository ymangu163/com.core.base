package com.itheima28.caller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author andong
 * ��������о���ʾ�Ľ���
 */
public class MainUI4 extends Activity implements OnClickListener {

	/**
	 * ������ձ�����ʱ�ص��˷���
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		// ����ִ�д˾����. ִ�и���ĳ�ʼ������.
		
		setContentView(R.layout.main);		// ���õ�ǰ������ʾ�Ĳ���.
		
		
		Button btnCall = (Button) findViewById(R.id.btn_call);
		
		btnCall.setOnClickListener(this);
	}
	
//	class MyOnClickListener implements OnClickListener {
//
//		@Override
//		public void onClick(View v) {
//			System.out.println("MainUI3 �������..");
//			call();
//		}
//	}
	
	/**
	 * ����绰��ҵ�񷽷�
	 */
	private void call() {
		
		// 1. ȡ��������еĺ���
		EditText etNumber = (EditText) findViewById(R.id.number);	// ��������
		String number = etNumber.getText().toString();	// ��Ҫ����ĺ���
		
		// 2. ���ݺ��벦��绰
		Intent intent = new Intent();		// ����һ����ͼ
		intent.setAction(Intent.ACTION_CALL);		// ָ���䶯��Ϊ����绰
		intent.setData(Uri.parse("tel:" + number));	// ָ����Ҫ�����ĺ���
		startActivity(intent);	// ִ���������
	}

	@Override
	public void onClick(View v) {
		System.out.println("MainUI4 �������..");
		call();
	}
}
