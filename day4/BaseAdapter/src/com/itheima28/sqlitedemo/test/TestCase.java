package com.itheima28.sqlitedemo.test;

import java.util.List;

import com.itheima28.sqlitedemo.dao.PersonDao;
import com.itheima28.sqlitedemo.db.PersonSQLiteOpenHelper;
import com.itheima28.sqlitedemo.entities.Person;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestCase extends AndroidTestCase {

	private static final String TAG = "TestCase";

	public void test() {
		// 数据库什么时候创建
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		
		// 第一次连接数据库时创建数据库文件. onCreate会被调用
		openHelper.getReadableDatabase();
	}
	
	public void testInsert() {
		PersonDao dao = new PersonDao(getContext());
		
		dao.insert(new Person(0, "冠希", 28));
	}
	
	public void testDelete() {
		PersonDao dao = new PersonDao(getContext());
		dao.delete(1);
	}

	public void testUpdate() {
		PersonDao dao = new PersonDao(getContext());
		dao.update(3, "凤姐");
	}

	public void testQueryAll() {
		PersonDao dao = new PersonDao(getContext());
		List<Person> personList = dao.queryAll();
		
		for (Person person : personList) {
			Log.i(TAG, person.toString());
		}
	}

	public void testQueryItem() {
		PersonDao dao = new PersonDao(getContext());
		Person person = dao.queryItem(4);
		Log.i(TAG, person.toString());
	}
	
	public void testTransaction() {
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = openHelper.getWritableDatabase();
		
		if(db.isOpen()) {
			
			try {
				// 开启事务
				db.beginTransaction();
				
				// 1. 从张三账户中扣1000块钱
				db.execSQL("update person set balance = balance - 1000 where name = 'zhangsan';");
				
				// ATM机, 挂掉了.
				// int result = 10 / 0;
				
				// 2. 向李四账户中加1000块钱
				db.execSQL("update person set balance = balance + 1000 where name = 'lisi';");
				
				// 标记事务成功
				db.setTransactionSuccessful();
			} finally {
				// 停止事务
				db.endTransaction();
			}
			db.close();
		}
	}
	
	public void testTransactionInsert() {
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = openHelper.getWritableDatabase();
		
		if(db.isOpen()) {
			
			// 1. 记住当前的时间
			long start = System.currentTimeMillis();
			
			// 2. 开始添加数据
			try {
				db.beginTransaction();
				for (int i = 0; i < 10000; i++) {
					db.execSQL("insert into person(name, age, balance) values('wang" + i + "', " + (10 + i) + ", " + (10000 + i) + ")");
				}
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
			
			// 3. 记住结束时间, 计算耗时时间
			long end = System.currentTimeMillis();
			
			long diff = end - start;
			Log.i(TAG, "耗时: " + diff + "毫秒");
			
			db.close();
		}
	}
}
