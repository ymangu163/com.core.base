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
		//����һ��imageviewĬ������һ��λͼ
		srcBmp = BitmapFactory.decodeResource(getResources(), R.drawable.tomcat);
		iv1.setImageBitmap(srcBmp);
		//����ԭͼ��һ�������� ���޸�  ��������һ���հ׵�ͼ�Ρ�
		alterBitmap = Bitmap.createBitmap(srcBmp.getWidth(), srcBmp.getHeight(),srcBmp.getConfig());
	}
	/**
	 * ����ԭͼ bm��һ������������
	 * @param view
	 */
	public void click(View view){
		//1.׼��һ������  ���������׼���õ� �հ׵�λͼ
		Canvas canvas = new Canvas(alterBitmap);
		//2.׼��һ������
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		//3.����
		Matrix m = new Matrix();
		m.setRotate(180, srcBmp.getWidth()/2, srcBmp.getHeight()/2);
		
		canvas.drawBitmap(srcBmp, m, paint);
		iv2.setImageBitmap(alterBitmap);//��ԭͼ�ĸ������õ������ϡ�
	}
}
