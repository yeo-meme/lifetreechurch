package kr.uncode.lifetreechurch.WeeklyFm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import kr.uncode.lifetreechurch.Model.BlogWeekly;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmWeekly2Binding;
import kr.uncode.lifetreechurch.utils.MLog;

public class WeeklyImage2Fm extends BaseFragment {

    private static final String IMAGE_URL = "WEEKLY_IMAGE";

    private String image_url;

    FmWeekly2Binding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_weekly2, container, false);
//        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            image_url = getArguments().getString(IMAGE_URL); // 전달한 key 값
            MLog.d(image_url);
        }
        getWeeklyImg();
    }

    private void getWeeklyImg() {

        if (image_url != null) {
            Glide.with(binding.getRoot())
                    .load(image_url)
                    .override(500,300)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.weeklyImage2);

        }


    }
}
