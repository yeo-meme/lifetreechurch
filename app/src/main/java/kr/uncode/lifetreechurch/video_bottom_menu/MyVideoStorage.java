package kr.uncode.lifetreechurch.video_bottom_menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
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
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.RecyclerViewDecoration;
import kr.uncode.lifetreechurch.YoutubeRecyclerAdapter;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmMyvideostorageBinding;
import kr.uncode.lifetreechurch.fm_video.YoutubePlayerActivity;
import kr.uncode.lifetreechurch.utils.MLog;

import static android.content.Context.MODE_PRIVATE;

public class MyVideoStorage extends BaseFragment {

    FmMyvideostorageBinding binding;

    private RecentAdapter recentAdapter;
    private SharedPreferences pref;
    private String storageUrl = null;
    private YouTubePlayer youTubePlayer;
    private List<UserVideo> userVideoList;

    public String videoId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_myvideostorage, container, false);
        setYoutubeData();
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final RealmResults<UserVideo> keyresult = realm.where(UserVideo.class).findAll();
                        keyresult.deleteAllFromRealm();

                    }
                });
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recentAdapter = new RecentAdapter();

        toolbarMenuButtonController(true);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<UserVideo> data = realm.where(UserVideo.class).findAll();

        MLog.d("userVideo 1:" + data);
        List<UserVideo> tem = data;
        MLog.d("userVideo 2:" + tem);

        recentAdapter.setItems(tem);


            for (int i = 0; i < tem.size(); i++) {
                videoId = tem.get(i).getVideoId();
                MLog.d("userVideo 3:" + videoId);
        }
        if (videoId != null) {
//                    initYouTubePlayerView(videoId);
        } else {
            Toast.makeText(getContext(), "보관함이비워져 있습니다.", Toast.LENGTH_LONG).show();

        }

    }

//    private void initYouTubePlayerView(String secondVideo) {
//
//
//        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                youTubePlayer.loadVideo(secondVideo, 0);
//
//            }
//        });


//        getLifecycle().addObserver(binding.youtubePlayerView);
//
//        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
////                recyclerClickListener(youTubePlayer);
//                YouTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        secondVideo, 0f
//                );
////                addFullScreenListenerToPlayer();
//            }
//        });
//    }

    public void listener() {
//        savePreferences();


//        binding.myStorage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savePreferences();
//            }
//        });

    }

    private void setYoutubeData() {
        binding.recyclerViewFeed.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewFeed.setLayoutManager(layoutManager);
//        binding.recyclerViewFeed.addItemDecoration(new RecyclerViewDecoration());
        binding.recyclerViewFeed.setAdapter(recentAdapter);
    }


    private void savePreferences() {


//        SharedPreferences.Editor editor= pref.edit();
//        getPreferences();
    }

    private void getPreferences() {
        pref = getActivity().getSharedPreferences("hihihi", MODE_PRIVATE);

        MLog.d("shared" + pref);
    }
}
