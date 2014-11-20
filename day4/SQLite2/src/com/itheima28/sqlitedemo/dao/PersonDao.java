package com.itheima28.sqlitedemo.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima28.sqlitedemo.db.PersonSQLiteOpenHelper;
import com.itheima28.sqlitedemo.entities.Person;

public class PersonDao {

	private PersonSQLiteOpenHelper mOpenHelper;	// 数据库的帮助类对象

	public PersonDao(Context context) {
		mOpenHelper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * 添加到person表一条数据
	 * @param person
	 */
	public void insert(Person person) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()) {	// 如果数据库打开, 执行添加的操作
			
			// 执行添加到数据库的操作
			db.execSQL("insert into person(name, age) values(?, ?);", new Object[]{person.getName(), person.getAge()});
			
			db.close();	// 数据库关闭
		}
	}
	
	/**
	 * 更据id删除记录
	 * @param id
	 */
	public void delete(int id) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();	// 获得可写的数据库对象
		if(db.isOpen()) {	// 如果数据库打开, 执行添加的操作
			
			db.execSQL("delete from person where _id = ?;", new Integer[]{id});
			
			db.close();	// 数据库关闭
		}
	}
	

	/**
	 * 根据id找到记录, 并且修改姓名
	 * @param id
	 * @param name
	 */
	public void update(int id, String name) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()) {	// 如果数据库打开, 执行添加的操作
			
			db.execSQL("update person set name = ? where _id = ?;", new Object[]{name, id});
			
			db.close();	// 数据库关闭
		}
	}
	
	public List<Person> queryAll() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// 获得一个只读的数据库对象
		if(db.isOpen()) {
			
			Cursor cursor = db.rawQuery("select _id, name, age from person;", null);
			
			if(cursor != null && cursor.getCount() > 0) {
				List<Person> personList = new ArrayList<Person>();
				int id;
				String name;
				int age;
				while(cursor.moveToNext()) {
					id = cursor.getInt(0);	// 取第0列的数据 id
					name = cursor.getString(1);	// 取姓名
					age = cursor.getInt(2);		// 取年龄
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
	 * 根据id查询人
	 * @param id
	 * @return
	 */
	public Person queryItem(int id) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// 获得一个只读的数据库对象
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
