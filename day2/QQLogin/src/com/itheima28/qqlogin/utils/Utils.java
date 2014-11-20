package com.itheima28.qqlogin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.text.TextUtils;

public class Utils {

	/**
	 * 保存用户信息
	 * @param number
	 * @param password
	 * @return true 成功
	 */
	public static boolean saveUserInfo(String number, String password) {
		
		try {
			String path = "/data/data/com.itheima28.qqlogin/itheima28.txt";
			
			FileOutputStream fos = new FileOutputStream(path);
			
			// 307966990##123123
			String data = number + "##" + password;
			
			fos.write(data.getBytes());
			
			fos.flush();
			
			fos.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 保存用户信息
	 * @param number
	 * @param password
	 * @return true 成功
	 */
	public static boolean saveUserInfo(Context context, String number, String password) {
		
		try {
//			String path = "/data/data/com.itheima28.qqlogin/itheima28.txt";
			
//			File filesDir = context.getFilesDir();
			File filesDir = context.getCacheDir();
			
			File f = new File(filesDir, "itheima28.txt");
			
			FileOutputStream fos = new FileOutputStream(f);
			
			// 307966990##123123
			String data = number + "##" + password;
			
			fos.write(data.getBytes());
			
			fos.flush();
			
			fos.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 返回用户信息
	 * @return
	 */
	public static Map<String, String> getUserInfo() {

		try {
			String path = "/data/data/com.itheima28.qqlogin/itheima28.txt";
			
			FileInputStream fis = new FileInputStream(path);
			
			// 字符流对象
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			// 307966990##123123
			String text = reader.readLine();
			
			if(!TextUtils.isEmpty(text)) {
				String[] split = text.split("##");
			
				Map<String, String> userInfoMap = new HashMap<String, String>();
				userInfoMap.put("number", split[0]);
				userInfoMap.put("password", split[1]);
				return userInfoMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 返回用户信息
	 * @return
	 */
	public static Map<String, String> getUserInfo(Context context) {

		try {
//			String path = "/data/data/com.itheima28.qqlogin/itheima28.txt";
			
//			File filesDir = context.getFilesDir();
			File filesDir = context.getCacheDir();
			
			File f = new File(filesDir, "itheima28.txt");
			
			FileInputStream fis = new FileInputStream(f);
			
			// 字符流对象
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

			// 307966990##123123
			String text = reader.readLine();
			
			if(!TextUtils.isEmpty(text)) {
				String[] split = text.split("##");
			
				Map<String, String> userInfoMap = new HashMap<String, String>();
				userInfoMap.put("number", split[0]);
				userInfoMap.put("password", split[1]);
				return userInfoMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
