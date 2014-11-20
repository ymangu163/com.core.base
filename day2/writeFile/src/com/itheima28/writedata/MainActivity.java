package com.itheima28.writedata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// д����
		
		// ˽���ļ�
		writeToLocal("private.txt", Context.MODE_PRIVATE);
		// �ɶ��ļ�
		writeToLocal("readable.txt", Context.MODE_WORLD_READABLE);
		// ��д�ļ�
		writeToLocal("writeable.txt", Context.MODE_WORLD_WRITEABLE);
		// �ɶ���д�ļ�
		writeToLocal("readable_writeable.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
	}

	private void writeToLocal(String fileName, int mode) {
		try {
			FileOutputStream fos = openFileOutput(fileName, mode);
			fos.write(("��һ������д������: " + fileName).getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
