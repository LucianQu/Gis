package com.bojian.gis.debug;

import android.os.Debug;
import android.util.Log;

public final class L {
	private static final String TAG = "L";
	/**Log日志输出开关，打包时请将字段置为false**/
	public static final boolean ON_OFF = false;

	public static void info(String tag, String msg) {
		if (ON_OFF) {
			Log.i(tag, msg);
		}
	}

	public static void verbose(String tag, String msg) {
		if (ON_OFF) {
			Log.v(tag, msg);
		}
	}

	public static void debug(String tag, String msg) {
		if (ON_OFF) {
			Log.d(tag, msg);
		}
	}

	public static void d(String msg) {
		if (ON_OFF) {
			Log.d(TAG, msg);
		}
	}

	public static void warning(String tag, String msg) {
		if (ON_OFF) {
			Log.w(tag, msg);
		}
	}

	public static void error(String tag, String msg) {
		if (ON_OFF) {
			Log.e(tag, msg);
		}
	}

	public static void i(String msg) {
		if (ON_OFF) {
			Log.i(TAG, msg);
		}
	}

	public static void e(String tag,String msg) {
		if (ON_OFF) {
			Log.e(tag, msg);
		}
	}

	public static void startTrace() {
		Debug.startMethodTracing();
	}

	public static void stopTrace() {
		Debug.stopMethodTracing();
	}
}
