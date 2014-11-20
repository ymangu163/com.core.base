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
		
		
		// 写数据
		
		// 私有文件
		writeToLocal("private.txt", Context.MODE_PRIVATE);
		// 可读文件
		writeToLocal("readable.txt", Context.MODE_WORLD_READABLE);
		// 可写文件
		writeToLocal("writeable.txt", Context.MODE_WORLD_WRITEABLE);
		// 可读可写文件
		writeToLocal("readable_writeable.txt", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
	}

	private void writeToLocal(String fileName, int mode) {
		try {
			FileOutputStream fos = openFileOutput(fileName, mode);
			fos.write(("第一个程序写的数据: " + fileName).getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
