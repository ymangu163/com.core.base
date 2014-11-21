package com.itheima.musicplayer;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_path;
	private MediaPlayer mediaPlayer;
	
	private Button bt_play,bt_pause,bt_stop,bt_replay;
	
	private SurfaceView sv;
	private SurfaceHolder holder;
	
	private int position;
	private String filepath;

	private SeekBar seekBar1;
	
	private Timer timer;
	private TimerTask task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		bt_play = (Button) findViewById(R.id.bt_play);
		bt_pause = (Button) findViewById(R.id.bt_pause);
		bt_stop = (Button) findViewById(R.id.bt_stop);
		bt_replay = (Button) findViewById(R.id.bt_replay);
		
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int postion = seekBar.getProgress();
				mediaPlayer.seekTo(postion);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
		//得到surfaceview
		sv = (SurfaceView) findViewById(R.id.sv);
		//得到显示界面内容的容器
		holder = sv.getHolder();
		//在低版本模拟器上运行记得加上下面的参数。不自己维护双缓冲区，而是等待多媒体播放框架主动的推送数据。
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
					timer.cancel();
					task.cancel();
					timer = null;
					task = null;
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				System.out.println("created");
				if(position>0){//记录的有播放进度。
					try {
						mediaPlayer = new MediaPlayer();
						mediaPlayer.setDataSource(filepath);//设置播放的数据源。
						mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
						mediaPlayer.setDisplay(holder);
						mediaPlayer.prepare();//准备开始播放 播放的逻辑是c代码在新的线程里面执行。
						mediaPlayer.start();
						mediaPlayer.seekTo(position);
						bt_play.setEnabled(false);
						mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
							@Override
							public void onCompletion(MediaPlayer mp) {
								bt_play.setEnabled(true);
							}
						});
						int max = mediaPlayer.getDuration();
						seekBar1.setMax(max);
						timer = new Timer();
						task = new TimerTask() {
							@Override
							public void run() {
								seekBar1.setProgress(mediaPlayer.getCurrentPosition());
							}
						};
						timer.schedule(task, 0, 500);
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
	 * 播放
	 * @param view
	 */
	public void play(View view) {
		filepath = et_path.getText().toString().trim();
		File file = new File(filepath);
		if(file.exists()){
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(filepath);//设置播放的数据源。
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setDisplay(holder);
				mediaPlayer.prepare();//准备开始播放 播放的逻辑是c代码在新的线程里面执行。
				mediaPlayer.start();
				//设置拖动进度条的最大值
				int max = mediaPlayer.getDuration();
				seekBar1.setMax(max);
				timer = new Timer();
				task = new TimerTask() {
					@Override
					public void run() {
						seekBar1.setProgress(mediaPlayer.getCurrentPosition());
					}
				};
				timer.schedule(task, 0, 500);
				
				bt_play.setEnabled(false);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						bt_play.setEnabled(true);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(this, "播放失败", 0).show();
			}
		}else{
			Toast.makeText(this, "文件不存在，请检查文件的路径", 0).show();
		}
	}
	/**
	 * 暂停
	 * @param view
	 */
	public void pause(View view) {
		if("继续".equals(bt_pause.getText().toString())){
			mediaPlayer.start();
			bt_pause.setText("暂停");
			return;
		}
		if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
			mediaPlayer.pause();
			bt_pause.setText("继续");
		}
	}
	/**
	 * 停止
	 * @param view
	 */
	public void stop(View view) {
		if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		bt_pause.setText("暂停");
		bt_play.setEnabled(true);
	}
	/**
	 * 重播
	 * @param view
	 */
	public void replay(View view) {
		if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
			mediaPlayer.seekTo(0);
		}else{
			play(view);
		}
		bt_pause.setText("暂停");
	}

}
