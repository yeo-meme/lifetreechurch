package kr.uncode.lifetreechurch.WeeklyFm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.uncode.lifetreechurch.Config.JiNuConfig;
import kr.uncode.lifetreechurch.Model.BlogWeekly;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.databinding.FmWeeklyBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class Weekly_Fm extends Fragment {

    private String temImageUrl;
    private JiNuConfig jinuConfig;

    FmWeeklyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_weekly, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getJinNu();
    }

    private void getJinNu() {
        jinuConfig = new JiNuConfig();
        jinuConfig.jiNuList(new ResponseCallback<BlogWeekly>() {
            @Override
            public void response(BlogWeekly response) {
                if (response != null) {
                    MLog.d("jinuAPI ok!!");
                    for (int i = 0; i < response.data.size(); i++) {
                        BlogWeekly.Data blogWeekly = response.data.get(i);
                        temImageUrl = blogWeekly.imgurl1;
                        MLog.d("data get ImageUrl : " + temImageUrl);
                    }
                    showWeekly();
                }
            }
        });
    }

    private void showWeekly() {
        if (temImageUrl != null) {
            Glide.with(binding.getRoot())
                    .load(temImageUrl)
                    .override(500,300)
//                        .apply(new RequestOptions().override(displayMetrics.widthPixels - 36, 200))
                    .into(binding.weeklyImage1);
        }

    }
}
