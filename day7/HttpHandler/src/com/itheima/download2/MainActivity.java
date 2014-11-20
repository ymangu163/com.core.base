package com.itheima.download2;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class MainActivity extends Activity {
	private EditText et_path;
	private TextView tv_info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		tv_info = (TextView) findViewById(R.id.tv_info);
	}

	public void download(View view){
		String path = et_path.getText().toString().trim();
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "���������ص�·��", 0).show();
			return;
		}else{
			HttpUtils http = new HttpUtils();
			HttpHandler handler = http.download(path,
					"/sdcard/xxx.zip",
				    true, // ���Ŀ���ļ����ڣ�����δ��ɵĲ��ּ������ء���������֧��RANGEʱ���������ء�
				    true, // ��������󷵻���Ϣ�л�ȡ���ļ�����������ɺ��Զ���������
				    new RequestCallBack<File>() {

				        @Override
				        public void onStart() {
				        	tv_info.setText("conn...");
				        }

				        @Override
				        public void onLoading(long total, long current, boolean isUploading) {
				        	tv_info.setText(current + "/" + total);
				        }

				        @Override
				        public void onSuccess(ResponseInfo<File> responseInfo) {
				        	tv_info.setText("downloaded:" + responseInfo.result.getPath());
				        }
				        @Override
				        public void onFailure(HttpException error, String msg) {
				        	tv_info.setText(msg);
				        }
				});
		}
	}
}
