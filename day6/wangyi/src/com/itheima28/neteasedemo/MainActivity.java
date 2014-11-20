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
    	 * 接收消息
    	 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SUCCESS:		// 访问成功, 有数据
				// 给Listview列表绑定数据
				
				newInfoList = (List<NewInfo>) msg.obj;

				MyAdapter adapter = new MyAdapter();
				lvNews.setAdapter(adapter);
				break;
			case FAILED:	// 无数据
				Toast.makeText(MainActivity.this, "当前网络崩溃了.", 0).show();
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

		// 抓取新闻数据
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 获得新闻集合
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
	 * 返回新闻信息
	 */
	private List<NewInfo> getNewsFromInternet() {
		HttpClient client = null;
		try {
			// 定义一个客户端
			client = new DefaultHttpClient();
			
			// 定义get方法
			HttpGet get = new HttpGet("http://192.168.1.254:8080/NetEaseServer/new.xml");
			
			// 执行请求
			HttpResponse response = client.execute(get);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			if(statusCode == 200) {
				InputStream is = response.getEntity().getContent();
				List<NewInfo> newInfoList = getNewListFromInputStream(is);
				return newInfoList;
			} else {
				Log.i(TAG, "访问失败: " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(client != null) {
				client.getConnectionManager().shutdown();		// 关闭和释放资源
			}
		}
		return null;
	}
    
	/**
	 * 从流中解析新闻集合
	 * @param is
	 * @return
	 */
	private List<NewInfo> getNewListFromInputStream(InputStream is) throws Exception {
		XmlPullParser parser = Xml.newPullParser();	// 创建一个pull解析器
		parser.setInput(is, "utf-8");	// 指定解析流, 和编码
		
		int eventType = parser.getEventType();
		
		List<NewInfo> newInfoList = null;
		NewInfo newInfo = null;
		while(eventType != XmlPullParser.END_DOCUMENT) {	// 如果没有到结尾处, 继续循环
			
			String tagName = parser.getName();	// 节点名称
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
			eventType = parser.next();		// 取下一个事件类型
		}
		return newInfoList;
	}
	
	class MyAdapter extends BaseAdapter {

		/**
		 * 返回列表的总长度
		 */
		@Override
		public int getCount() {
			return newInfoList.size();
		}

		/**
		 * 返回一个列表的子条目的布局
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
			
			// 重新赋值, 不会产生缓存对象中原有数据保留的现象
			SmartImageView sivIcon = (SmartImageView) view.findViewById(R.id.siv_listview_item_icon);
			TextView tvTitle = (TextView) view.findViewById(R.id.tv_listview_item_title);
			TextView tvDetail = (TextView) view.findViewById(R.id.tv_listview_item_detail);
			TextView tvComment = (TextView) view.findViewById(R.id.tv_listview_item_comment);
			
			NewInfo newInfo = newInfoList.get(position);
			
			sivIcon.setImageUrl(newInfo.getImageUrl());		// 设置图片
			tvTitle.setText(newInfo.getTitle());
			tvDetail.setText(newInfo.getDetail());
			tvComment.setText(newInfo.getComment() + "跟帖");
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
