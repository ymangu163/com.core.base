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
        
        // ��view�����ListView�Ϳ�����BaseAdapter��������
        mListView.setAdapter(new MyAdapter());
    }

    /**
     * @author andong
     * ����������
     */
    class MyAdapter extends BaseAdapter {

    	private static final String TAG = "MyAdapter";

		/**
    	 * ����ListView�����ݵĳ���
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
		 * �˷������ص���ListView���б���ĳһ�е�View����
		 * position ��ǰ���ص�view������λ��
		 * convertView �������
		 * parent ����ListView����
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			
			if(convertView == null) {
				// �������������, ���ڰ�xml����ת����view����
				LayoutInflater inflater = MainActivity2.this.getLayoutInflater();
				view = inflater.inflate(R.layout.listview_item, null);
			} else {
				view = convertView;
			}
			
			// ��view�е����������丳ֵ
			TextView tvName = (TextView) view.findViewById(R.id.tv_listview_item_name);
			TextView tvAge = (TextView) view.findViewById(R.id.tv_listview_item_age);
			
			Person person = personList.get(position);
			
			tvName.setText("����: " + person.getName());
			tvAge.setText("����: " + person.getAge());
			return view;
		}
    	
    }
}
