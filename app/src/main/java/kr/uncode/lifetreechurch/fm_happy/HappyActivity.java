package kr.uncode.lifetreechurch.fm_happy;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.databinding.ActivityHappdetailsBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public class HappyActivity extends BaseActivity {

    ActivityHappdetailsBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_happdetails);
    }
}
