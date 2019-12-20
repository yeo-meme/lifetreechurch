package kr.uncode.lifetreechurch.IntroduceBottomActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.databinding.ActivityIntroduceBinding;
import kr.uncode.lifetreechurch.utils.CircleIndicator;

/**
 * Created by jinu@timehub.kr on 2019-12-20
 */
public class IntroduceActivity extends BaseActivity {

    ActivityIntroduceBinding binding;

    private WebSettings mWebSettings;
    private WebView mWebView;
    private Adapter adapter;
    private ViewPager viewPager;
    private int[] images = {R.drawable.intro, R.drawable.introt};

    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_introduce);

        viewPager = findViewById(R.id.image_into);
        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(mOnpageChangeListener);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager,true);
    }
//
//    private void initIndicator() {
//
//        circleIndicator.setItemMargin(15);
//        circleIndicator.setAnimDuration(300);
//        circleIndicator.createDotPanel(images.length, R.drawable.indicator_non, R.drawable.indicator_on);
//    }
//
//    private ViewPager.OnPageChangeListener mOnpageChangeListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////            circleIndicator.selectDot(position);
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
}
