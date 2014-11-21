package com.itheima.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlayMusicService extends Service {

	//绑定服务 调用服务的方法。
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
}
