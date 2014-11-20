package com.itheima28.neteasedemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

import com.itheima28.neteasedemo.domain.NewInfo;
import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private final int SUCCESS = 0;
    private final int FAILED = 1;
	private ListView lvNews;
	private List<NewInfo> newInfoList;
    
    private Handler handler = new Handler() {

		/**
    	 * ������Ϣ
    	 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SUCCESS:		// ���ʳɹ�, ������
				// ��Listview�б������
				
				newInfoList = (List<NewInfo>) msg.obj;

				MyAdapter adapter = new MyAdapter();
				lvNews.setAdapter(adapter);
				break;
			case FAILED:	// ������
				Toast.makeText(MainActivity.this, "��ǰ���������.", 0).show();
				break;
			default:
				break;
			}
		}
    };

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
    }

	private void init() {
		lvNews = (ListView) findViewById(R.id.lv_news);

		// ץȡ��������
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ������ż���
				List<NewInfo> newInfoList = getNewsFromInternet();
				Message msg = new Message();
				if(newInfoList != null) {
					msg.what = SUCCESS;
					msg.obj = newInfoList;
				} else {
					msg.what = FAILED;
				}
				handler.sendMessage(msg);
			}
		}).start();
		
		
	}

	/**
	 * ����������Ϣ
	 */
	private List<NewInfo> getNewsFromInternet() {
		HttpClient client = null;
		try {
			// ����һ���ͻ���
			client = new DefaultHttpClient();
			
			// ����get����
			HttpGet get = new HttpGet("http://192.168.1.254:8080/NetEaseServer/new.xml");
			
			// ִ������
			HttpResponse response = client.execute(get);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			if(statusCode == 200) {
				InputStream is = response.getEntity().getContent();
				List<NewInfo> newInfoList = getNewListFromInputStream(is);
				return newInfoList;
			} else {
				Log.i(TAG, "����ʧ��: " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(client != null) {
				client.getConnectionManager().shutdown();		// �رպ��ͷ���Դ
			}
		}
		return null;
	}
    
	/**
	 * �����н������ż���
	 * @param is
	 * @return
	 */
	private List<NewInfo> getNewListFromInputStream(InputStream is) throws Exception {
		XmlPullParser parser = Xml.newPullParser();	// ����һ��pull������
		parser.setInput(is, "utf-8");	// ָ��������, �ͱ���
		
		int eventType = parser.getEventType();
		
		List<NewInfo> newInfoList = null;
		NewInfo newInfo = null;
		while(eventType != XmlPullParser.END_DOCUMENT) {	// ���û�е���β��, ����ѭ��
			
			String tagName = parser.getName();	// �ڵ�����
			switch (eventType) {
			case XmlPullParser.START_TAG: // <news>
				if("news".equals(tagName)) {
					newInfoList = new ArrayList<NewInfo>();
				} else if("new".equals(tagName)) {
					newInfo = new NewInfo();
				} else if("title".equals(tagName)) {
					newInfo.setTitle(parser.nextText());
				} else if("detail".equals(tagName)) {
					newInfo.setDetail(parser.nextText());
				} else if("comment".equals(tagName)) {
					newInfo.setComment(Integer.valueOf(parser.nextText()));
				} else if("image".equals(tagName)) {
					newInfo.setImageUrl(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:	// </news>
				if("new".equals(tagName)) {
					newInfoList.add(newInfo);
				}
				break;
			default:
				break;
			}
			eventType = parser.next();		// ȡ��һ���¼�����
		}
		return newInfoList;
	}
	
	class MyAdapter extends BaseAdapter {

		/**
		 * �����б���ܳ���
		 */
		@Override
		public int getCount() {
			return newInfoList.size();
		}

		/**
		 * ����һ���б������Ŀ�Ĳ���
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			
			if(convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				view = inflater.inflate(R.layout.listview_item, null);
			} else {
				view = convertView;
			}
			
			// ���¸�ֵ, ����������������ԭ�����ݱ���������
			SmartImageView sivIcon = (SmartImageView) view.findViewById(R.id.siv_listview_item_icon);
			TextView tvTitle = (TextView) view.findViewById(R.id.tv_listview_item_title);
			TextView tvDetail = (TextView) view.findViewById(R.id.tv_listview_item_detail);
			TextView tvComment = (TextView) view.findViewById(R.id.tv_listview_item_comment);
			
			NewInfo newInfo = newInfoList.get(position);
			
			sivIcon.setImageUrl(newInfo.getImageUrl());		// ����ͼƬ
			tvTitle.setText(newInfo.getTitle());
			tvDetail.setText(newInfo.getDetail());
			tvComment.setText(newInfo.getComment() + "����");
			return view;
		}
		

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
