package com.itheima28.qqlogin;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima28.qqlogin.utils.Utils;
import com.itheima28.qqlogin.utils.UtilsOfSharedPreferences;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";
	private EditText etNumber;
	private EditText etPassword;
	private CheckBox cbRemerberPWD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etNumber = (EditText) findViewById(R.id.et_number);
		etPassword = (EditText) findViewById(R.id.et_password);
		cbRemerberPWD = (CheckBox) findViewById(R.id.cb_remerber_pwd);
		Button btnLogin = (Button) findViewById(R.id.btn_login);
		
		btnLogin.setOnClickListener(this);
		
		// 回显数据
		Map<String, String> userInfoMap = UtilsOfSharedPreferences.getUserInfo(this);
		if(userInfoMap != null) {
			etNumber.setText(userInfoMap.get("number"));
			etPassword.setText(userInfoMap.get("password"));
		}
	}

	@Override
	public void onClick(View v) {
		// 执行登录的操作
		
		// 1. 取出号码和密码
		String number = etNumber.getText().toString();
		String password = etPassword.getText().toString();
		
		if(TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
			// 弹出吐司
			Toast.makeText(this, "请正确输入", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// 2. 判断记住密码是否被选中, 如果被选中, 存起来
		if(cbRemerberPWD.isChecked()) {
			// 当前需要记住密码
			Log.i(TAG, "记住密码: " + number + ", " + password);
			
			boolean isSuccess = UtilsOfSharedPreferences.saveUserInfo(this, number, password);
			
			if(isSuccess) {
				Toast.makeText(this, "保存成功", 0).show();
			} else {
				Toast.makeText(this, "保存失败", 0).show();
			}
		}
		
		// 3. 登陆成功
		Toast.makeText(this, "登录成功", 0).show();
	}
}
