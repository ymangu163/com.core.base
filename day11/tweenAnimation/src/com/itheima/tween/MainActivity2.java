package com.itheima.tween;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
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
		Animation aa = AnimationUtils.loadAnimation(this, R.anim.alpha);
		iv.startAnimation(aa);
	}
	//位移动画
	public void trans(View view){
		Animation ta = AnimationUtils.loadAnimation(this, R.anim.trans);
		iv.startAnimation(ta);
	}
	//缩放动画
	public void scale(View view){
		Animation sa = AnimationUtils.loadAnimation(this, R.anim.scale);
		iv.startAnimation(sa);
	}
	
	//旋转动画
	public void rotate(View view){
		Animation ra = AnimationUtils.loadAnimation(this, R.anim.rotate);
		iv.startAnimation(ra);
	}
	//动画组合
	public void set(View view){
		Animation set = AnimationUtils.loadAnimation(this, R.anim.set);
		iv.startAnimation(set);
	}
}
