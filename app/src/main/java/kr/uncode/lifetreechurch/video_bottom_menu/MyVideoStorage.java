package kr.uncode.lifetreechurch.video_bottom_menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
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

    private YouTubePlayerUtils youTubePlayerUtils;
    private String youTubePlayId;
    public String videoId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_myvideostorage, container, false);
        setHasOptionsMenu(true);
        Toast.makeText(getContext(),"new Fragment",Toast.LENGTH_LONG).show();
//        binding.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Realm realm = Realm.getDefaultInstance();
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        final RealmResults<UserVideo> keyresult = realm.where(UserVideo.class).findAll();
//                        keyresult.deleteAllFromRealm();
//
//                    }
//                });
//            }
//        });
        if (youTubePlayId != null) {
            initYouTubePlayerView(youTubePlayId);
        } else {
            Toast.makeText(getContext(), "보관함이비워져 있습니다.", Toast.LENGTH_LONG).show();
        }
        setYoutubeData();
        return binding.getRoot();

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mene_storage,menu);
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
            if (i ==0) {
                youTubePlayId = videoId;
            }
        }

        recentAdapter = new RecentAdapter();
        recentAdapter.setItems(tem);



        toolbarMenuButtonController(true);





    }


    public void refresh() {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.detach(this).attach(this).commit();
        Toast.makeText(getContext(),"new Fragment",Toast.LENGTH_LONG).show();
    }

    private void initYouTubePlayerView(String secondVideo) {
        getLifecycle().addObserver(binding.youtubePlayerView);
        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);

                youTubePlayer.loadVideo(secondVideo,0f);
//                recyclerClickListener(youTubePlayer);
//                youTubePlayerUtils.loadOrCueVideo(
//                        youTubePlayer,
//                        getLifecycle(),
//                        secondVideo, 0f
//                );
//                addFullScreenListenerToPlayer();
            }
        });
    }



    private void setYoutubeData() {
        binding.recyclerViewFeed.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewFeed.setLayoutManager(layoutManager);
//        binding.recyclerViewFeed.addItemDecoration(new RecyclerViewDecoration());
        binding.recyclerViewFeed.setAdapter(recentAdapter);
    }



}
