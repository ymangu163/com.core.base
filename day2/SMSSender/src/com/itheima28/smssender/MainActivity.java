package com.itheima28.smssender;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	
	private EditText etNumber;
	private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        etNumber = (EditText) findViewById(R.id.et_number);
        etContent = (EditText) findViewById(R.id.et_content);
        
        Button button = (Button) findViewById(R.id.btn_send);
        button.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// ����
		String number = etNumber.getText().toString();
		// ����
		String content = etContent.getText().toString();
		
		SmsManager smsManager = SmsManager.getDefault();
		
		smsManager.sendTextMessage(
				number,	// �ռ���
				null, 	// �������ĺ���
				content, // ����
				null, 
				null);
	}
    
}
