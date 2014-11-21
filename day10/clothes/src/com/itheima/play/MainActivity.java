package com.itheima.play;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	// 可以修改的位图
	private Bitmap alertBitmap;
	private Canvas canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pre);
		// 创建一个空白的原图的拷贝
		alertBitmap = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), bitmap.getConfig());
		canvas = new Canvas(alertBitmap);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		canvas.drawBitmap(bitmap, new Matrix(), paint);
		iv.setImageBitmap(alertBitmap);
		iv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:// 手指按下屏幕
					System.out.println("action down");
					break;
				case MotionEvent.ACTION_MOVE:// 手指在屏幕上移动
					int x = (int) event.getX();
					int y = (int) event.getY();
					System.out.println("设置("+x+","+y+")透明颜色");
					for(int i=-4;i<5;i++){
						for(int j=-4;j<5;j++){
							try{
							alertBitmap.setPixel(x+i, y+j, Color.TRANSPARENT);
							}catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
					iv.setImageBitmap(alertBitmap);
					break;
				case MotionEvent.ACTION_UP:// 手指离开屏幕
					MediaPlayer.create(getApplicationContext(), R.raw.higirl).start();
					break;
				}
				return true;//可以重复循环的处理事件
			}
		});
	}

}
