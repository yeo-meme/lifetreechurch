package kr.uncode.lifetreechurch.IntroduceBottomActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.databinding.ActivityIntrochurchBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class IntroduceChActivity extends BaseActivity {

    private WebSettings mws;
    ActivityIntrochurchBinding binding;

    private ProgressBar progressBar;
    private static final String URL = "https://blog.naver.com/PostList.nhn?blogId=ttlmc&from=postList&categoryNo=11";
    private WebViewClientClass webViewClientClass;
    private WebView mwv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introchurch);

        mwv = findViewById(R.id.introChurch);
        progressBar = findViewById(R.id.progressBar1);
        webViewClientClass = new WebViewClientClass();
//        webViewClientClass.onPageFinished(mwv,URL);
//        webViewClientClass.onPageFinished(mwv,URL);
        mws = mwv.getSettings();
        mws.setJavaScriptEnabled(true);
        mws.setLoadWithOverviewMode(true);


        mwv.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view,int progress) {
                if (progress <100) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                } else if (progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
                progressBar.setProgress(progress);
            }
        });
        mwv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                MLog.d("webView start");
                progressON("Loading...");
//                progressDialog.show();
//                BaseApplication.getInstance().progressON(getParent(), "Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                MLog.d("webView finish");

                progressOFF();
//                BaseApplication.getInstance().progressOFF();
            }

        });

        mwv.loadUrl(URL);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == event.KEYCODE_BACK) && mwv.canGoBack()) {
            mwv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }
}
