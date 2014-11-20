package com.itheima.kof97;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Ĭ������º������л�activity�ᱻ����Ȼ�����´�����
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	private TextView tv_blood;
	private int blood = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("oncreate");
		setContentView(R.layout.activity_main);
		tv_blood = (TextView) findViewById(R.id.tv_blood);
	}

	public void click(View view){
		blood --;
		tv_blood.setText("�Է�������ֵ��"+blood);
		if(blood<0){
			Toast.makeText(this, "K.O.!", 1).show();
		}
	}
	
	//�����ٵ�ʱ����õķ���
		@Override
		protected void onDestroy() {
			System.out.println("ondestory");
			super.onDestroy();
		}
		//��activity�����û��ɼ���ʱ����õķ���
		@Override
		protected void onStart() {
			System.out.println("onstart");
			super.onStart();
		}
		@Override
		protected void onRestart() {
			System.out.println("onrestart");
			super.onRestart();
		}
		
		
		//��activity�����û����ɼ���ʱ����õķ���
		@Override
		protected void onStop() {
			System.out.println("onstop");
			super.onStop();
		}
		
		//���濪ʼ��ȡ�������Ӧ�ķ����� (���水ť���Ա�������ı�������������ݣ�
		@Override
		protected void onResume() {
			System.out.println("onresume");
			super.onResume();
		}
		//����ʧȥ�����Ӧ�ķ�������ͣ������ť���ɱ�������ı��򲻿��������ݣ����ǽ����û���Ȼ�ܿ�����
		@Override
		protected void onPause() {
			System.out.println("onpause");
			super.onPause();
		}
		

}
