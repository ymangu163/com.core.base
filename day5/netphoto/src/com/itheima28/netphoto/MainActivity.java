package com.itheima28.netphoto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "MainActivity";
	protected static final int ERROR = 1;
	private EditText etUrl;
	private ImageView ivIcon;
	private final int SUCCESS = 0;
	
	private Handler handler = new Handler() {

		/**
		 * ������Ϣ
		 */
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			Log.i(TAG, "what = " + msg.what);
			if(msg.what == SUCCESS) {	// ��ǰ�Ƿ�������, ȥ��ʾͼƬ
				ivIcon.setImageBitmap((Bitmap) msg.obj);		// ����imageView��ʾ��ͼƬ
			} else if(msg.what == ERROR) {
				Toast.makeText(MainActivity.this, "ץȥʧ��", 0).show();
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ivIcon = (ImageView) findViewById(R.id.iv_icon);
		etUrl = (EditText) findViewById(R.id.et_url);
		
		findViewById(R.id.btn_submit).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		final String url = etUrl.getText().toString();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				Bitmap bitmap = getImageFromNet(url);

//				ivIcon.setImageBitmap(bitmap);		// ����imageView��ʾ��ͼƬ
				if(bitmap != null) {
					Message msg = new Message();
					msg.what = SUCCESS;
					msg.obj = bitmap;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = ERROR;
					handler.sendMessage(msg);
				}
			}}).start();
		
	}

	/**
	 * ����url����ȡ����ץȥͼƬ����
	 * @param url
	 * @return url��Ӧ��ͼƬ
	 */
	private Bitmap getImageFromNet(String url) {
		HttpURLConnection conn = null;
		try {
			URL mURL = new URL(url);	// ����һ��url����
			
			// �õ�http�����Ӷ���
			conn = (HttpURLConnection) mURL.openConnection();
			
			conn.setRequestMethod("GET");		// �������󷽷�ΪGet
			conn.setConnectTimeout(10000);		// �������ӷ������ĳ�ʱʱ��, �������10����, û�����ӳɹ�, �����쳣
			conn.setReadTimeout(5000);		// ���ö�ȡ����ʱ��ʱʱ��, �������5��, ���쳣
			
			conn.connect();		// ��ʼ����
			
			int responseCode = conn.getResponseCode(); // �õ�����������Ӧ��
			if(responseCode == 200) {
				// ���ʳɹ�
				InputStream is = conn.getInputStream();	// ��÷��������ص�������
				
				Bitmap bitmap = BitmapFactory.decodeStream(is); // ���� ������ ����һ��bitmapλͼ����
				
				return bitmap;
			} else {
				Log.i(TAG, "����ʧ��: responseCode = " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();		// �Ͽ�����
			}
		}
		return null;
	}
}
