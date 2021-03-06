package com.itheima28.caller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * @author andong
 * 程序刚运行就显示的界面
 */
public class MainUI extends Activity {

	/**
	 * 当界面刚被创建时回调此方法
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		// 必须执行此句代码. 执行父类的初始化操作.
		
		setContentView(R.layout.main);		// 设置当前界面显示的布局.
	}
	
	/**
	 * 当拨打此号码的按钮被点击时触发此方法.
	 * @param v
	 */
	public void call(View v) {
		System.out.println("拨打电话.");
		
		// 1. 取出输入框中的号码
		EditText etNumber = (EditText) findViewById(R.id.number);	// 输入框对象
		String number = etNumber.getText().toString();	// 将要拨打的号码
		
		// 2. 根据号码拨打电话
		Intent intent = new Intent();		// 创建一个意图
		intent.setAction(Intent.ACTION_CALL);		// 指定其动作为拨打电话
		intent.setData(Uri.parse("tel:" + number));	// 指定将要拨出的号码
		startActivity(intent);	// 执行这个动作
	}
}
