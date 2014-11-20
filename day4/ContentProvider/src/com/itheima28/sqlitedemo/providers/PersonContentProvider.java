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
	private static final int PRESON_INSERT_CODE = 0;	// ����person����ӵĲ�����uriƥ����
	private static final int PERSON_DELETE_CODE = 1;
	private static final int PERSON_UPDATE_CODE = 2;
	private static final int PERSON_QUERY_ALL_CODE = 3;
	private static final int PERSON_QUERY_ITEM_CODE = 4;
	
	private static UriMatcher uriMatcher;
	private PersonSQLiteOpenHelper mOpenHelper;		// person������ݿ��������
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		// ���һЩuri(�ֻ���)
		
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
		case PERSON_QUERY_ALL_CODE:  // ��ѯ�����˵�uri
			if(db.isOpen()) {
				Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
				return cursor;
				// db.close(); ����cursor�����ʱ, �����Թر����ݿ�
			}
			break;
		case PERSON_QUERY_ITEM_CODE:		// ��ѯ���ǵ�������, uriĩβ����һ��id
			if(db.isOpen()) {
				
				long id = ContentUris.parseId(uri);
				
				Cursor cursor = db.query("person", projection, "_id = ?", new String[]{id + ""}, null, null, sortOrder);

				return cursor;
			}
			break;
		default:
			throw new IllegalArgumentException("uri��ƥ��: " + uri);
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case PERSON_QUERY_ALL_CODE: // ���ض�����MIME-type
			return "vnd.android.cursor.dir/person";
		case PERSON_QUERY_ITEM_CODE: // ���ص�����MIME-TYPE
			return "vnd.android.cursor.item/person";
		default:
			break;
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		switch (uriMatcher.match(uri)) {
		case PRESON_INSERT_CODE:	// ����˵�person����
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			
			if(db.isOpen()) {
				
				long id = db.insert("person", null, values);
				
				db.close();
				
				return ContentUris.withAppendedId(uri, id);
			}
			break;
		default:
			throw new IllegalArgumentException("uri��ƥ��: " + uri);
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		switch (uriMatcher.match(uri)) {
		case PERSON_DELETE_CODE:	// ��person����ɾ�����ݵĲ���
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if(db.isOpen()) {
				int count = db.delete("person", selection, selectionArgs);
				db.close();
				return count;
			}
			break;
		default:
			throw new IllegalArgumentException("uri��ƥ��: " + uri);
		}
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		switch (uriMatcher.match(uri)) {
		case PERSON_UPDATE_CODE: // ����person��Ĳ���
			SQLiteDatabase db = mOpenHelper.getWritableDatabase();
			if(db.isOpen()) {
				int count = db.update("person", values, selection, selectionArgs);
				db.close();
				return count;
			}
			break;
		default:
			throw new IllegalArgumentException("uri��ƥ��: " + uri);
		}
		return 0;
	}

}
