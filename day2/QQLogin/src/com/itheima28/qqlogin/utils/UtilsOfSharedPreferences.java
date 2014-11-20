package com.itheima28.qqlogin.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class UtilsOfSharedPreferences {

	/**
	 * �����û���Ϣ��sd��
	 * @param number
	 * @param password
	 * @return true �ɹ�
	 */
	public static boolean saveUserInfo(Context context, String number, String password) {
		
		try {
			// /data/data/����/shared_prefs/itheima28
			SharedPreferences sp = context.getSharedPreferences("itheima28", Context.MODE_PRIVATE);
			
			// ���һ���༭����
			Editor edit = sp.edit();
			
			// ������
			edit.putString("number", number);
			edit.putString("password", password);
			
			// �ύ, ���������洢������.
			edit.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	/**
	 * ��sd����ȡ�û���Ϣ
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
