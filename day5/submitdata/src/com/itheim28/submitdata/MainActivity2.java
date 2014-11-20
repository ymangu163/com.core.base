package com.itheim28.submitdata;

import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.Header;

import com.itheim28.submitdata.utils.NetUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends Activity {

	protected static final String TAG = "MainActivity2";
	private EditText etUserName;
	private EditText etPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etUserName = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
	}

	public void doGet(View v) {
		final String userName = etUserName.getText().toString();
		final String password = etPassword.getText().toString();
	
        AsyncHttpClient client = new AsyncHttpClient();

        String data = "username=" + URLEncoder.encode(userName) + "&password=" + URLEncoder.encode(password);
        
        client.get("http://10.0.2.2:8080/ServerItheima28/servlet/LoginServlet?" + data, new MyResponseHandler());
	}
	
	public void doPost(View v) {
		final String userName = etUserName.getText().toString();
		final String password = etPassword.getText().toString();
		
		AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("username", userName);
        params.put("password", password);
        
        client.post("http://10.0.2.2:8080/ServerItheima28/servlet/LoginServlet", 
        		params, 
        		new MyResponseHandler());
	}
	
	class MyResponseHandler extends AsyncHttpResponseHandler {

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				byte[] responseBody) {
//			Log.i(TAG, "statusCode: " + statusCode);
			
			Toast.makeText(MainActivity2.this, 
					"³É¹¦: statusCode: " + statusCode + ", body: " + new String(responseBody), 
					0).show();
		}

		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error) {
			Toast.makeText(MainActivity2.this, "Ê§°Ü: statusCode: " + statusCode, 0).show();
		}
    }
}
