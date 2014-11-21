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
		builder.setTitle("提醒");
		builder.setMessage("确定退出当前应用程序吗？");
		builder.setPositiveButton("立刻退出", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();//关闭当前的activity。
				//把自己的进程杀死。
				//自杀的方法。
				android.os.Process.killProcess(android.os.Process.myPid());
				//System.exit(0);
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}
}
