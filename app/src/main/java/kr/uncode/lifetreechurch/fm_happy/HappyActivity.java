package kr.uncode.lifetreechurch.fm_happy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.databinding.ActivityHappdetailsBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public class HappyActivity extends BaseActivity {


    private static final String HAPPY_TITLE = "MEME";
    private static final String HAPPY_CON = "JIN";
    private static final String HAPPY_URL ="JINMEME";

    //웹뷰 키
    private static final String WEB_VIEW_HAPPY ="PI";
    private String url = null;

    ActivityHappdetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_happdetails);


        Intent intent =getIntent();
        String title = intent.getExtras().getString(HAPPY_TITLE);
        String content = intent.getExtras().getString(HAPPY_CON);
        url = intent.getExtras().getString(HAPPY_URL);

        MLog.d("cccc"+ content);
        binding.text.setText(content);

        binding.urlRink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (url != null) {
                    onButton(url);
                }
            }
        });

    }

    private void onButton(String url) {
        Intent myIntent = new Intent(this, HappyWebActivity.class);
        myIntent.putExtra(WEB_VIEW_HAPPY,url);
        MLog.d("happy url:"+url);
        startActivity(myIntent);
    }
}
