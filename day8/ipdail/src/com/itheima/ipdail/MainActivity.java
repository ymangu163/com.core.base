package com.itheima.ipdail;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_number;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_number = (EditText) findViewById(R.id.et_number);
		sp = getSharedPreferences("config", MODE_PRIVATE);
	}

	public void save(View view){
		String ipnumber = et_number.getText().toString().trim();
		if(TextUtils.isEmpty(ipnumber)){
			Toast.makeText(this, "清除ip号码成功", 0).show();
		}else{
			Toast.makeText(this, "设置ip号码成功", 0).show();
		}
		Editor editor = sp.edit();
		editor.putString("ipnumber", ipnumber);
		editor.commit();
	}

}
