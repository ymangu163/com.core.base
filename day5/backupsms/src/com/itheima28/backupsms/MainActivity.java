package com.itheima28.backupsms;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import com.itheima28.backupsms.entities.SmsInfo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	/**
	 * ���ݶ���
	 * @param v
	 */
	public void backupSms(View v) {
		// 1. ������еĶ���
		Uri uri = Uri.parse("content://sms/");
		
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(uri, new String[]{"_id", "address", "date", "type", "body"}, null, null, null);
		
		if(cursor != null && cursor.getCount() > 0) {
			List<SmsInfo> smsList = new ArrayList<SmsInfo>();
			SmsInfo sms;
			
			while(cursor.moveToNext()) {	// �����α�������ָ��������һλ, �������һλ, ֹͣ.����false
				sms = new SmsInfo();
				sms.setId(cursor.getInt(0));	// ���ö��ŵ�id
				sms.setAddress(cursor.getString(1));	// ���ö��ŵĺ���
				sms.setDate(cursor.getLong(2));	// ���ö��ŵ�����
				sms.setType(cursor.getInt(3));	// ���ö��ŵ�����, ����1���Ƿ���2
				sms.setBody(cursor.getString(4)); // ���ö��ŵ�����
				smsList.add(sms);
			}
			cursor.close();
			
			// 2. ���л�������
			writeToLocal(smsList);
		}
	}
	
	/**
	 * ���л�������
	 */
	private void writeToLocal(List<SmsInfo> smsList) {
		
		try {
			XmlSerializer serializer = Xml.newSerializer();	// �õ����л�����
			// ָ�����λ��
			FileOutputStream fos = new FileOutputStream("/mnt/sdcard/sms.xml");
			serializer.setOutput(fos, "utf-8");
			
			serializer.startDocument("utf-8", true);
			
			serializer.startTag(null, "smss");
			
			for (SmsInfo smsInfo : smsList) {
				serializer.startTag(null, "sms");
				serializer.attribute(null, "id", String.valueOf(smsInfo.getId()));
				
				// д����
				serializer.startTag(null, "address");
				serializer.text(smsInfo.getAddress());
				serializer.endTag(null, "address");

				// дʱ��
				serializer.startTag(null, "date");
				serializer.text(String.valueOf(smsInfo.getDate()));
				serializer.endTag(null, "date");
				
				//д����
				serializer.startTag(null, "type");
				serializer.text(String.valueOf(smsInfo.getType()));
				serializer.endTag(null, "type");
				
				// д����
				serializer.startTag(null, "body");
				serializer.text(smsInfo.getBody());
				serializer.endTag(null, "body");
				
				serializer.endTag(null, "sms");
			}
			
			serializer.endTag(null, "smss");
			
			serializer.endDocument();
			
			Toast.makeText(this, "���ݳɹ�", 0).show();
		} catch (Exception e) {
			Toast.makeText(this, "����ʧ��", 0).show();
			e.printStackTrace();
		}
		
	}
}
