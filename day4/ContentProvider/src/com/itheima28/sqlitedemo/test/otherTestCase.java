package com.itheima28.othercontentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class TextCase extends AndroidTestCase {

	private static final String TAG = "TextCase";

	public void testInsert() {
		Uri uri = Uri.parse("content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/insert");
		
		// 内容提供者访问对象
		ContentResolver resolver = getContext().getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put("name", "fengjie");
		values.put("age", 90);
		
		uri = resolver.insert(uri, values);
		Log.i(TAG, "uri: " + uri);
		long id = ContentUris.parseId(uri);
		Log.i(TAG, "添加到: " + id);
	}
	
	public void testDelete() {
		Uri uri = Uri.parse("content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/delete");
		
		// 内容提供者访问对象
		ContentResolver resolver = getContext().getContentResolver();
		
		String where = "_id = ?";
		String[] selectionArgs = {"21"};
		int count = resolver.delete(uri, where, selectionArgs);
		Log.i(TAG, "删除行: " + count);
	}
	
	public void testUpdate() {
		Uri uri = Uri.parse("content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/update");
		
		// 内容提供者访问对象
		ContentResolver resolver = getContext().getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put("name", "lisi");
		
		int count = resolver.update(uri, values, "_id = ?", new String[]{"20"});
		Log.i(TAG, "更新行: " + count);
	}
	
	public void testQueryAll() {
		Uri uri = Uri.parse("content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/queryAll");
		
		// 内容提供者访问对象
		ContentResolver resolver = getContext().getContentResolver();
		
		Cursor cursor = resolver.query(uri, new String[]{"_id", "name", "age"}, null, null, "_id desc");
		
		if(cursor != null && cursor.getCount() > 0) {
			
			int id;
			String name;
			int age;
			while(cursor.moveToNext()) {
				id = cursor.getInt(0);
				name = cursor.getString(1);
				age = cursor.getInt(2);
				Log.i(TAG, "id: " + id + ", name: " + name + ", age: " + age);
			}
			cursor.close();
		}
	}
	
	public void testQuerySingleItem() {
		Uri uri = Uri.parse("content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/query/#");
		
		// 在uri的末尾添加一个id content://com.itheima28.sqlitedemo.providers.PersonContentProvider/person/query/20
		uri = ContentUris.withAppendedId(uri, 20);
		
		// 内容提供者访问对象
		ContentResolver resolver = getContext().getContentResolver();
		
		Cursor cursor = resolver.query(uri, new String[]{"_id", "name", "age"}, null, null, null);
		
		if(cursor != null && cursor.moveToFirst()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			int age = cursor.getInt(2);
			cursor.close();
			Log.i(TAG, "id: " + id + ", name: " + name + ", age: " + age);
		}
	}
}
