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

import kr.uncode.lifetreechurch.Config.BlogConfig;
import kr.uncode.lifetreechurch.Config.UnCodeVideoConfig;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmYoutubeBinding;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;


public class VideoFragment extends BaseFragment {

    private BlogConfig blogConfig;
    FmYoutubeBinding binding;


    private static final String MORNING = "MORNING";
    private static final String AFTERNOON = "AFTERNOON";
    private static final String WEDNESDAY = "WEDNESDAY";
    private static final String DAWN = "DAWN";


    private UnCodeVideoConfig unCodeVideoConfig;
    private String newest = null;

    private void videoDate() {
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoList(new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                if (response != null) {
                    for (int i = 0; i < response.data.size(); i++) {
                        UnCodeVideoModel.Data items = response.data.get(i);
                        if (i == 0) {
                            newest = items.videoId;
                            MLog.d("new videoId" + newest);
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
//        videoConfig.videoList(new ResponseCallback<YoutubeResponse>() {
//            @Override
//            public void response(YoutubeResponse response) {
//
//                if (response != null) {
//                    for (int e = 0; e < response.items.size(); e++) {
//                        YoutubeResponse.Items items = response.items.get(e);
//                        if (e == 0) {
//                            newest = items.id.videoId;
//                            MLog.d("newest :" + newest);
//                        }
//                    }
//
//
//                    binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                        @Override
//                        public void onReady(YouTubePlayer youTubePlayer) {
//                            super.onReady(youTubePlayer);
//
//                            youTubePlayer.loadVideo(newest, 0);
//                        }
//                    });
//                }
//
//            }
//        });
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

        Bundle args = getArguments();
        if (args != null) {

            String name = args.getString("MEME");
            MLog.d("args test" + name);
        }

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
        binding.sunMorning.setOnClickListener(this::morningVideoList);
        binding.dawnWorship.setOnClickListener(this::dawnVideoList);
        binding.sunAfternoon.setOnClickListener(this::sunAfternoonVideoList);
        binding.wednesdayWorship.setOnClickListener(this::wednesdayVideoList);
        binding.recentViewWorship.setOnClickListener(this::myWorshipVideo);
        binding.myWorship.setOnClickListener(this::recentView);
    }

    private void wednesdayVideoList(View view) {
        String player_state = "수요";
        replaceFragmentYoutube(new MorningVideoListFragment(),true,player_state);
    }

    private void sunAfternoonVideoList(View view) {
        String player_state = "오후";
        replaceFragmentYoutube(new MorningVideoListFragment(),true,player_state);
    }

    private void dawnVideoList(View view) {
        String player_state = "새벽";
        replaceFragmentYoutube(new MorningVideoListFragment(),true,player_state);
    }

    private void morningVideoList(View view) {
        String player_state = "오전";
        replaceFragmentYoutube(new MorningVideoListFragment(),true,player_state);
    }

    private void recentView(View view) {
        replaceFragment(new MyVideoStorage(), true);

    }

    private void myWorshipVideo(View view) {
        replaceFragment(new MyVideoStorage(), true);

    }


    /**
     * 주일 오전 예배 유튜브
     */
    private void worshipVideoList(View view) {
//        replaceFragment(new MorningVideoListFragment(), true);
        replaceFragmentYoutube(new MorningVideoListFragment(), true,MORNING);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.youtubePlayerView.release();
    }
}
