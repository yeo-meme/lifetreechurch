package kr.uncode.lifetreechurch.fm_video;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.YoutubeRecyclerAdapter;
import kr.uncode.lifetreechurch.YoutubeVideoModel;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmVideolistBinding;
import kr.uncode.lifetreechurch.lt_main.MainActivity;

public class VideoListFragment extends BaseFragment {
    private YoutubeRecyclerAdapter mSettingsListAdapter;
    YoutubeRecyclerAdapter mRecyclerAdapter;
//    @BindView(R.id.recyclerViewFeed)
     private RecyclerView recyclerViewFeed;
    private RecyclerView.LayoutManager mLayoutManager;
    private YouTubePlayerView youTubePlayerViewLayout;
    FmVideolistBinding binding;



    public YouTubePlayer youTubePlayer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//데이터바인딩 유튜트 라이브러리 적용 어려워서 일단 기본틀로 가려고 주석
//        binding = DataBindingUtil.inflate(inflater, R.layout.fm_videolist, container, false);

        View view = inflater.inflate(R.layout.fm_videolist, container, false);
        youTubePlayerViewLayout = view.findViewById(R.id.youtube_player_view);
        recyclerViewFeed = view.findViewById(R.id.recyclerViewFeed);

        recyclerViewFeed.setLayoutManager(mLayoutManager);
        recyclerViewFeed.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFeed.setAdapter(mRecyclerAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        youTubePlayerViewLayout.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                String videoId = "f3aI5OUbHKE";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity)
            ButterKnife.bind(getActivity());
        setYoutubeData();


    }
    private void setYoutubeData() {
        List<YoutubeVideoModel> youtubeVideo = prepareList();
        mRecyclerAdapter = new YoutubeRecyclerAdapter(youtubeVideo);
        mLayoutManager =new LinearLayoutManager(getActivity());
    }


    private List<YoutubeVideoModel> prepareList() {
        ArrayList<YoutubeVideoModel> videoArrayList = new ArrayList<>();

        //add 데모 아이템
        YoutubeVideoModel video1 = new YoutubeVideoModel();
        video1.setId(1l);
        video1.setImageUrl("https://i.ytimg.com/vi/zI-Pux4uaqM/maxresdefault.jpg");
        video1.setTitle(
                "Thugs Of Hindostan - Official Trailer | Amitabh Bachchan | Aamir Khan");
        video1.setVideoId("zI-Pux4uaqM");
        videoArrayList.add(video1);

        // add second item
        YoutubeVideoModel video2 = new YoutubeVideoModel();
        video2.setId(2l);
        video2.setImageUrl("https://i.ytimg.com/vi/8ZK_S-46KwE/maxresdefault.jpg");
        video2.setTitle(
                "Colors for Children to Learning with Baby Fun Play with Color Balls Dolphin...");
        video2.setVideoId("8ZK_S-46KwE");

        // add third item
        YoutubeVideoModel video3 = new YoutubeVideoModel();
        video3.setId(3l);
        video3.setImageUrl("https://i.ytimg.com/vi/8czMWUH7vW4/hqdefault.jpg");
        video3.setTitle("Air Hostess Accepts Marriage Proposal Mid-Air, Airline Fires her.");
        video3.setVideoId("8czMWUH7vW4");

        // add four item
        YoutubeVideoModel video4 = new YoutubeVideoModel();
        video4.setId(4l);
        video4.setImageUrl("https://i.ytimg.com/vi/YrQVYEb6hcc/maxresdefault.jpg");
        video4.setTitle("EXPERIMENT Glowing 1000 degree METAL BALL vs Gunpowder (100 grams)");
        video4.setVideoId("YrQVYEb6hcc");

        // add four item
        YoutubeVideoModel video5 = new YoutubeVideoModel();
        video5.setId(5l);
        video5.setImageUrl("https://i.ytimg.com/vi/S84Fuo2rGoY/maxresdefault.jpg");
        video5.setTitle("What happened after Jauhar of Padmavati");
        video5.setVideoId("S84Fuo2rGoY");

        videoArrayList.add(video1);
        videoArrayList.add(video2);
        videoArrayList.add(video3);
        videoArrayList.add(video4);
        return videoArrayList;
    }





}
