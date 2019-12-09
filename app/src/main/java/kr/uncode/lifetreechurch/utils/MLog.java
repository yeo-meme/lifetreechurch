package kr.uncode.lifetreechurch.utils;

import android.app.Activity;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;

import kr.uncode.lifetreechurch.BuildConfig;

/**
 * Created by yeomeme@gmail.com on 2019-11-18
 */
public class MLog {
    private static boolean DEBUG = BuildConfig.DEBUG;
    private static final String TAG = "meme";


    static {

    }


    /**
     * 디버그 모드인지 확인
     *
     * @return debug mode : true <br>
     * release mode : false
     */
    public static boolean isDebugMode() {
        return DEBUG;
    }

    /**
     * Log Level Error
     */

    public static void e() {
        if (DEBUG) Log.e(TAG, buildLogMsg(""));
    }


    /**
     * Log Level Error
     *
     * @param message err message
     */
    public static void e(String message) {
        if (DEBUG)
            Log.e(TAG, buildLogMsg(message));
    }

    /**
     * Log Level Information
     */
    public static void i() {
        if (DEBUG) Log.i(TAG, buildLogMsg(""));
    }

    /**
     * Log Level Debug
     *
     * @param message debug message
     */
    public static void i(String message) {
        if (DEBUG) Log.i(TAG, buildLogMsg(message));
    }


    /**
     * Log Level Debug
     */
    public static void d() {
        if (DEBUG) Log.d(TAG, buildLogMsg(""));
    }

    /**
     * Log Level Debug
     * @param message debug message
     */
    public static void d(String message) {
        if (DEBUG) Log.d(TAG,buildLogMsg(message));
    }

    private static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append(" ");
        sb.append(ste.getMethodName());
        sb.append("(): ");
        sb.append(message);
        return sb.toString();
    }


}
