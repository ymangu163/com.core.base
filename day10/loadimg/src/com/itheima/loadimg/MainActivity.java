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
		//�൱�����ڴ���Դ ����ͼƬ�ķֱ��ʶ���
		// Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/photo.jpg");
		// iv.setImageBitmap(bitmap);
		
		//1.�õ���Ļ�Ŀ����Ϣ
		WindowManager wm = getWindowManager();
		int screenWidth = wm.getDefaultDisplay().getWidth();
		int screenHeight = wm.getDefaultDisplay().getHeight();
		System.out.println("��Ļ��ߣ�"+screenWidth+"-"+screenHeight);

		//2.�õ�ͼƬ�Ŀ�ߡ�
		BitmapFactory.Options opts = new Options();//����λͼ�ĸ�������
		opts.inJustDecodeBounds = true;//��ȥ������ʵ��λͼ��ֻ�ǻ�ȡ���λͼ��ͷ�ļ���Ϣ
		Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/photo.jpg", opts);
		int bitmapWidth = opts.outWidth;
		int bitmapHeight = opts.outHeight;
		System.out.println("ͼƬ��ߣ� "+bitmapWidth+"-"+bitmapHeight);
		
		//3.�������ű���
		int dx = bitmapWidth/screenWidth;
		int dy = bitmapHeight/screenHeight;
		int scale = 1;
		if(dx>dy&&dy>1){
			System.out.println("����ˮƽ��������,���ű�����"+dx);
			 scale = dx;
		}
		
		if(dy>dx&&dx>1){
			System.out.println("���մ�ֱ��������,���ű�����"+dy);
			scale = dy;
		}
		//4.���ż���ͼƬ���ڴ档
		opts.inSampleSize = scale;
		opts.inJustDecodeBounds = false;//������ȥ�������λͼ��
		bitmap = BitmapFactory.decodeFile("/mnt/sdcard/photo.jpg", opts);
		iv.setImageBitmap(bitmap);
	}
}
