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
import android.os.Environment;
import android.text.TextUtils;

public class UtilsOfSDCard {

	/**
	 * �����û���Ϣ��sd��
	 * @param number
	 * @param password
	 * @return true �ɹ�
	 */
	public static boolean saveUserInfo(Context context, String number, String password) {
		
		try {
			// �жϵ�ǰ���ֻ��Ƿ���sd��
			String state = Environment.getExternalStorageState();
			
			if(!Environment.MEDIA_MOUNTED.equals(state)) {
				// �Ѿ�������sd��
				return false;
			}
			
			File sdCardFile = Environment.getExternalStorageDirectory();
			
			File file = new File(sdCardFile, "itheima28.txt");
			
			FileOutputStream fos = new FileOutputStream(file);
			
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
	 * ��sd����ȡ�û���Ϣ
	 * @return
	 */
	public static Map<String, String> getUserInfo(Context context) {
		
		try {
			// �жϵ�ǰ���ֻ��Ƿ���sd��
			String state = Environment.getExternalStorageState();
			
			if(!Environment.MEDIA_MOUNTED.equals(state)) {
				// �Ѿ�������sd��
				return null;
			}
			
			File sdCardFile = Environment.getExternalStorageDirectory();
			
			File file = new File(sdCardFile, "itheima28.txt");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			String text = br.readLine();
			
			br.close();
			
			if(!TextUtils.isEmpty(text)) {
				Map<String, String> userInfoMap = new HashMap<String, String>();
				String[] split = text.split("##");
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
