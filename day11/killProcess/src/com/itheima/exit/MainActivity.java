package com.itheima.exit;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("����");
		builder.setMessage("ȷ���˳���ǰӦ�ó�����");
		builder.setPositiveButton("�����˳�", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();//�رյ�ǰ��activity��
				//���Լ��Ľ���ɱ����
				//��ɱ�ķ�����
				android.os.Process.killProcess(android.os.Process.myPid());
				//System.exit(0);
			}
		});
		builder.setNegativeButton("ȡ��", null);
		builder.show();
	}
}
