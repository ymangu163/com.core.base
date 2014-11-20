package com.itheima28.sqlitedemo;

import java.util.List;

import com.itheima28.sqlitedemo.dao.PersonDao;
import com.itheima28.sqlitedemo.entities.Person;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends Activity {

    private List<Person> personList;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ListView mListView = (ListView) findViewById(R.id.listview);
        
        PersonDao dao = new PersonDao(this);
        personList = dao.queryAll();
        
        // 把view层对象ListView和控制器BaseAdapter关联起来
        mListView.setAdapter(new MyAdapter());
    }

    /**
     * @author andong
     * 数据适配器
     */
    class MyAdapter extends BaseAdapter {

    	private static final String TAG = "MyAdapter";

		/**
    	 * 定义ListView的数据的长度
    	 */
		@Override
		public int getCount() {
			return personList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * 此方法返回的是ListView的列表中某一行的View对象
		 * position 当前返回的view的索引位置
		 * convertView 缓存对象
		 * parent 就是ListView对象
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			
			if(convertView == null) {
				// 布局填充器对象, 用于把xml布局转换成view对象
				LayoutInflater inflater = MainActivity2.this.getLayoutInflater();
				view = inflater.inflate(R.layout.listview_item, null);
			} else {
				view = convertView;
			}
			
			// 给view中的姓名和年龄赋值
			TextView tvName = (TextView) view.findViewById(R.id.tv_listview_item_name);
			TextView tvAge = (TextView) view.findViewById(R.id.tv_listview_item_age);
			
			Person person = personList.get(position);
			
			tvName.setText("姓名: " + person.getName());
			tvAge.setText("年龄: " + person.getAge());
			return view;
		}
    	
    }
}
