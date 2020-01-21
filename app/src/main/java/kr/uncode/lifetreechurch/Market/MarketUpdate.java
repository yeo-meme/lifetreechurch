package kr.uncode.lifetreechurch.Market;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Patterns;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.lang.ref.WeakReference;
import java.util.regex.Pattern;

import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2020-01-21
 */
public class MarketUpdate extends AsyncTask<Void, Void, String> {

    public static final int MARKET_UPDATE_NOTHING = 0;
    public static final int MARKET_UPDATE_REQUIRED = 1;
    public static final int MARKET_UPDATE_OPTIONAL = 2;

    private WeakReference<Context> contextWeakReference;
    private MarketUpdateTaskCallback taskCallback;


    public MarketUpdate(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }
    public interface MarketUpdateTaskCallback {
        void marketUpdate(int type);
    }

    public void setTaskCallback(MarketUpdateTaskCallback callback) {
        this.taskCallback = callback;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        try {
            String StoreUrl = "https://play.google.com/store/apps/details?id=kr.uncode.lifetreechurch";
            Document doc = Jsoup.connect(StoreUrl).get();
            Elements Version = doc.select(".htlgb");
            for (int i = 0; i < Version.size(); i++) {
                String marketVersion = Version.get(i).text();
                if (Pattern.matches("^[0-9\\\\.]*$", marketVersion)) {
                    return marketVersion;
                }
            }
        } catch (Exception e) {
            MLog.d(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String marketVersion) {
        if (marketVersion != null) {
            try {
                Context context = contextWeakReference.get();
                String deviceVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;

                MLog.i("marketVersion:" + marketVersion);
                MLog.i("deviceVersion:" + deviceVersion);


                String[] mvStringArray = marketVersion.split("\\.");
                String[] dvStringArray;
                if (deviceVersion.contains("-")) {
                    dvStringArray = deviceVersion.split("-")[0].split("\\.");
                } else {
                    dvStringArray = deviceVersion.split("\\.");
                }

                boolean required = false;
                boolean optional = false;

                for (int idx = 0; idx < 3; idx++) {
                    int mIntVer = Integer.parseInt(mvStringArray[idx]);
                    int dIntVer = Integer.parseInt(dvStringArray[idx]);
                    if (idx != 2) {
                        if (!required) required = mIntVer > dIntVer;
                    } else {
                        optional = mIntVer > dIntVer;
                    }
                }


                boolean requiredSum = Integer.parseInt(mvStringArray[0] + mvStringArray[1]) >= Integer.parseInt(dvStringArray[0] + dvStringArray[1]);

                MLog.d("required :" + required);
                MLog.d("optional :" + optional);
                MLog.d("requiredSum :" + requiredSum);

                if (required && requiredSum) {
                    taskCallback.marketUpdate(MARKET_UPDATE_REQUIRED);
                } else {
                    if (optional && requiredSum) {
                        taskCallback.marketUpdate(MARKET_UPDATE_OPTIONAL);
                    } else {
                        taskCallback.marketUpdate(MARKET_UPDATE_NOTHING);
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                MLog.d(e.getMessage());
                taskCallback.marketUpdate(MARKET_UPDATE_NOTHING);
            }
        } else {
            taskCallback.marketUpdate(MARKET_UPDATE_NOTHING);
        }
    }
}
