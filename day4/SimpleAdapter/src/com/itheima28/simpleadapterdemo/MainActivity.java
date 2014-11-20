package com.itheima28.simpleadapterdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView mListView = (ListView) findViewById(R.id.listview);
		
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "����1");
		map.put("icon", R.drawable.f007);
		data.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "����2");
		map.put("icon", R.drawable.f007);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "����3");
		map.put("icon", R.drawable.f007);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "����4");
		map.put("icon", R.drawable.f007);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "����5");
		map.put("icon", R.drawable.f007);
		data.add(map);
		
		SimpleAdapter adapter = new SimpleAdapter(
				this, // ������
				data, // listView�󶨵�����
				R.layout.listview_item, // listview������Ŀ�Ĳ��ֵ�id
				new String[]{"name", "icon"}, 	// data�����е�map�������key
				new int[]{R.id.tv_name, R.id.iv_icon}); // resource �е�id
		
		mListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
