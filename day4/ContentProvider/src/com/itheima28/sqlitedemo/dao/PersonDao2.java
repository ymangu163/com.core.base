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
	private PersonSQLiteOpenHelper mOpenHelper;	// 数据库的帮助类对象

	public PersonDao2(Context context) {
		mOpenHelper = new PersonSQLiteOpenHelper(context);
	}
	
	/**
	 * 添加到person表一条数据
	 * @param person
	 */
	public void insert(Person person) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		if(db.isOpen()) {	// 如果数据库打开, 执行添加的操作
			
			ContentValues values = new ContentValues();
			values.put("name", person.getName());		// key作为要存储的列名, value对象列的值
			values.put("age", person.getAge());
			long id = db.insert("person", "name", values);
			Log.i(TAG, "id: " + id);
			
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
			
			String whereClause = "_id = ?";
			String[] whereArgs = {id + ""};
			int count = db.delete("person", whereClause, whereArgs);
			Log.i(TAG, "删除了: " + count + "行");
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
			ContentValues values = new ContentValues();
			values.put("name", name);
			
			int count  = db.update("person", values, "_id = ?", new String[]{id + ""});

			Log.i(TAG, "修改了: " + count + "行");
			
			db.close();	// 数据库关闭
		}
	}
	
	public List<Person> queryAll() {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// 获得一个只读的数据库对象
		if(db.isOpen()) {
			String[] columns = {"_id", "name", "age"};	// 需要的列
			String selection = null;	// 选择条件, 给null查询所有
			String[] selectionArgs = null;	// 选择条件的参数, 会把选择条件中的? 替换成数据中的值
			String groupBy = null;	// 分组语句  group by name
			String having = null;	// 过滤语句
			String orderBy = null;	// 排序
			
			Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
			
			int id;
			String name;
			int age;
			if(cursor != null && cursor.getCount() > 0) {
				List<Person> personList = new ArrayList<Person>();
				
				while(cursor.moveToNext()) {	// 向下移一位, 知道最后一位, 不可以往下移动了, 停止.
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
	 * 根据id查询人
	 * @param id
	 * @return
	 */
	public Person queryItem(int id) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();	// 获得一个只读的数据库对象
		if(db.isOpen()) {
			String[] columns = {"_id", "name", "age"};	// 需要的列
			String selection = "_id = ?";	// 选择条件, 给null查询所有
			String[] selectionArgs = {id + ""};	// 选择条件的参数, 会把选择条件中的? 替换成数据中的值
			String groupBy = null;	// 分组语句  group by name
			String having = null;	// 过滤语句
			String orderBy = null;	// 排序
			
			Cursor cursor = db.query("person", columns, selection, selectionArgs, groupBy, having, orderBy);
			
			if(cursor != null && cursor.moveToFirst()) {		// cursor对象不为null, 并且可以移动到第一行
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
