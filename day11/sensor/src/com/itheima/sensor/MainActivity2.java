package com.itheima.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private SensorManager sm;
	private MyListener listener;
	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		iv = (ImageView) findViewById(R.id.iv);
		//方向传感器
		Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		listener = new MyListener();
		sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
	}

	private class MyListener implements SensorEventListener{
		float lastangle = 0;
		@Override
		public void onSensorChanged(SensorEvent event) {
			// 0=North, 90=East, 180=South, 270=West  
			float angle = event.values[0];//手机与正北方向的夹角
			System.out.println("angle:"+angle);
			RotateAnimation ra = new RotateAnimation(-lastangle, angle,
					Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
			iv.startAnimation(ra);
			lastangle = angle;
		}
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	}
	@Override
	protected void onDestroy() {
		sm.unregisterListener(listener);
		listener = null;
		super.onDestroy();
	}

}
