package kr.uncode.lifetreechurch.utils;

import android.content.SharedPreferences;

import java.util.Calendar;

public class MPref {

    private static final String APP_SHARED_PREFS = "APP_SHARED_PREFS";

    private static SharedPreferences.Editor prefsEditor;
    private static SharedPreferences appSharedPreefs;

    public static final String DEVELOPE_KEY = "AIzaSyD20axF8pwlVcco5B0WMa_LTZM9Cq5VcZY";
    public static final String SERVER_URL = "https://www.googleapis.com";
    public static final String BLOG_SERVER_URL = "https://api.uncode.kr:6000/ttlmc";

    private static final String MARKET_UPDATE_DIALOG_CHECK_IN_TIME = "MARKET_UPDATE_DIALOG_CHECK_IN_TIME ";


    public static void setMarketUpdateDialogCheckInTime(long checkInTime) {
        MLog.d("save checkInTime :" + checkInTime);
        prefsEditor.putLong(MARKET_UPDATE_DIALOG_CHECK_IN_TIME, checkInTime);
        prefsEditor.commit();
    }


    public static boolean isMarketUpdateDialogShow() {

        boolean result = false;

        long checkInTimeMillis = appSharedPreefs.getLong(MARKET_UPDATE_DIALOG_CHECK_IN_TIME, 0);
        long currentTimeMillis = Calendar.getInstance().getTimeInMillis();
        long diffSec = (currentTimeMillis - checkInTimeMillis) / 1000;
        long diffDays = diffSec / (24 * 60 * 60);

        if (checkInTimeMillis == 0 || diffDays > 7) {
            result = true;
        }

        MLog.d("currentTimeMillis " + currentTimeMillis);
        MLog.d("checkInTimeMillis " + checkInTimeMillis + ", diffDays" + diffDays + "," + result);

        return result;
    }

}
