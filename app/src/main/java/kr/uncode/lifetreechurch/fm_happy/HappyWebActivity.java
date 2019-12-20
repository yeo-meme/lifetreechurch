package kr.uncode.lifetreechurch.fm_happy;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class HappyWebActivity extends BaseActivity {

    private static final String WEB_VIEW_HAPPY ="PI";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.happywebview);

        Intent intent = getIntent();
        String url = intent.getExtras().getString(WEB_VIEW_HAPPY);

        WebView webView = findViewById(R.id.happyWeb);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
