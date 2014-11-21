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
	// �����޸ĵ�λͼ
	private Bitmap alertBitmap;
	private Canvas canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pre);
		// ����һ���հ׵�ԭͼ�Ŀ���
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
				case MotionEvent.ACTION_DOWN:// ��ָ������Ļ
					System.out.println("action down");
					break;
				case MotionEvent.ACTION_MOVE:// ��ָ����Ļ���ƶ�
					int x = (int) event.getX();
					int y = (int) event.getY();
					System.out.println("����("+x+","+y+")͸����ɫ");
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
				case MotionEvent.ACTION_UP:// ��ָ�뿪��Ļ
					MediaPlayer.create(getApplicationContext(), R.raw.higirl).start();
					break;
				}
				return true;//�����ظ�ѭ���Ĵ����¼�
			}
		});
	}

}
