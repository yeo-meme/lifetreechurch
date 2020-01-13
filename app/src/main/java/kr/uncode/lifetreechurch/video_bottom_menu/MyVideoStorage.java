package kr.uncode.lifetreechurch.video_bottom_menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmMyvideostorageBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class MyVideoStorage extends BaseFragment {

    FmMyvideostorageBinding binding;

    private RecentAdapter recentAdapter;
    private SharedPreferences pref;
    private String storageUrl = null;
    private YouTubePlayer youTubePlayer;
    private List<UserVideo> userVideoList;

    private YouTubePlayerUtils youTubePlayerUtils;
    private String youTubePlayId;
    public String videoId;

    public YouTubePlayer mYouTubePlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_myvideostorage, container, false);
        setHasOptionsMenu(true);
        recentMenuShowController(false);
        toolbarMenuButtonController(true);
        if (youTubePlayId != null) {
            initYouTubePlayerView(youTubePlayId);

            binding.empty.setVisibility(View.GONE);
            binding.youtubeCard.setVisibility(View.VISIBLE);
        } else {

            binding.empty.setVisibility(View.VISIBLE);
            binding.youtubeCard.setVisibility(View.GONE);
            toolbarMenuButtonController(false);
//            binding.youtubePlayerView.release();
        }
        setYoutubeData();
        MLog.d("num 2");
//        recyclerViewListener(mYouTubePlayer);
        return binding.getRoot();

    }

    private void changeVideo(String videoId,final YouTubePlayer youTubePlayer) {
        MLog.d("xxxxx"+ videoId);
        MLog.d("num 6");
        if (videoId != null) {
            try {
                youTubePlayer.cueVideo(videoId,0f);
            } catch (Exception e) {}
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mene_storage, menu);
    }

    public void recyclerViewListener(final YouTubePlayer youTubePlayer) {
        recentAdapter.setOnItemClickListener(new OnItemClickListener<UserVideo>() {
            @Override
            public void onListItemClick(List<UserVideo> userVideos, int position) {
                MLog.d("hiiiii storage");
                MLog.d("num 1");
                MLog.d("listener youtubePlayer" + mYouTubePlayer);
                MLog.d("hiiiii storage" + userVideos.get(position).videoId);
                String videoId = userVideos.get(position).videoId;
                String title = userVideos.get(position).title;
                changeVideo(videoId,youTubePlayer);
            }
        });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserVideo> data = realm.where(UserVideo.class).findAll();



        MLog.d("userVideo 1:" + data);
        List<UserVideo> tem = data;
        MLog.d("userVideo 2:" + tem);


        for (int i = 0; i < tem.size(); i++) {
            videoId = tem.get(i).getVideoId();
            MLog.d("userVideo 3:" + videoId);
            if (i == 0) {
                youTubePlayId = videoId;
            }
        }

        recentAdapter = new RecentAdapter();
        recentAdapter.setItems(tem);


//        toolbarMenuButtonController(true);


    }




    private void initYouTubePlayerView(String secondVideo) {
        MLog.d("num 3");

//        getLifecycle().addObserver(binding.youtubePlayerView);
        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
//                recyclerClickListener(youTubePlayer);
                youTubePlayer.cueVideo(secondVideo, 0f);
                mYouTubePlayer = youTubePlayer;
                recyclerViewListener(youTubePlayer);

            }
        });
    }


    private void setYoutubeData() {
        binding.recyclerViewFeed.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewFeed.setLayoutManager(layoutManager);
//        binding.recyclerViewFeed.addItemDecoration(new RecyclerViewDecoration());
        binding.recyclerViewFeed.setAdapter(recentAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

//        toolbarMenuButtonController(false);
    }


}
