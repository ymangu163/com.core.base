package com.itheima.loadimg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}

	public void click(View view){
		//相当消耗内存资源 根据图片的分辨率而定
		// Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/photo.jpg");
		// iv.setImageBitmap(bitmap);
		
		//1.得到屏幕的宽高信息
		WindowManager wm = getWindowManager();
		int screenWidth = wm.getDefaultDisplay().getWidth();
		int screenHeight = wm.getDefaultDisplay().getHeight();
		System.out.println("屏幕宽高："+screenWidth+"-"+screenHeight);

		//2.得到图片的宽高。
		BitmapFactory.Options opts = new Options();//解析位图的附加条件
		opts.inJustDecodeBounds = true;//不去解析真实的位图，只是获取这个位图的头文件信息
		Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/photo.jpg", opts);
		int bitmapWidth = opts.outWidth;
		int bitmapHeight = opts.outHeight;
		System.out.println("图片宽高： "+bitmapWidth+"-"+bitmapHeight);
		
		//3.计算缩放比例
		int dx = bitmapWidth/screenWidth;
		int dy = bitmapHeight/screenHeight;
		int scale = 1;
		if(dx>dy&&dy>1){
			System.out.println("按照水平方法缩放,缩放比例："+dx);
			 scale = dx;
		}
		
		if(dy>dx&&dx>1){
			System.out.println("按照垂直方法缩放,缩放比例："+dy);
			scale = dy;
		}
		//4.缩放加载图片到内存。
		opts.inSampleSize = scale;
		opts.inJustDecodeBounds = false;//真正的去解析这个位图。
		bitmap = BitmapFactory.decodeFile("/mnt/sdcard/photo.jpg", opts);
		iv.setImageBitmap(bitmap);
	}
}
