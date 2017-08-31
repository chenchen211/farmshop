package com.hnxy.farmshop.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/7.
 */

public class SPUtils {

	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;

	private static SPUtils instance;

	private SPUtils(Context context, String name) {
		preferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
	}

	public static SPUtils getInstance(Context context,String name) {
		if (instance == null) {
			instance = new SPUtils(context,name);
		}
		return instance;
	}
	public void put(String key, Object object){
		editor = preferences.edit();
		if ((object instanceof String)){
			editor.putString(key, (String)object);
		} else if ((object instanceof Integer)){
			editor.putInt(key, ((Integer)object).intValue());
		} else if ((object instanceof Boolean)){
			editor.putBoolean(key, ((Boolean)object).booleanValue());
		} else if ((object instanceof Float)){
			editor.putFloat(key, ((Float)object).floatValue());
		} else if ((object instanceof Long)){
			editor.putLong(key, ((Long)object).longValue());
		}else {
			editor.putString(key, object.toString());
		}
		editor.apply();
	}

	public Object get(String key, Object defaultObject){

		if ((defaultObject instanceof String)){
			return preferences.getString(key, (String)defaultObject);
		}if ((defaultObject instanceof Integer)){
			return preferences.getInt(key, ((Integer) defaultObject).intValue());
		}if ((defaultObject instanceof Boolean)){
			return preferences.getBoolean(key, ((Boolean)defaultObject).booleanValue());
		}if ((defaultObject instanceof Float)){
			return preferences.getFloat(key, ((Float)defaultObject).floatValue());
		}if ((defaultObject instanceof Long)){
			return preferences.getLong(key, ((Long)defaultObject).longValue());
		}
		return null;
	}

	public void remove(String key){
		editor.remove(key);
	}

	public void clear(){
		editor.clear();
	}

	public boolean contains(String key){
		return preferences.contains(key);
	}

	public Map<String, ?> getAll(Context context){
		return preferences.getAll();
	}
}
