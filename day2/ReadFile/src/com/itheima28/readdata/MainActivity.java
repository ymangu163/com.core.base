package com.itheima28.readdata;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private String basicPath = "/data/data/com.itheima28.writedata/files/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn_read_private).setOnClickListener(this);
		findViewById(R.id.btn_write_private).setOnClickListener(this);
		
		findViewById(R.id.btn_read_readable).setOnClickListener(this);
		findViewById(R.id.btn_write_readable).setOnClickListener(this);
		
		findViewById(R.id.btn_read_writeable).setOnClickListener(this);
		findViewById(R.id.btn_write_writeable).setOnClickListener(this);
		
		findViewById(R.id.btn_read_readable_writeable).setOnClickListener(this);
		findViewById(R.id.btn_write_readable_writeable).setOnClickListener(this);
	}

	/**
	 * 哪一个控件被点击, v对象就代表被点击的对象
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_read_private:
			readFile("private.txt");
			break;
		case R.id.btn_write_private:
			writeFile("private.txt");
			break;
		case R.id.btn_read_readable:
			readFile("readable.txt");
			break;
		case R.id.btn_write_readable:
			writeFile("readable.txt");
			break;
		case R.id.btn_read_writeable:
			readFile("writeable.txt");
			break;
		case R.id.btn_write_writeable:
			writeFile("writeable.txt");
			break;
		case R.id.btn_read_readable_writeable:
			readFile("readable_writeable.txt");
			break;
		case R.id.btn_write_readable_writeable:
			writeFile("readable_writeable.txt");
			break;
		default:
			break;
		}
	}

	/**
	 * 读文件
	 * @param fileName
	 */
	private void readFile(String fileName) {
		try {
			String path = basicPath + fileName;
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String text = reader.readLine();
			reader.close();
			Toast.makeText(this, "读取成功: " + text, 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "读取失败: " + fileName, 0).show();
		}
	}
	
	private void writeFile(String fileName) {
		try {
			String path = basicPath + fileName;
			
			FileOutputStream fos = new FileOutputStream(path);
			
			fos.write("哈哈, 被我给黑了".getBytes());
			
			fos.flush();
			
			fos.close();
			Toast.makeText(this, "写入成功: " + fileName, 0).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "写入失败: " + fileName, 0).show();
		}
	}
	
}
