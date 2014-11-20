package com.itheima.smshelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListNumberActivity extends Activity {
	private ListView lv;
	private String[] objects = {
			"1234","34324","5643543","32424" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.sms_item,
				R.id.tv_info, objects));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String smsinfo = objects[position];
				Intent data = new Intent();
				data.putExtra("smsinfo", smsinfo);
				//设置数据。
				setResult(1, data);
				//关闭掉当前的activity，并且回传数据 onActivityResult().
				finish();
			}
		});
	}
}
