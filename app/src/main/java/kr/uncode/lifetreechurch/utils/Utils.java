package kr.uncode.lifetreechurch.utils;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by yeomeme@gmail.com on 2019-11-18
 */
public class Utils {

    /**
     * 연속 클릭 중복방지 <br>
     * 해당 하는 뷰 Enable 설정을 false로 지정하고, 1초후 해제
     */
    public static void doubleClickDeny(View v) {
        v.setEnabled(false);
        v.postDelayed(() -> v.setEnabled(true), TimeUnit.SECONDS.toMillis(1));
    }

    /**
     * 소트 키보드 숨김/보이임
     */

    public static void hideKeyboard(View view, boolean hide) {
        if (view == null) return;
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (hide) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            else imm.showSoftInput(view, 0);
        }
    }

    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isWifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        MLog.d("wifi" + isWifi);
        MLog.d("wifi network" + activeNetwork.isConnectedOrConnecting());
        return isWifi && activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    private static void notNetworked() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                notConnected();

            }
        },1000);
    }
}
