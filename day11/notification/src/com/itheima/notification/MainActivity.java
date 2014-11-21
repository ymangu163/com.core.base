package com.itheima.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void click(View view){
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.notification, "我是一个通知", System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_CALL);
		intent.setData(Uri.parse("tel:110"));
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, "我是标题", "我是内容", contentIntent);
		nm.notify(0, notification);
	}
	/**
	 * 新版本的notification
	 * @param view
	 */
	@SuppressLint("NewApi")
	public void click2(View view){
		 Notification noti = new Notification.Builder(this)
         .setContentTitle("我是标题")
         .setContentText("我是内容")
         .setSmallIcon(R.drawable.notification)
         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
         .build();
		 NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		 nm.notify(0, noti);
	}
}
