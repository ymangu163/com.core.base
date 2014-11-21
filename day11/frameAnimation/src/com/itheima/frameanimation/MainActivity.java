package com.itheima.frameanimation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;
	private AnimationDrawable mAnimationDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		// ��xml�ļ��Ķ�����Դ����Ϊiv����
		iv.setBackgroundResource(R.drawable.girl);
		// ��ȡ���õĶ�����Դ�� ִ�п�����Ҫ����һ����ʱ��
		mAnimationDrawable = (AnimationDrawable) iv.getBackground();
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mAnimationDrawable.start();
			return true;
		}
		return super.onTouchEvent(event);
	}
}
