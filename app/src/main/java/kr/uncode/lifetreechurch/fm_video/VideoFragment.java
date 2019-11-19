package kr.uncode.lifetreechurch.fm_video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmYoutubeBinding;
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;
import kr.uncode.lifetreechurch.video_bottom_menu.RecentMyVideo;


public class VideoFragment extends BaseFragment {

    FmYoutubeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_youtube, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuListener(view);
    }

    private void menuListener(View v) {
        binding.sunMorning.setOnClickListener(this::worshipVideoList);
        binding.dawnWorship.setOnClickListener(this::worshipVideoList);
        binding.sunAfternoon.setOnClickListener(this::worshipVideoList);
        binding.wednesdayWorship.setOnClickListener(this::worshipVideoList);
        binding.recentViewWorship.setOnClickListener(this::myWorshipVideo);
        binding.myWorship.setOnClickListener(this::recentView);
    }

    private void recentView(View view) {
        replaceFragment(new RecentMyVideo(),true);

    }

    private void myWorshipVideo(View view) {
        replaceFragment(new MyVideoStorage(), true);

    }

    private void worshipVideoList(View view) {
        replaceFragment(new VideoListFragment(),true);
    }
}
