package com.bojian.gis.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreUtil {
	private Context context;
	private String FileName = "gis";

	public SharePreUtil(Context mContext) {
		this.context = mContext;
	};

	public void saveIntValueToSp(String key, int value) {
		SharedPreferences sp = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public void saveBooleanValueToSp(String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void saveStringValueToSp(String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public boolean containsKey(String key) {
		SharedPreferences sp = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	public void removeSpByKey(String key) {
		SharedPreferences sharePre = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharePre.edit();
		editor.remove(key);
		editor.commit();
	}

	public Integer getIntegerValueByKey(String key) {
		SharedPreferences sharePre = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		return sharePre.getInt(key, -1);
	}

	public boolean getBooleanValueByKey(String key) {
		SharedPreferences sharePre = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		return sharePre.getBoolean(key, false);
	}

	public String getStringValueByKey(String key) {
		SharedPreferences sharePre = context.getSharedPreferences(FileName,
				Context.MODE_PRIVATE);
		return sharePre.getString(key, null)
		;
	}
}
