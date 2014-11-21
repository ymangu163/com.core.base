package com.itheima.killother;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private ActivityManager am;//相当于进程管理器
	private EditText et_packname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		et_packname = (EditText) findViewById(R.id.et_packname);
	}

	public void click(View view){
		String packname = et_packname.getText().toString().trim();
		am.killBackgroundProcesses(packname);
		//杀死空进程 和 后台进程
	}

}
