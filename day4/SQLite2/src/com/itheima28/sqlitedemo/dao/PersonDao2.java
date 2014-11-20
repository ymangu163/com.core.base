package com.itheima28.sqlitedemo.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itheima28.sqlitedemo.db.PersonSQLiteOpenHelper;
import com.itheima28.sqlitedemo.entities.Person;

public class PersonDao2 {

	private static final String TAG = "PersonDao2";
	private PersonSQLiteOpenHelper mOpenHelper;	// ���ݿ�İ��������

	public PersonDao2(Context context) {
		mOpenHelper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * ��ӵ�person��һ������
	 * @param person
	 */
	public void insert(Person person) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()) {	// ������ݿ��, ִ����ӵĲ���
			
			ContentValues values = new ContentValues();
			values.put("name", person.getName());		// key��ΪҪ�洢������, value�����е�ֵ
			values.put("age", person.getAge());
			long id = db.insert("person", "name", values);
			Log.i(TAG, "id: " + id);
			
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
			
			String whereClause = "_id = ?";
			String[] whereArgs = {id + ""};
			int count = db.delete("person", whereClause, whereArgs);
			Log.i(TAG, "ɾ����: " + count + "��");
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
			ContentValues values = new ContentValues();
			values.put("name", name);
			
			int count  = db.update("person", values, "_id = ?", new String[]{id + ""});

			Log.i(TAG, "�޸���: " + count + "��");
			
			db.close();	// ���ݿ�ر�
		}
	}
	
	public List<Person> queryAll() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// ���һ��ֻ�������ݿ����
		if(db.isOpen()) {
			String[] columns = {"_id", "name", "age"};	// ��Ҫ����
			String selection = null;	// ѡ������, ��null��ѯ����
			String[] selectionArgs = null;	// ѡ�������Ĳ���, ���ѡ�������е�? �滻�������е�ֵ
			String groupBy = null;	// �������  group by name
			String having = null;	// �������
			String orderBy = null;	// ����
			
			Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
			
			int id;
			String name;
			int age;
			if(cursor != null && cursor.getCount() > 0) {
				List<Person> personList = new ArrayList<Person>();
				
				while(cursor.moveToNext()) {	// ������һλ, ֪�����һλ, �����������ƶ���, ֹͣ.
					id = cursor.getInt(0);
					name = cursor.getString(1);
					age = cursor.getInt(2);
					
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
			String[] columns = {"_id", "name", "age"};	// ��Ҫ����
			String selection = "_id = ?";	// ѡ������, ��null��ѯ����
			String[] selectionArgs = {id + ""};	// ѡ�������Ĳ���, ���ѡ�������е�? �滻�������е�ֵ
			String groupBy = null;	// �������  group by name
			String having = null;	// �������
			String orderBy = null;	// ����
			
			Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
			
			if(cursor != null && cursor.moveToFirst()) {		// cursor����Ϊnull, ���ҿ����ƶ�����һ��
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
