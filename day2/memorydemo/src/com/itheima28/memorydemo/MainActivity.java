package com.itheima28.memorydemo;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tvMemoryInfo = (TextView) findViewById(R.id.tv_memory_info);
		
		// ���sd�����ڴ�״̬
		File sdcardFileDir = Environment.getExternalStorageDirectory();
		String sdcardMemory = getMemoryInfo(sdcardFileDir);
		
		// ����ֻ��ڲ��洢�ؼ���״̬
		File dataFileDir = Environment.getDataDirectory();
		String dataMemory = getMemoryInfo(dataFileDir);
		
		tvMemoryInfo.setText("SD��: " + sdcardMemory + "\n�ֻ��ڲ�: " + dataMemory);
	}

	/**
	 * ����·����ȡ�ڴ�״̬
	 * @param path
	 * @return
	 */
	private String getMemoryInfo(File path) {
		// ���һ������״̬����
        StatFs stat = new StatFs(path.getPath());
        
        long blockSize = stat.getBlockSize();	// ���һ�������Ĵ�С
        
        long totalBlocks = stat.getBlockCount();	// �������������
        
        long availableBlocks = stat.getAvailableBlocks();	// ��ÿ��õ���������
        
        // �ܿռ�
        String totalMemory =  Formatter.formatFileSize(this, totalBlocks * blockSize);
        // ���ÿռ�
        String availableMemory = Formatter.formatFileSize(this, availableBlocks * blockSize);
		
		return "�ܿռ�: " + totalMemory + "\n���ÿռ�: " + availableMemory;
	}
}
