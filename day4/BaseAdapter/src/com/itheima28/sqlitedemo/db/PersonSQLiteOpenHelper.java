package com.itheima28.sqlitedemo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author andong
 * ���ݿ������, ���ڴ����͹������ݿ��.
 */
public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "PersonSQLiteOpenHelper";

	/**
	 * ���ݿ�Ĺ��캯��
	 * @param context
	 * 
	 * name ���ݿ�����
	 * factory �α깤��
	 * version ���ݿ�İ汾�� ������С��1
	 */
	public PersonSQLiteOpenHelper(Context context) {
		super(context, "itheima28.db", null, 2);
	}

	/**
	 * ���ݿ��һ�δ���ʱ�ص��˷���.
	 * ��ʼ��һЩ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		// �������ݿ�
		String sql = "create table person(_id integer primary key, name varchar(20), age integer);";
		db.execSQL(sql);		// ����person��
	}

	/**
	 * ���ݿ�İ汾�Ÿ���ʱ�ص��˷���,
	 * �������ݿ������(ɾ����, ��ӱ�, �޸ı�)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		if(oldVersion == 1 && newVersion == 2) {
			Log.i(TAG, "���ݿ������");
			// ��person�������һ�������balance
			db.execSQL("alter table person add balance integer;");
		}
	}

}
