package com.itheima28.sqlitedemo.providers;

import com.itheima28.sqlitedemo.db.PersonSQLiteOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonContentProvider extends ContentProvider {
	
	private static final String AUTHORITY = "com.itheima28.sqlitedemo.providers.PersonContentProvider";
	private static final int PRESON_INSERT_CODE = 0;	// 操作person表添加的操作的uri匹配码
	private static final int PERSON_DELETE_CODE = 1;
	private static final int PERSON_UPDATE_CODE = 2;
	private static final int PERSON_QUERY_ALL_CODE = 3;
	private static final int PERSON_QUERY_ITEM_CODE = 4;
	
	private static UriMatcher uriMatcher;
	private PersonSQLiteOpenHelper mOpenHelper;		// person表的数据库帮助对象
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		// 添加一些uri(分机号)
		
		// content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/insert
		uriMatcher.addURI(AUTHORITY, "person/insert", PRESON_INSERT_CODE);
		
		// content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/delete
		uriMatcher.addURI(AUTHORITY, "person/delete", PERSON_DELETE_CODE);

		// content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/update
		uriMatcher.addURI(AUTHORITY, "person/update", PERSON_UPDATE_CODE);
		
		// content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/queryAll
		uriMatcher.addURI(AUTHORITY, "person/queryAll", PERSON_QUERY_ALL_CODE);
		
		// content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/query/#
		uriMatcher.addURI(AUTHORITY, "person/query/#", PERSON_QUERY_ITEM_CODE);
	}

	@Override
	public boolean onCreate() {
		mOpenHelper = new PersonSQLiteOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		switch (uriMatcher.match(uri)) {
		case PERSON_QUERY_ALL_CODE:  // 查询所有人的uri
			if(db.isOpen()) {
				Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
				return cursor;
				// db.close(); 返回cursor结果集时, 不可以关闭数据库
			}
			break;
		case PERSON_QUERY_ITEM_CODE:		// 查询的是单条数据, uri末尾出有一个id
			if(db.isOpen()) {
				
				long id = ContentUris.parseId(uri);
				
				Cursor cursor = db.query("person", projection, "_id = ?", new String[]{id + ""}, null, null, sortOrder);

				return cursor;
			}
			break;
		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case PERSON_QUERY_ALL_CODE: // 返回多条的MIME-type
			return "vnd.android.cursor.dir/person";
		case PERSON_QUERY_ITEM_CODE: // 返回单条的MIME-TYPE
			return "vnd.android.cursor.item/person";
		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		switch (uriMatcher.match(uri)) {
		case PRESON_INSERT_CODE:	// 添加人到person表中
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			
			if(db.isOpen()) {
				
				long id = db.insert("person", null, values);
				
				db.close();
				
				return ContentUris.withAppendedId(uri, id);
			}
			break;
		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		switch (uriMatcher.match(uri)) {
		case PERSON_DELETE_CODE:	// 在person表中删除数据的操作
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if(db.isOpen()) {
				int count = db.delete("person", selection, selectionArgs);
				db.close();
				return count;
			}
			break;
		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		switch (uriMatcher.match(uri)) {
		case PERSON_UPDATE_CODE: // 更新person表的操作
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if(db.isOpen()) {
				int count = db.update("person", values, selection, selectionArgs);
				db.close();
				return count;
			}
			break;
		default:
			throw new IllegalArgumentException("uri不匹配: " + uri);
		}
		return 0;
	}

}
