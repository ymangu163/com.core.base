package com.itheima.rpcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_name = (EditText) findViewById(R.id.et_name);
	}
	
	
	//��ʽ��ͼ  ������ָ��Ҫ������������������������ ��Ӧ�ó���֮�������һ��
	// һ�㼤���Լ�Ӧ�õ������ʱ�� ������ʾ��ͼ
	
	//��ʽ��ͼ�� ֻ��Ҫָ��Ҫ���������ݾͿ��� �� �ô�Ӧ�ó���֮��û����ϣ�
	//�������д��Ӧ��  ��ʽ��ͼ�� ����Ҫ���ĶԷ��İ���������
	
	
	
	public void enter(View view){
		String name = et_name.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "����������", 0).show();
			return;
		}
		//��ͼ     ������  ����  ����
		Intent intent = new Intent(this, CalcActivity.class);
		intent.putExtra("name", name);
		//��ʽ��ͼ
		//intent.setClassName(getPackageName(), "com.itheima.rpcalc.CalcActivity");//��ָ��������Ҳ��ָ������ ֱ��ָ��Ҫ��������
		startActivity(intent);
		//��ʽ��ͼ
		//intent.setAction(action);
		//intent.setData(data);
	}
}
