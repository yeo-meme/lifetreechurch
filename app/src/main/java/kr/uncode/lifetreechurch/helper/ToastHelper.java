package kr.uncode.lifetreechurch.helper;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.utils.MLog;

public class ToastHelper {

    private static WeakReference<Context> weakContext;
    private static Toast toast;


    static {
        Context context = BaseApplication.getInstance();
        weakContext = new WeakReference<>(context);
    }


    /**
     * Toast message center gravity
     *
     * @param message  String text
     * @param duration Toast message duration Contants
     */
    private static void showToastCenter(String message, int duration) {
        if (TextUtils.isEmpty(message)) return;
        if (toast != null) toast.cancel();
        toast = Toast.makeText(weakContext.get(), message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * Toast message center gravity
     *
     * @param resId String Resource id
     * @param duration Toast message duration Constants
     */
    private static void showToastCenter(int resId, int duration) {
        String message = null;
        try {
            message = weakContext.get().getString(resId);
        } catch (Exception e) {
            MLog.e(e.getMessage());
        }
        if (!TextUtils.isEmpty(message)) {
            showToastCenter(message, duration);
        }
    }


    /**
     * Toast 짧게 보이기
     *
     * @param resId String Resource id
     */

    public static void showToastShort(int resId) {
        showToastCenter(resId, Toast.LENGTH_SHORT);
    }


    /**
     * Toast 짧게 보이기
     *
     * @param msg String text
     */
    public static void showToastShort(String msg) {
        showToastCenter(msg, Toast.LENGTH_SHORT);
    }


    /**
     * toast 길게 보이기
     *
     * @param resId String Resource id
     */

    public static void showToastLong(int resId) {
        showToastCenter(resId,Toast.LENGTH_LONG);
    }


    /**
     * toast 길게 보이기
     *
     * @param msg String text
     */
    public static void showToastLong(String msg) {
        showToastCenter(msg, Toast.LENGTH_LONG);
    }

    
}
