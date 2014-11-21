package com.itheima.musicplayer;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_path;
	
	private MediaPlayer mediaPlayer;
	
	private Button bt_play,bt_pause,bt_stop,bt_replay;
	
	private SurfaceView sv;
	private SurfaceHolder holder;
	
	private int position;
	private String filepath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		bt_play = (Button) findViewById(R.id.bt_play);
		bt_pause = (Button) findViewById(R.id.bt_pause);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_replay = (Button) findViewById(R.id.bt_replay);
		//�õ�surfaceview
		sv = (SurfaceView) findViewById(R.id.sv);
		//�õ���ʾ�������ݵ�����
		holder = sv.getHolder();
		//�ڵͰ汾ģ���������мǵü�������Ĳ��������Լ�ά��˫�����������ǵȴ���ý�岥�ſ���������������ݡ�
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		holder.addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				System.out.println("destoryed");
				if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
					position = mediaPlayer.getCurrentPosition();
					mediaPlayer.stop();
					mediaPlayer.release();
					mediaPlayer = null;
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				System.out.println("created");
				if(position>0){//��¼���в��Ž��ȡ�
					try {
						mediaPlayer = new MediaPlayer();
						mediaPlayer.setDataSource(filepath);//���ò��ŵ�����Դ��
						mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
						mediaPlayer.setDisplay(holder);
						mediaPlayer.prepare();//׼����ʼ���� ���ŵ��߼���c�������µ��߳�����ִ�С�
						mediaPlayer.start();
						mediaPlayer.seekTo(position);
						bt_play.setEnabled(false);
						mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
							@Override
							public void onCompletion(MediaPlayer mp) {
								bt_play.setEnabled(true);
							}
						});
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				System.out.println("changed");
			}
		});
		
	}
	/**
	 * ����
	 * @param view
	 */
	public void play(View view) {
		filepath = et_path.getText().toString().trim();
		//File file = new File(filepath);
		if(filepath.startsWith("http://")){
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(filepath);//���ò��ŵ�����Դ��
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setDisplay(holder);
				mediaPlayer.prepare();//׼����ʼ���� ���ŵ��߼���c�������µ��߳�����ִ�С�
				mediaPlayer.start();
				bt_play.setEnabled(false);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						bt_play.setEnabled(true);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "����ʧ��", 0).show();
			}
		}else{
			Toast.makeText(this, "�ļ������ڣ������ļ���·��", 0).show();
		}
	}
	/**
	 * ��ͣ
	 * @param view
	 */
	public void pause(View view) {
		if("����".equals(bt_pause.getText().toString())){
			mediaPlayer.start();
			bt_pause.setText("��ͣ");
			return;
		}
		if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
			mediaPlayer.pause();
			bt_pause.setText("����");
		}
	}
	/**
	 * ֹͣ
	 * @param view
	 */
	public void stop(View view) {
		if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		bt_pause.setText("��ͣ");
		bt_play.setEnabled(true);
	}
	/**
	 * �ز�
	 * @param view
	 */
	public void replay(View view) {
		if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
			mediaPlayer.seekTo(0);
		}else{
			play(view);
		}
		bt_pause.setText("��ͣ");
	}

}
