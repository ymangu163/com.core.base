package com.itheima28.caller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author andong
 * 程序刚运行就显示的界面
 */
public class MainUI4 extends Activity implements OnClickListener {

	/**
	 * 当界面刚被创建时回调此方法
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		// 必须执行此句代码. 执行父类的初始化操作.
		
		setContentView(R.layout.main);		// 设置当前界面显示的布局.
		
		
		Button btnCall = (Button) findViewById(R.id.btn_call);
		
		btnCall.setOnClickListener(this);
	}
	
//	class MyOnClickListener implements OnClickListener {
//
//		@Override
//		public void onClick(View v) {
//			System.out.println("MainUI3 拨打号码..");
//			call();
//		}
//	}
	
	/**
	 * 拨打电话的业务方法
	 */
	private void call() {
		
		// 1. 取出输入框中的号码
		EditText etNumber = (EditText) findViewById(R.id.number);	// 输入框对象
		String number = etNumber.getText().toString();	// 将要拨打的号码
		
		// 2. 根据号码拨打电话
		Intent intent = new Intent();		// 创建一个意图
		intent.setAction(Intent.ACTION_CALL);		// 指定其动作为拨打电话
		intent.setData(Uri.parse("tel:" + number));	// 指定将要拨出的号码
		startActivity(intent);	// 执行这个动作
	}

	@Override
	public void onClick(View v) {
		System.out.println("MainUI4 拨打号码..");
		call();
	}
}
