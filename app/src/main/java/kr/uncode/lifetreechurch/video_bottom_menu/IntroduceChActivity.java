package kr.uncode.lifetreechurch.video_bottom_menu;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.databinding.ActivityIntrochurchBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class IntroduceChActivity extends BaseActivity {

    private WebSettings mws;
    ActivityIntrochurchBinding binding;

    private WebView mwv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introchurch);
        mwv = findViewById(R.id.introChurch);

        mws = mwv.getSettings();
        mws.setJavaScriptEnabled(true);
        mws.setLoadWithOverviewMode(true);


        mwv.setWebChromeClient(new WebChromeClient());
        mwv.setWebViewClient(new WebViewClient());
        mwv.loadUrl("https://blog.naver.com/PostList.nhn?blogId=ttlmc&from=postList&categoryNo=11");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == event.KEYCODE_BACK) && mwv.canGoBack()) {
            mwv.goBack();
            return true;
        }return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }
}
