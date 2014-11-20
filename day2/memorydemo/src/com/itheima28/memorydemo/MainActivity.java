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
		
		// 获得sd卡的内存状态
		File sdcardFileDir = Environment.getExternalStorageDirectory();
		String sdcardMemory = getMemoryInfo(sdcardFileDir);
		
		// 获得手机内部存储控件的状态
		File dataFileDir = Environment.getDataDirectory();
		String dataMemory = getMemoryInfo(dataFileDir);
		
		tvMemoryInfo.setText("SD卡: " + sdcardMemory + "\n手机内部: " + dataMemory);
	}

	/**
	 * 根据路径获取内存状态
	 * @param path
	 * @return
	 */
	private String getMemoryInfo(File path) {
		// 获得一个磁盘状态对象
        StatFs stat = new StatFs(path.getPath());
        
        long blockSize = stat.getBlockSize();	// 获得一个扇区的大小
        
        long totalBlocks = stat.getBlockCount();	// 获得扇区的总数
        
        long availableBlocks = stat.getAvailableBlocks();	// 获得可用的扇区数量
        
        // 总空间
        String totalMemory =  Formatter.formatFileSize(this, totalBlocks * blockSize);
        // 可用空间
        String availableMemory = Formatter.formatFileSize(this, availableBlocks * blockSize);
		
		return "总空间: " + totalMemory + "\n可用空间: " + availableMemory;
	}
}
