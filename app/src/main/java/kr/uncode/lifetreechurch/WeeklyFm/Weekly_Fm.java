package kr.uncode.lifetreechurch.WeeklyFm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import kr.uncode.lifetreechurch.Config.JiNuConfig;
import kr.uncode.lifetreechurch.Model.BlogWeekly;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmWeeklyBinding;
import kr.uncode.lifetreechurch.fm_video.VideoFragment;
import kr.uncode.lifetreechurch.utils.MLog;

public class Weekly_Fm extends BaseFragment {
    private static final String IMAGE_URL = "WEEKLY_IMAGE";
    private String weekly1Url;
    private String weekly2Url;
    private JiNuConfig jinuConfig;

    FmWeeklyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_weekly, container, false);
        ClickCheck();
        return binding.getRoot();
    }

    private void ClickCheck() {
        binding.weeklyImage1.setOnClickListener(this::weekly1DetailView);
        binding.weeklyImage2.setOnClickListener(this::weekly2DetailView);
    }


    private void weekly1DetailView(View view) {
        MLog.d();
        Fragment fragment = new WeeklyImage1Fm(); // Fragment 생성
        Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
        bundle.putString(IMAGE_URL, weekly1Url); // key , value
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fmt = fm.beginTransaction();
        fragment.setArguments(bundle);
        fmt.replace(R.id.main_container,fragment).addToBackStack(null).commit();
    }


    private void weekly2DetailView(View view) {
        MLog.d();
        Fragment fragment = new WeeklyImage2Fm(); // Fragment 생성
        Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
        bundle.putString(IMAGE_URL, weekly2Url); // key , value
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fmt = fm.beginTransaction();
        fragment.setArguments(bundle);
        fmt.replace(R.id.main_container,fragment).addToBackStack(null).commit();

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
                        weekly1Url = blogWeekly.imgurl1;
                        weekly2Url = blogWeekly.imgurl2;
                        MLog.d("data get ImageUrl : " + weekly1Url);
                        MLog.d("data get ImageUrl : " + weekly2Url);
                    }
                    showWeekly();
                }
            }
        });
    }

    private void showWeekly() {
        if (weekly1Url != null ) {
            Glide.with(binding.getRoot())
                    .load(weekly1Url)
                    .override(500,300)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.weeklyImage1);
        }

        if (weekly2Url != null) {
            Glide.with(binding.getRoot())
                    .load(weekly2Url)
                    .override(500,300)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.weeklyImage2);

        }

    }
}
