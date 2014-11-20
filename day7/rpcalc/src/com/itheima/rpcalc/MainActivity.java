package com.itheima.rpcalc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_name = (EditText) findViewById(R.id.et_name);
	}
	
	
	//显式意图  ：必须指定要激活的组件的完整包名和类名 （应用程序之间耦合在一起）
	// 一般激活自己应用的组件的时候 采用显示意图
	
	//隐式意图： 只需要指定要动作和数据就可以 （ 好处应用程序之间没有耦合）
	//激活别人写的应用  隐式意图， 不需要关心对方的包名和类名
	
	
	
	public void enter(View view){
		String name = et_name.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "请输入姓名", 0).show();
			return;
		}
		//意图     开电视  打人  打酱油
		Intent intent = new Intent(this, CalcActivity.class);
		intent.putExtra("name", name);
		//显式意图
		//intent.setClassName(getPackageName(), "com.itheima.rpcalc.CalcActivity");//不指定动作，也不指定数据 直接指定要激活的组件
		startActivity(intent);
		//隐式意图
		//intent.setAction(action);
		//intent.setData(data);
	}
}
