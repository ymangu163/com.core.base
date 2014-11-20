package com.itheima28.xmldemo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;
import android.util.Xml;

import com.itheima28.xmldemo.Person;

public class TestCase extends AndroidTestCase {

	public void test() {
//		writeXmlToLocal();
		
		List<Person> personList = parserXmlFromLocal();
		
		for (Person person : personList) {
			Log.i("TestCase", person.toString());
		}
	}
	
	/**
	 * дxml�ļ�������
	 */
	private void writeXmlToLocal() {
		List<Person> personList = getPersonList();
		
		// ������л�����
		XmlSerializer serializer = Xml.newSerializer();
		
		try {
			File path = new File(Environment.getExternalStorageDirectory(), "persons.xml");
			FileOutputStream fos = new FileOutputStream(path);
			// ָ�����л����������λ�úͱ���
			serializer.setOutput(fos, "utf-8");
			
			serializer.startDocument("utf-8", true);	// д��ʼ <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
			
			serializer.startTag(null, "persons");		// <persons>
			
			for (Person person : personList) {
				// ��ʼд��

				serializer.startTag(null, "person");	// <person>
				serializer.attribute(null, "id", String.valueOf(person.getId()));
				
				// д����
				serializer.startTag(null, "name");		// <name>
				serializer.text(person.getName());
				serializer.endTag(null, "name");		// </name>
				
				// д����
				serializer.startTag(null, "age");		// <age>
				serializer.text(String.valueOf(person.getAge()));
				serializer.endTag(null, "age");		// </age>
				
				serializer.endTag(null, "person");	// </person>
			}
			
			serializer.endTag(null, "persons");			// </persons>
			
			serializer.endDocument();		// ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private List<Person> getPersonList() {
		List<Person> personList = new ArrayList<Person>();
		
		for (int i = 0; i < 30; i++) {
			personList.add(new Person(i, "wang" + i, 18 + i));
		}
		
		return personList;
	}
	
	private List<Person> parserXmlFromLocal() {
		try {
			File path = new File(Environment.getExternalStorageDirectory(), "persons.xml");
			FileInputStream fis = new FileInputStream(path);
			
			// ���pull����������
			XmlPullParser parser = Xml.newPullParser();
			// ָ���������ļ��ͱ����ʽ
			parser.setInput(fis, "utf-8");
			
			int eventType = parser.getEventType(); 		// ����¼�����
			
			List<Person> personList = null;
			Person person = null;
			String id;
			while(eventType != XmlPullParser.END_DOCUMENT) {
				String tagName = parser.getName();	// ��õ�ǰ�ڵ������
				
				switch (eventType) {
				case XmlPullParser.START_TAG: 		// ��ǰ���ڿ�ʼ�ڵ�  <person>
					if("persons".equals(tagName)) {	// <persons>
						personList = new ArrayList<Person>();
					} else if("person".equals(tagName)) { // <person id="1">
						person = new Person();
						id = parser.getAttributeValue(null, "id");
						person.setId(Integer.valueOf(id));
					} else if("name".equals(tagName)) { // <name>
						person.setName(parser.nextText());
					} else if("age".equals(tagName)) { // <age>
						person.setAge(Integer.parseInt(parser.nextText()));
					}
					break;
				case XmlPullParser.END_TAG:		// </persons>
					if("person".equals(tagName)) {
						// ��Ҫ���������ú�ֵ��person������ӵ�������
						personList.add(person);
					}
					break;
				default:
					break;
				}
				
				eventType = parser.next();		// �����һ���¼�����
			}
			return personList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
