package com.itheima28.htmldemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private static final int SUCCESS = 0;
	protected static final int ERROR = 1;
	private EditText etUrl;
	private TextView tvHtml;
	
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SUCCESS:
				 tvHtml.setText((String) msg.obj);
				break;
			case ERROR:
				Toast.makeText(MainActivity.this, "����ʧ��", 0).show();
				break;
			default:
				break;
			}
		}
		
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etUrl = (EditText) findViewById(R.id.et_url);
		tvHtml = (TextView) findViewById(R.id.tv_html);
		
	}

	public void getHtml(View v) {
		final String url = etUrl.getText().toString();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// ��������
				String html = getHtmlFromInternet(url);
				
				if(!TextUtils.isEmpty(html)) {
					// ����textview����ʾ��
					Message msg = new Message();
					msg.what = SUCCESS;
					msg.obj = html;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = ERROR;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}

	/**
	 * ���ݸ�����url��������, ץȥhtml����
	 * @param url
	 * @return
	 */
	protected String getHtmlFromInternet(String url) {
		
		try {
			URL mURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) mURL.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(5000);
			
//			conn.connect();
			
			int responseCode = conn.getResponseCode();
			
			if(responseCode == 200) {
				InputStream is = conn.getInputStream();
				String html = getStringFromInputStream(is);
				return html;
			} else {
				Log.i(TAG, "����ʧ��: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ����������һ���ַ�����Ϣ
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	private String getStringFromInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		
		while((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		is.close();
		
		String html = baos.toString();	// �����е�����ת�����ַ���, ���õı�����: utf-8
		
		String charset = "utf-8";
		if(html.contains("gbk") || html.contains("gb2312")
				|| html.contains("GBK") || html.contains("GB2312")) {		// �������gbk, gb2312����, �Ͳ���gbk������ж��ַ�������
			charset = "gbk";
		}
		
		html = new String(baos.toByteArray(), charset);	// ��ԭ�е��ֽ��������ʹ�ô����ı������ƽ��б���
		baos.close();
		return html;
	}
}
