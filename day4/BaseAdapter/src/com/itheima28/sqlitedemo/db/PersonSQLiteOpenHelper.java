package com.itheima28.sqlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author andong
 * 数据库帮助类, 用于创建和管理数据库的.
 */
public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "PersonSQLiteOpenHelper";

	/**
	 * 数据库的构造函数
	 * @param context
	 * 
	 * name 数据库名称
	 * factory 游标工程
	 * version 数据库的版本号 不可以小于1
	 */
	public PersonSQLiteOpenHelper(Context context) {
		super(context, "itheima28.db", null, 2);
	}

	/**
	 * 数据库第一次创建时回调此方法.
	 * 初始化一些表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		// 操作数据库
		String sql = "create table person(_id integer primary key, name varchar(20), age integer);";
		db.execSQL(sql);		// 创建person表
	}

	/**
	 * 数据库的版本号更新时回调此方法,
	 * 更新数据库的内容(删除表, 添加表, 修改表)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if(oldVersion == 1 && newVersion == 2) {
			Log.i(TAG, "数据库更新啦");
			// 在person表中添加一个余额列balance
			db.execSQL("alter table person add balance integer;");
		}
	}

}
