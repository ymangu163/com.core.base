package com.itheima.tween;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}
	//透明度动画
	public void alpha(View view){
		AlphaAnimation  aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(2000);
		aa.setRepeatCount(1);
		aa.setRepeatMode(Animation.REVERSE);
		aa.setFillAfter(true);
		iv.startAnimation(aa);
	}
	//位移动画
	public void trans(View view){
		TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.0f, 
				Animation.RELATIVE_TO_PARENT, 0.0f);
		ta.setDuration(2000);
		ta.setRepeatCount(1);
		ta.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(ta);
	}
	//缩放动画
	public void scale(View view){
		ScaleAnimation sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setRepeatCount(1);
		sa.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(sa);
	}
	
	//旋转动画
	public void rotate(View view){
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		ra.setDuration(2000);
		ra.setRepeatCount(1);
		ra.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(ra);
	}
	//动画组合
	public void set(View view){
		AnimationSet set = new AnimationSet(false);
		TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.5f, 
				Animation.RELATIVE_TO_PARENT, -0.5f, 
				Animation.RELATIVE_TO_PARENT, 0.5f);
		ta.setDuration(2000);
		ta.setRepeatCount(1);
		ta.setRepeatMode(Animation.REVERSE);
		ScaleAnimation sa = new ScaleAnimation(0.1f, 2.0f, 0.1f, 2.0f, Animation.RELATIVE_TO_SELF, 
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setRepeatCount(1);
		sa.setRepeatMode(Animation.REVERSE);
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		ra.setDuration(2000);
		ra.setRepeatCount(1);
		ra.setRepeatMode(Animation.REVERSE);
		set.addAnimation(ra);
		//set.addAnimation(ta);
		set.addAnimation(sa);
		iv.startAnimation(set);
	}
}
