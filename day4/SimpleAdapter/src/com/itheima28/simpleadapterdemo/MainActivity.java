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
		map.put("name", "张三1");
		map.put("icon", R.drawable.f007);
		data.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "张三2");
		map.put("icon", R.drawable.f007);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "张三3");
		map.put("icon", R.drawable.f007);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "张三4");
		map.put("icon", R.drawable.f007);
		data.add(map);

		map = new HashMap<String, Object>();
		map.put("name", "张三5");
		map.put("icon", R.drawable.f007);
		data.add(map);
		
		SimpleAdapter adapter = new SimpleAdapter(
				this, // 上下文
				data, // listView绑定的数据
				R.layout.listview_item, // listview的子条目的布局的id
				new String[]{"name", "icon"}, 	// data数据中的map集合里的key
				new int[]{R.id.tv_name, R.id.iv_icon}); // resource 中的id
		
		mListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
