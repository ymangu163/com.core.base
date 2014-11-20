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
				
				int columnCount = cursor.getColumnCount(); // �е�����
				
				for (int i = 0; i < columnCount; i++) {
					String columnName = cursor.getColumnName(i);	// ȡ��Ӧiλ�õ��е�����
					String columnValue = cursor.getString(i); // ȡ����Ӧiλ�õ��е�ֵ
					
					Log.i(TAG, "��ǰ�ǵ�" + cursor.getPosition() + "��: " + columnName + " = " + columnValue);
				}
			}
			cursor.close();
		}
	}

	/**
	 * ��ѯ��ϵ��
	 * @param v
	 */
	public void queryContacts(View v) {
		// 1. ȥraw_contacts����ȡ������ϵ�˵�_id
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		
		Cursor cursor = getContentResolver().query(uri, new String[]{"_id"}, null, null, null);
//		printCursor(cursor);
		if(cursor != null && cursor.getCount() > 0) {
			
			while(cursor.moveToNext()) {
				int id = cursor.getInt(0);
				// 2. ȥdata���и�������ȡ����_id��ѯ��Ӧid������.
				
				String selection = "raw_contact_id = ?";
				String[] selectionArgs = {String.valueOf(id)};
				Cursor c = getContentResolver().query(dataUri, new String[]{"data1", "mimetype"}, 
						selection, selectionArgs, null);
				if(c != null && c.getCount() > 0) {
					
					while(c.moveToNext()) {
						String mimetype = c.getString(1);		// ��ǰȡ����mimetype��ֵ
						String data1 = c.getString(0);		// ��ǰȡ����data1����
						
						if("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
							Log.i(TAG, "����: " + data1);
						} else if("vnd.android.cursor.item/name".equals(mimetype)) {
							Log.i(TAG, "����: " + data1);
						} else if("vnd.android.cursor.item/email_v2".equals(mimetype)) {
							Log.i(TAG, "����: " + data1);
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
		// 1. ��raw_contacts�������һ����¼
		
		// ȡraw_contacts����contact_id��ֵ
		Cursor cursor = getContentResolver().query(uri, new String[]{"contact_id"}, null, null, "contact_id desc limit 1");
		if(cursor != null && cursor.moveToFirst()) {
			int contact_id = cursor.getInt(0);
			contact_id ++;
			cursor.close();
			
			ContentValues values = new ContentValues();
			values.put("contact_id", contact_id);
			getContentResolver().insert(uri, values);

			// 2. ����������Ӽ�¼��id, ȡdata���������������
			
			// �����
			values = new ContentValues();
			values.put("mimetype", "vnd.android.cursor.item/phone_v2");
			values.put("data1", "10086");
			values.put("raw_contact_id", contact_id);
			getContentResolver().insert(dataUri, values);
			
			// ������
			values = new ContentValues();
			values.put("mimetype", "vnd.android.cursor.item/name");
			values.put("data1", "�й��ƶ�");
			values.put("raw_contact_id", contact_id);
			getContentResolver().insert(dataUri, values);
			
			// ������
			values = new ContentValues();
			values.put("mimetype", "vnd.android.cursor.item/email_v2");
			values.put("data1", "10086@kengni.com");
			values.put("raw_contact_id", contact_id);
			getContentResolver().insert(dataUri, values);
		}

	}
}
