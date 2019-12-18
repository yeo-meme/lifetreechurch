package kr.uncode.lifetreechurch.fm_video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import java.util.ArrayList;
import java.util.List;

import kr.co.prnd.YouTubePlayerView;
import kr.uncode.lifetreechurch.Config.VideoConfig;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.RecyclerViewDecoration;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.YoutubeRecyclerAdapter;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmVideolistBinding;
import kr.uncode.lifetreechurch.lt_main.MainActivity;
import kr.uncode.lifetreechurch.utils.MLog;

public class VideoListFragment extends BaseFragment {
    private YoutubeRecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //    private YouTubePlayerView youTubePlayerViewLayout;
    private FmVideolistBinding binding;

    private VideoConfig videoConfig;

    //    public YouTubePlayer youTubePlayer;
    private static String YOUTUBE = "YOUTUBE";
    public Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//데이터바인딩 유튜트 라이브러리 적용 어려워서 일단 기본틀로 가려고 주석
        BaseFragment.YoutubeFm.newInstance();
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_videolist, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        youTubePlayerViewLayout.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                String videoId = "f3aI5OUbHKE";
//                youTubePlayer.loadVideo(videoId, 0);
//            }
//        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerAdapter = new YoutubeRecyclerAdapter();

        activity = getActivity();
        if (activity != null && activity instanceof MainActivity)
            getVideoId();


        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {

                YoutubeResponse.Items items = (YoutubeResponse.Items) aa.get(position);
                String playId = items.id.videoId;

                Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
                intent.putExtra(YOUTUBE, playId);
                startActivity(intent);
            }
        });
//        setYoutubeData();


    }

    private void getVideoId() {
        videoConfig = new VideoConfig();
        videoConfig.videoList(new ResponseCallback<YoutubeResponse>() {
            @Override
            public void response(YoutubeResponse response) {
                if (response != null) {
                    MLog.d("youtubeModel Ok!");
                    mRecyclerAdapter.setItems(response.items);

                    for (int a = 0; a < response.items.size(); a++) {
                        if (a == 1) {
                            String secondVideo = response.items.get(a).id.videoId;
                            MLog.d("second video:" + secondVideo);
                            secondVideoRun(secondVideo);
                        }
                    }
                    setYoutubeData();
                }
            }
        });
    }

    private void secondVideoRun(String secondVideo) {
        binding.youtubePlayerView.play(secondVideo, new YouTubePlayerView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                MLog.d("youtube success");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }

    private void setYoutubeData() {
        binding.recyclerViewFeed.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewFeed.setLayoutManager(layoutManager);
        binding.recyclerViewFeed.addItemDecoration(new RecyclerViewDecoration(2));
        binding.recyclerViewFeed.setAdapter(mRecyclerAdapter);
    }


}
