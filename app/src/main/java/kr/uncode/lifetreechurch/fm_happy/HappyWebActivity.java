package kr.uncode.lifetreechurch.fm_happy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.databinding.HappywebviewBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class HappyWebActivity extends BaseActivity {


    private static final String HAPPY_TITLE = "MEME";
    private static final String HAPPY_CON = "JIN";
    private static final String WEB_VIEW_HAPPY = "PI";
    private Context context;
    HappywebviewBinding binding;
    private WebSettings webSettings;

    private String title = null;
    private String contents = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.happywebview);

        Intent intent = getIntent();
        title = intent.getExtras().getString(HAPPY_TITLE);
        contents = intent.getExtras().getString(HAPPY_CON);


        binding.happyVital.setText(contents);
//        webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webView.setWebChromeClient(new WebChromeClient());

//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                progressON("Loading...");
//            }
//
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                Toast.makeText(getParent(), "로딩중 에러가 발생했습니다.다시 시도하세요", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                progressOFF();
//            }
//
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                webView.loadUrl(url);
//                progressOFF();
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });
//
//        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                MLog.d("key 뒤로가기");
                this.finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
