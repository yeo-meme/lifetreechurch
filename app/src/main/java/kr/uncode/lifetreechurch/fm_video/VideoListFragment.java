package kr.uncode.lifetreechurch.fm_video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

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
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;

public class VideoListFragment extends BaseFragment {
    private YoutubeRecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //    private YouTubePlayerView youTubePlayerViewLayout;
    private FmVideolistBinding binding;

    private VideoConfig videoConfig;

    private MyVideoStorage myVideoStorage;

    //    public YouTubePlayer youTubePlayer;
    private static String YOUTUBE = "YOUTUBE";
    public Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//데이터바인딩 유튜트 라이브러리 적용 어려워서 일단 기본틀로 가려고 주석
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

        myVideoStorage = new MyVideoStorage();
        activity = getActivity();
        if (activity != null && activity instanceof MainActivity)
            getVideoId();


        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {

                YoutubeResponse.Items items = (YoutubeResponse.Items) aa.get(position);
                String playId = items.id.videoId;





                if (playId != null) {
                    Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
                    intent.putExtra(YOUTUBE, playId);

//                    myVideoStorage.listener(playId);
                    startActivity(intent);

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("data",playId);
                    editor.commit();

//                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                    String data = sharedPreferences.getString("data","");
                    MLog.d("preferencec :"+data);
                }

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

    binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
        @Override
        public void onReady(YouTubePlayer youTubePlayer) {
            super.onReady(youTubePlayer);
            youTubePlayer.loadVideo(secondVideo,0);
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
