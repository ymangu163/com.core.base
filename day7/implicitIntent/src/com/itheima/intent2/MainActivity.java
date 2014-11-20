package com.itheima.intent2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		// 打 action
		// 人 数据
		// 附件的数据 Category 类别
		Intent intent = new Intent();
		intent.setAction("com.itheima.intent2.open2");
		intent.addCategory(Intent.CATEGORY_DEFAULT);

		// URL:统一资源定位符 http https ftp rtsp: URI：统一资源标识符 url是uri的一个子集
		// intent.setData(Uri.parse("jianren:张三"));
		// intent.setType("application/person");
		intent.setDataAndType(Uri.parse("jianren:张三"), "application/person");
		startActivity(intent);
	}
}
