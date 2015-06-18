package com.github.booknara.appcategory.util;

import android.content.Context;
import android.widget.Toast;

import com.github.booknara.appcategory.BuildConfig;

public class ToastFactory {
	static String CNAME = ToastFactory.class.getSimpleName();

	/**
	 * show short time
	 * 
	 * @param c
	 * @param resId Id
	 */
	public static void showShort(Context c, int resId) {
		if (c == null)
			return;

		try {
			Toast.makeText(c, c.getString(resId), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			if (BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
		}
	}

	/**
	 * show short time
	 * 
	 * @param c
	 * @param message
	 */
	public static void showShort(Context c, String message) {
		if (c == null || StringUtil.isEmpty(message))
			return;

		try {
			Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			if (BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
		}
	}

	/**
	 * show long time
	 * 
	 * @param c
	 * @param resId Id
	 */
	public static void showLong(Context c, int resId) {
		if (c == null)
			return;

		try {
			Toast.makeText(c, c.getString(resId), Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			if (BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
		}
	}
	
	/**
	 * show long time
	 * 
	 * @param c
	 * @param message
	 */
	public static void showLong(Context c, String message) {
		if (c == null || StringUtil.isEmpty(message))
			return;

		try {
			Toast.makeText(c, message, Toast.LENGTH_LONG).show();
		} catch (Exception e) {
			if (BuildConfig.DEBUG)
				Logger.e(CNAME, ExceptionUtil.exception(e));
		}
	}

}
