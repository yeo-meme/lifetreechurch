package kr.uncode.lifetreechurch.fm_happy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

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
    ActivityHappdetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_happdetails);


        Intent intent =getIntent();
        String title = intent.getExtras().getString(HAPPY_TITLE);
        String content = intent.getExtras().getString(HAPPY_CON);

        MLog.d("cccc"+ content);
        binding.text.setText(content);
    }
}
