package com.itheima.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PlayMusicService extends Service {

	//�󶨷��� ���÷���ķ�����
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
}
