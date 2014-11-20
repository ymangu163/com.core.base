package com.itheima28.qqlogin.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class UtilsOfSharedPreferences {

	/**
	 * 保存用户信息到sd卡
	 * @param number
	 * @param password
	 * @return true 成功
	 */
	public static boolean saveUserInfo(Context context, String number, String password) {
		
		try {
			// /data/data/包名/shared_prefs/itheima28
			SharedPreferences sp = context.getSharedPreferences("itheima28", Context.MODE_PRIVATE);
			
			// 获得一个编辑对象
			Editor edit = sp.edit();
			
			// 存数据
			edit.putString("number", number);
			edit.putString("password", password);
			
			// 提交, 数据真正存储起来了.
			edit.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * 到sd卡获取用户信息
	 * @return
	 */
	public static Map<String, String> getUserInfo(Context context) {
		
		SharedPreferences sp = context.getSharedPreferences("itheima28", Context.MODE_PRIVATE);
		
		String number = sp.getString("number", null);
		String password = sp.getString("password", null);
		
		if(!TextUtils.isEmpty(number) && !TextUtils.isEmpty(password)) {
			Map<String, String> userInfoMap = new HashMap<String, String>();
			userInfoMap.put("number", number);
			userInfoMap.put("password", password);
			return userInfoMap;
		}
		return null;
	}
}
