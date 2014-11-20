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
		
		// ��������
		Map<String, String> userInfoMap = UtilsOfSharedPreferences.getUserInfo(this);
		if(userInfoMap != null) {
			etNumber.setText(userInfoMap.get("number"));
			etPassword.setText(userInfoMap.get("password"));
		}
	}

	@Override
	public void onClick(View v) {
		// ִ�е�¼�Ĳ���
		
		// 1. ȡ�����������
		String number = etNumber.getText().toString();
		String password = etPassword.getText().toString();
		
		if(TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
			// ������˾
			Toast.makeText(this, "����ȷ����", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// 2. �жϼ�ס�����Ƿ�ѡ��, �����ѡ��, ������
		if(cbRemerberPWD.isChecked()) {
			// ��ǰ��Ҫ��ס����
			Log.i(TAG, "��ס����: " + number + ", " + password);
			
			boolean isSuccess = UtilsOfSharedPreferences.saveUserInfo(this, number, password);
			
			if(isSuccess) {
				Toast.makeText(this, "����ɹ�", 0).show();
			} else {
				Toast.makeText(this, "����ʧ��", 0).show();
			}
		}
		
		// 3. ��½�ɹ�
		Toast.makeText(this, "��¼�ɹ�", 0).show();
	}
}
