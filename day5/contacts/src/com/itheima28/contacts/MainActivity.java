package com.itheima28.contacts;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	private void printCursor(Cursor cursor) {
		if(cursor != null && cursor.getCount() > 0) {
			
			while(cursor.moveToNext()) {
				
				int columnCount = cursor.getColumnCount(); // 列的总数
				
				for (int i = 0; i < columnCount; i++) {
					String columnName = cursor.getColumnName(i);	// 取对应i位置的列的名称
					String columnValue = cursor.getString(i); // 取出对应i位置的列的值
					
					Log.i(TAG, "当前是第" + cursor.getPosition() + "行: " + columnName + " = " + columnValue);
				}
			}
			cursor.close();
		}
	}

	/**
	 * 查询联系人
	 * @param v
	 */
	public void queryContacts(View v) {
		// 1. 去raw_contacts表中取所有联系人的_id
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		
		Cursor cursor = getContentResolver().query(uri, new String[]{"_id"}, null, null, null);
//		printCursor(cursor);
		if(cursor != null && cursor.getCount() > 0) {
			
			while(cursor.moveToNext()) {
				int id = cursor.getInt(0);
				// 2. 去data表中根据上面取到的_id查询对应id的数据.
				
				String selection = "raw_contact_id = ?";
				String[] selectionArgs = {String.valueOf(id)};
				Cursor c = getContentResolver().query(dataUri, new String[]{"data1", "mimetype"}, 
						selection, selectionArgs, null);
				if(c != null && c.getCount() > 0) {
					
					while(c.moveToNext()) {
						String mimetype = c.getString(1);		// 当前取的是mimetype的值
						String data1 = c.getString(0);		// 当前取的是data1数据
						
						if("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
							Log.i(TAG, "号码: " + data1);
						} else if("vnd.android.cursor.item/name".equals(mimetype)) {
							Log.i(TAG, "姓名: " + data1);
						} else if("vnd.android.cursor.item/email_v2".equals(mimetype)) {
							Log.i(TAG, "邮箱: " + data1);
						}
					}
					c.close();
				}
			}
			cursor.close();
		}

	}
	
	public void addContacts(View v) {
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		// 1. 在raw_contacts表中添加一个记录
		
		// 取raw_contacts表中contact_id的值
		Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, "contact_id desc limit 1");
		if(cursor != null && cursor.moveToFirst()) {
			int contact_id = cursor.getInt(0);
			contact_id ++;
			cursor.close();
			
			ContentValues values = new ContentValues();
			values.put("contact_id", contact_id);
			getContentResolver().insert(uri, values);

			// 2. 根据上面添加记录的id, 取data表中添加三条数据
			
			// 存号码
			values = new ContentValues();
			values.put("mimetype", "vnd.android.cursor.item/phone_v2");
			values.put("data1", "10086");
			values.put("raw_contact_id", contact_id);
			getContentResolver().insert(dataUri, values);
			
			// 存姓名
			values = new ContentValues();
			values.put("mimetype", "vnd.android.cursor.item/name");
			values.put("data1", "中国移动");
			values.put("raw_contact_id", contact_id);
			getContentResolver().insert(dataUri, values);
			
			// 存姓名
			values = new ContentValues();
			values.put("mimetype", "vnd.android.cursor.item/email_v2");
			values.put("data1", "10086@kengni.com");
			values.put("raw_contact_id", contact_id);
			getContentResolver().insert(dataUri, values);
		}

	}
}
