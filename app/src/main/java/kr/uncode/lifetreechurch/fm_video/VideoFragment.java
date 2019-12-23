package kr.uncode.lifetreechurch.fm_video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;


import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import kr.uncode.lifetreechurch.Config.VideoConfig;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmYoutubeBinding;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;
import kr.uncode.lifetreechurch.video_bottom_menu.RecentMyVideo;


public class VideoFragment extends BaseFragment {

    private VideoConfig videoConfig;
    FmYoutubeBinding binding;

    private String newest = null;

    private void videoDate() {
        videoConfig = new VideoConfig();
        videoConfig.videoList(new ResponseCallback<YoutubeResponse>() {
            @Override
            public void response(YoutubeResponse response) {

                if (response != null) {
                    for (int e = 0; e < response.items.size(); e++) {
                        YoutubeResponse.Items items = response.items.get(e);
                        if (e == 0) {
                            newest = items.id.videoId;
                            MLog.d("newest :" + newest);
                        }
                    }


                    binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(YouTubePlayer youTubePlayer) {
                            super.onReady(youTubePlayer);

                            youTubePlayer.loadVideo(newest, 0);
                        }
                    });
                }

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_youtube, container, false);

//        View view = inflater.inflate(R.layout.fm_youtube,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuListener(view);

        videoDate();
//        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                String videoId = "f3aI5OUbHKE";
//                youTubePlayer.loadVideo(videoId,0);
//            }
//        });
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
        replaceFragment(new MyVideoStorage(), true);

    }

    private void myWorshipVideo(View view) {
        replaceFragment(new MyVideoStorage(), true);

    }

    private void worshipVideoList(View view) {
        replaceFragment(new VideoListFragment(), true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.youtubePlayerView.release();
    }
}
