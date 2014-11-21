package com.itheima.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	private File file ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}

	public void click(View view) {
		Intent intent = new Intent();
		// 指定拍照的意图。
		intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
		file =  new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file)); // 指定保存文件的路径
		startActivityForResult(intent, 100);												
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==100){
			iv.setImageURI(Uri.fromFile(file));
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
