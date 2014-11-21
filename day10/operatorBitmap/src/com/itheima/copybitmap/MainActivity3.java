package com.itheima.copybitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv1,iv2;
	private Bitmap alterBitmap;
	private Bitmap srcBmp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		//给第一个imageview默认设置一个位图
		srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
		iv1.setImageBitmap(srcBmp);
		//创建原图的一个副本。 可修改  创建的是一个空白的图形。
		alterBitmap = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(),srcBmp.getConfig());
	}
	/**
	 * 创建原图 bm的一个拷贝。副本
	 * @param view
	 */
	public void click(View view){
		//1.准备一个画板  在上面放上准备好的 空白的位图
		Canvas canvas = new Canvas(alterBitmap);
		//2.准备一个画笔
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		//3.画画
		Matrix m = new Matrix();
		m.setRotate(180, srcBmp.getWidth()/2, srcBmp.getHeight()/2);
		
		canvas.drawBitmap(srcBmp, m, paint);
		iv2.setImageBitmap(alterBitmap);//把原图的副本设置到界面上。
	}
}
