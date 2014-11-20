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
		// ���ݿ�ʲôʱ�򴴽�
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		
		// ��һ���������ݿ�ʱ�������ݿ��ļ�. onCreate�ᱻ����
		openHelper.getReadableDatabase();
	}
	
	public void testInsert() {
		PersonDao dao = new PersonDao(getContext());
		
		dao.insert(new Person(0, "��ϣ", 28));
	}
	
	public void testDelete() {
		PersonDao dao = new PersonDao(getContext());
		dao.delete(1);
	}

	public void testUpdate() {
		PersonDao dao = new PersonDao(getContext());
		dao.update(3, "���");
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
				// ��������
				db.beginTransaction();
				
				// 1. �������˻��п�1000��Ǯ
				db.execSQL("update person set balance = balance - 1000 where name = 'zhangsan';");
				
				// ATM��, �ҵ���.
				// int result = 10 / 0;
				
				// 2. �������˻��м�1000��Ǯ
				db.execSQL("update person set balance = balance + 1000 where name = 'lisi';");
				
				// �������ɹ�
				db.setTransactionSuccessful();
			} finally {
				// ֹͣ����
				db.endTransaction();
			}
			db.close();
		}
	}
	
	public void testTransactionInsert() {
		PersonSQLiteOpenHelper openHelper = new PersonSQLiteOpenHelper(getContext());
		SQLiteDatabase db = openHelper.getWritableDatabase();
		
		if(db.isOpen()) {
			
			// 1. ��ס��ǰ��ʱ��
			long start = System.currentTimeMillis();
			
			// 2. ��ʼ�������
			try {
				db.beginTransaction();
				for (int i = 0; i < 10000; i++) {
					db.execSQL("insert into person(name, age, balance) values('wang" + i + "', " + (10 + i) + ", " + (10000 + i) + ")");
				}
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
			
			// 3. ��ס����ʱ��, �����ʱʱ��
			long end = System.currentTimeMillis();
			
			long diff = end - start;
			Log.i(TAG, "��ʱ: " + diff + "����");
			
			db.close();
		}
	}
}
