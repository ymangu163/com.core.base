package com.itheima28.sqlitedemo.test;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.itheima28.sqlitedemo.dao.PersonDao2;
import com.itheima28.sqlitedemo.db.PersonSQLiteOpenHelper;
import com.itheima28.sqlitedemo.entities.Person;

public class TestCase2 extends AndroidTestCase {

	private static final String TAG = "TestCase";

	public void test() {
		// 数据库什么时候创建
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		
		// 第一次连接数据库时创建数据库文件. onCreate会被调用
		openHelper.getReadableDatabase();
	}
	
	public void testInsert() {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.insert(new Person(0, "zhouqi", 88));
	}
	
	public void testDelete() {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.delete(8);
	}

	public void testUpdate() {
		PersonDao2 dao = new PersonDao2(getContext());
		dao.update(3, "fengjie");
	}

	public void testQueryAll() {
		PersonDao2 dao = new PersonDao2(getContext());
		List<Person> personList = dao.queryAll();
		
		for (Person person : personList) {
			Log.i(TAG, person.toString());
		}
	}

	public void testQueryItem() {
		PersonDao2 dao = new PersonDao2(getContext());
		Person person = dao.queryItem(4);
		Log.i(TAG, person.toString());
	}
}
