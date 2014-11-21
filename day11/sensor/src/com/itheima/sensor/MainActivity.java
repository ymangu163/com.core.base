package com.itheima.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	private SensorManager sm;
	private MyListener listener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		//光线传感器
		Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
		listener = new MyListener();
		sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
	}

	private class MyListener implements SensorEventListener{
		@Override
		public void onSensorChanged(SensorEvent event) {
			float light = event.values[0];
			System.out.println("light:"+light);
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
