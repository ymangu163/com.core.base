package com.itheima.intent2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		// �� action
		// �� ����
		// ���������� Category ���
		Intent intent = new Intent();
		intent.setAction("com.itheima.intent2.open2");
		intent.addCategory(Intent.CATEGORY_DEFAULT);

		// URL:ͳһ��Դ��λ�� http https ftp rtsp: URI��ͳһ��Դ��ʶ�� url��uri��һ���Ӽ�
		// intent.setData(Uri.parse("jianren:����"));
		// intent.setType("application/person");
		intent.setDataAndType(Uri.parse("jianren:����"), "application/person");
		startActivity(intent);
	}
}
