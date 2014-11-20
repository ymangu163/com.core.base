package com.itheima.mutilethread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int DOWNLOAD_ERROR = 1;
	private static final int THREAD_ERROR = 2;
	public static final int DWONLOAD_FINISH = 3;
	private EditText et_path;
	private EditText et_count;
	/**
	 * ��Ž������Ĳ���
	 */
	private LinearLayout ll_container;
	
	/**
	 * �������ļ���
	 */
	private List<ProgressBar> pbs;
	
	/**
	 * android�µ���Ϣ�������������̴߳������ſ��Ը���ui
	 */
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_ERROR:
				Toast.makeText(getApplicationContext(), "����ʧ��", 0).show();
				break;
			case THREAD_ERROR:
				Toast.makeText(getApplicationContext(), "����ʧ��,������", 0).show();
				break;
			case DWONLOAD_FINISH:
				Toast.makeText(getApplicationContext(), "�������", 0).show();
				break;
			}
		};
	};
	
	/**
	 * �̵߳�����
	 */
	private int threadCount = 3;

	/**
	 * ÿ����������Ĵ�С
	 */
	private long blocksize;

	/**
	 * �������е��̵߳�����
	 */
	private  int runningThreadCount;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_path = (EditText) findViewById(R.id.et_path);
		et_count = (EditText) findViewById(R.id.et_count);
		ll_container = (LinearLayout) findViewById(R.id.ll_container);
	}

	/**
	 * ���ذ�ť�ĵ���¼�
	 * @param view
	 */
	public void downLoad(View view){
		//�����ļ���·��
		final String path = et_path.getText().toString().trim();
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "�Բ�������·������Ϊ��", 0).show();
			return;
		}
		String count = et_count.getText().toString().trim();
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "�Բ���,�߳���������Ϊ��", 0).show();
			return;
		}
		threadCount = Integer.parseInt(count);
		//��յ��ɵĽ�����
		ll_container.removeAllViews();
		//�ڽ����������count��������
		pbs = new ArrayList<ProgressBar>();
		for(int j=0;j<threadCount;j++){
			ProgressBar pb = (ProgressBar) View.inflate(this, R.layout.pb, null);
			ll_container.addView(pb);
			pbs.add(pb);
		}
		Toast.makeText(this, "��ʼ����", 0).show();
		new Thread(){
			public void run() {
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(5000);
					int code = conn.getResponseCode();
					if (code == 200) {
						long size = conn.getContentLength();// �õ�����˷��ص��ļ��Ĵ�С
						System.out.println("�������ļ��Ĵ�С��" + size);
						blocksize = size / threadCount;
						// 1.�����ڱ��ش���һ����С��������һģһ���Ŀհ��ļ���
						File file = new File(Environment.getExternalStorageDirectory(),getFileName(path));
						RandomAccessFile raf = new RandomAccessFile(file, "rw");
						raf.setLength(size);
						// 2.�������ɸ����̷ֱ߳�ȥ���ض�Ӧ����Դ��
						runningThreadCount = threadCount;
						for (int i = 1; i <= threadCount; i++) {
							long startIndex = (i - 1) * blocksize;
							long endIndex = i * blocksize - 1;
							if (i == threadCount) {
								// ���һ���߳�
								endIndex = size - 1;
							}
							System.out.println("�����̣߳�" + i + "���ص�λ�ã�" + startIndex + "~"
									+ endIndex);
							int threadSize = (int) (endIndex - startIndex);
							pbs.get(i-1).setMax(threadSize);
							new DownloadThread(path, i, startIndex, endIndex).start();
						}
					}
					conn.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what = DOWNLOAD_ERROR;
					handler.sendMessage(msg);
				}
				
			};
		}.start();
		
	}
	private class DownloadThread extends Thread {
		
		private int threadId;
		private long startIndex;
		private long endIndex;
		private String path;

		public DownloadThread(String path, int threadId, long startIndex,
				long endIndex) {
			this.path = path;
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			try {
				// ��ǰ�߳����ص��ܴ�С
				int total = 0;
				File positionFile = new File(Environment.getExternalStorageDirectory(),getFileName(path)+threadId + ".txt");
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setRequestMethod("GET");
				// ���Ŵ���һ�ε�λ�ü�����������
				if (positionFile.exists() && positionFile.length() > 0) {// �ж��Ƿ��м�¼
					FileInputStream fis = new FileInputStream(positionFile);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(fis));
					// ��ȡ��ǰ�߳��ϴ����ص��ܴ�С�Ƕ���
					String lasttotalstr = br.readLine();
					int lastTotal = Integer.valueOf(lasttotalstr);
					System.out.println("�ϴ��߳�" + threadId + "���ص��ܴ�С��"
							+ lastTotal);
					startIndex += lastTotal;
					total += lastTotal;// �����ϴ����ص��ܴ�С��
					fis.close();
					//�����ݿ⡣
					//_id path threadid total
				}

				conn.setRequestProperty("Range", "bytes=" + startIndex + "-"
						+ endIndex);
			
				conn.setConnectTimeout(5000);
				int code = conn.getResponseCode();
				System.out.println("code=" + code);
				InputStream is = conn.getInputStream();
				File file = new File(Environment.getExternalStorageDirectory(),getFileName(path));
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				// ָ���ļ���ʼд��λ�á�
				raf.seek(startIndex);
				System.out.println("��" + threadId + "���̣߳�д�ļ��Ŀ�ʼλ�ã�"
						+ String.valueOf(startIndex));
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					RandomAccessFile rf = new RandomAccessFile(positionFile,
							"rwd");
					raf.write(buffer, 0, len);
					total += len;
					rf.write(String.valueOf(total).getBytes());
					rf.close();
					pbs.get(threadId-1).setProgress(total);
				}
				is.close();
				raf.close();

			} catch (Exception e) {
				e.printStackTrace();
				Message msg = Message.obtain();
				msg.what = THREAD_ERROR;
				handler.sendMessage(msg);
			} finally {
				// ֻ�����е��̶߳�������Ϻ� �ſ���ɾ����¼�ļ���
				synchronized (MainActivity.class) {
					System.out.println("�߳�" + threadId + "���������");
					runningThreadCount--;
					if (runningThreadCount < 1) {
						System.out.println("���е��̶߳���������ˡ�ɾ����ʱ��¼���ļ�");
						for (int i = 1; i <= threadCount; i++) {
							File f = new File(Environment.getExternalStorageDirectory(),getFileName(path)+ i + ".txt");
							System.out.println(f.delete());
						}
						Message msg = Message.obtain();
						msg.what = DWONLOAD_FINISH;
						handler.sendMessage(msg);
					}
				}

			}
		}
	}
	//http://192.168.1.100:8080/aa.exe
	private String getFileName(String path){
		int start = path.lastIndexOf("/")+1;
		return path.substring(start);
	}
	
}
