package com.itheim28.submitdata.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.util.Log;

public class NetUtils {

	private static final String TAG = "NetUtils";
	
	/**
	 * ʹ��post�ķ�ʽ��¼
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String loginOfPost(String userName, String password) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL("http://10.0.2.2:8080/ServerItheima28/servlet/LoginServlet");
			
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000); // ���ӵĳ�ʱʱ��
			conn.setReadTimeout(5000); // �����ݵĳ�ʱʱ��
			conn.setDoOutput(true);	// �������ô˷���, �������
//			conn.setRequestProperty("Content-Length", 234);		// ��������ͷ��Ϣ, �������ö��
			
			// post����Ĳ���
			String data = "username=" + userName + "&password=" + password;
			
			// ���һ�������, �����������д����, Ĭ�������, ϵͳ��������������������
			OutputStream out = conn.getOutputStream();	
			out.write(data.getBytes());
			out.flush();
			out.close();
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 200) {
				InputStream is = conn.getInputStream();
				String state = getStringFromInputStream(is);
				return state;
			} else {
				Log.i(TAG, "����ʧ��: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();
			}
		}
		return null;
	}

	/**
	 * ʹ��get�ķ�ʽ��¼
	 * @param userName
	 * @param password
	 * @return ��¼��״̬
	 */
	public static String loginOfGet(String userName, String password) {
		HttpURLConnection conn = null;
		try {
			String data = "username=" + URLEncoder.encode(userName) + "&password=" + URLEncoder.encode(password);
			URL url = new URL("http://10.0.2.2:8080/ServerItheima28/servlet/LoginServlet?" + data);
			conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");		// get����post�����ȫ��д
			conn.setConnectTimeout(10000); // ���ӵĳ�ʱʱ��
			conn.setReadTimeout(5000); // �����ݵĳ�ʱʱ��
			
			int responseCode = conn.getResponseCode();
			if(responseCode == 200) {
				InputStream is = conn.getInputStream();
				String state = getStringFromInputStream(is);
				return state;
			} else {
				Log.i(TAG, "����ʧ��: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				conn.disconnect();		// �ر�����
			}
		}
		return null;
	}
	
	/**
	 * ����������һ���ַ�����Ϣ
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	private static String getStringFromInputStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		
		while((len = is.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		is.close();
		
		String html = baos.toString();	// �����е�����ת�����ַ���, ���õı�����: utf-8
		
//		String html = new String(baos.toByteArray(), "GBK");
		
		baos.close();
		return html;
	}
}
