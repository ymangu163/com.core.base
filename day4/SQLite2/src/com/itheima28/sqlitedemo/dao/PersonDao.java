package com.itheima28.sqlitedemo.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima28.sqlitedemo.db.PersonSQLiteOpenHelper;
import com.itheima28.sqlitedemo.entities.Person;

public class PersonDao {

	private PersonSQLiteOpenHelper mOpenHelper;	// ���ݿ�İ��������

	public PersonDao(Context context) {
		mOpenHelper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * ��ӵ�person��һ������
	 * @param person
	 */
	public void insert(Person person) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()) {	// ������ݿ��, ִ����ӵĲ���
			
			// ִ����ӵ����ݿ�Ĳ���
			db.execSQL("insert into person(name, age) values(?, ?);", new Object[]{person.getName(), person.getAge()});
			
			db.close();	// ���ݿ�ر�
		}
	}
	
	/**
	 * ����idɾ����¼
	 * @param id
	 */
	public void delete(int id) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();	// ��ÿ�д�����ݿ����
		if(db.isOpen()) {	// ������ݿ��, ִ����ӵĲ���
			
			db.execSQL("delete from person where _id = ?;", new Integer[]{id});
			
			db.close();	// ���ݿ�ر�
		}
	}
	

	/**
	 * ����id�ҵ���¼, �����޸�����
	 * @param id
	 * @param name
	 */
	public void update(int id, String name) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()) {	// ������ݿ��, ִ����ӵĲ���
			
			db.execSQL("update person set name = ? where _id = ?;", new Object[]{name, id});
			
			db.close();	// ���ݿ�ر�
		}
	}
	
	public List<Person> queryAll() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// ���һ��ֻ�������ݿ����
		if(db.isOpen()) {
			
			Cursor cursor = db.rawQuery("select _id, name, age from person;", null);
			
			if(cursor != null && cursor.getCount() > 0) {
				List<Person> personList = new ArrayList<Person>();
				int id;
				String name;
				int age;
				while(cursor.moveToNext()) {
					id = cursor.getInt(0);	// ȡ��0�е����� id
					name = cursor.getString(1);	// ȡ����
					age = cursor.getInt(2);		// ȡ����
					personList.add(new Person(id, name, age));
				}

				db.close();
				return personList;
			}
			db.close();
		}
		return null;
	}
	
	/**
	 * ����id��ѯ��
	 * @param id
	 * @return
	 */
	public Person queryItem(int id) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// ���һ��ֻ�������ݿ����
		if(db.isOpen()) {
			Cursor cursor = db.rawQuery("select _id, name, age from person where _id = ?;", new String[]{id + ""});
			if(cursor != null && cursor.moveToFirst()) {
				int _id = cursor.getInt(0);
				String name = cursor.getString(1);
				int age = cursor.getInt(2);
				db.close();
				return new Person(_id, name, age);
			}
			db.close();
		}
		return null;
	}
}
