package com.xiaozhi.frame.tool.SharedPrefernces;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 构建编辑器
 */
public class SharedPreferencesUtil {

	private SharedPreferences sharedPreferences;

	private Editor editor;

	public SharedPreferencesUtil(Context context, String fileName) {
		getUserSharedPreferenceFile(context, fileName);
		getEditor();
	}

	/**
	 * 构建编辑器
     */
	public SharedPreferences.Editor getEditor() {
		editor = sharedPreferences.edit();
		return editor;
	}

	/**
	 * 关闭编辑器 注意如果调用setxx方法写入数据后须调用此方法
	 */
	public void editorCommit() {
		if (editor != null) {
			editor.commit();
		}
	}

	/**
	 * 获取用户信息缓存文件
     */
	public SharedPreferences getUserSharedPreferenceFile(Context context,
			String fileName) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(fileName, 0);
		}
		return sharedPreferences;
	}

	/**
	 * 缓存布尔值
     */
	public void setBoolean(String key, boolean value) {
		editor.putBoolean(key, value);
	}

	/**
	 * 获取数据 context 上下文 fiedName
     */
	public boolean getBoolean(String key) {
		return sharedPreferences.getBoolean(key, false);
	}

	/**
	 * 获取数据 context 上下文 fiedName 字段名 b 缺省值
     */
	public boolean getBoolean(String key, boolean value) {
		return sharedPreferences.getBoolean(key, value);
	}

	/**
	 * 缓存字符串
     */
	public void setString(String key, String value) {
		editor.putString(key, value);
	}

	/**
	 * 获取字符串数据  context 上下文 key 字段名 value 缺省值
     */
	public String getString(String key, String value) {
		return sharedPreferences.getString(key, value);
	}

	/**
	 * 获取字符串数据  context 上下文 key 字段名
     */
	public String getString(String key) {
		return sharedPreferences.getString(key, null);
	}

	/**
	 * 缓存整形数字
 	 */
	public void setInt(String key, int value) {
		editor.putInt(key, value);
	}

	/**
	 * 获取整形数字  context 上下文 key 字段名 value 缺省值
     */
	public int getInt(String key, int value) {
		return sharedPreferences.getInt(key, value);
	}

	/**
	 * 获取整形数字  context 上下文 key 字段名
     */
	public int getInt(String key) {
		return sharedPreferences.getInt(key, -1);
	}

}