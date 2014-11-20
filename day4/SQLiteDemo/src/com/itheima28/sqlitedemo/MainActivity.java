package com.itheima28.sqlitedemo;

import java.util.List;

import com.itheima28.sqlitedemo.dao.PersonDao;
import com.itheima28.sqlitedemo.entities.Person;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LinearLayout llList = (LinearLayout) findViewById(R.id.ll_list);
        
        PersonDao dao = new PersonDao(this);
        List<Person> personList = dao.queryAll();
        
        if(personList != null) {
	        TextView tv;
	        for (Person person : personList) {
				// 向线性布局中添加一个textview
	        	tv = new TextView(this);
	        	tv.setText(person.toString());
	        	tv.setTextSize(18);
	        	
	        	llList.addView(tv);
			}
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
