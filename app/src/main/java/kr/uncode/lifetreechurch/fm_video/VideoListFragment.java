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


import com.google.gson.JsonArray;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
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

    private YouTubePlayer myyouTubePlayer;
    //    public YouTubePlayer youTubePlayer;
    private static String YOUTUBE = "YOUTUBE";
    public Activity activity;
    private ArrayList<String> list = new ArrayList<String>();

    private JSONArray a;
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
        a = new JSONArray();
        myVideoStorage = new MyVideoStorage();
        activity = getActivity();
        if (activity != null && activity instanceof MainActivity)
            getVideoId();


        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {

                YoutubeResponse.Items items = (YoutubeResponse.Items) aa.get(position);
                String playId = items.id.videoId;

                MLog.d("new Click Clik: " + playId);


//                initYouTubePlayerView(playId);
//                secondVideoRun(playId);

//                myyouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
//                    @Override
//                    public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {
//                        super.onStateChange(youTubePlayer, state);
//                        if (state == PlayerConstants.PlayerState.PLAYING) {
//                            myyouTubePlayer.pause();
//                            MLog.d("youtube pause!!");
//                        }
//                    }
//                });

//                myyouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
//                    @Override
//                    public void onReady(YouTubePlayer youTubePlayer) {
//                        super.onReady(youTubePlayer);
//                        YouTubePlayerUtils.loadOrCueVideo(
//                                youTubePlayer,
//                                getLifecycle(),
//                                playId, 0f
//                        );
//
//                    }
//                });

//                youtubeListener(playId);


                if (playId != null) {

                    Context context;
                    context = getContext();

                    if (!list.contains(playId)) {
                        //list = 아이디 객체를 담는 스트링 타입 리스트
                        list.add(playId);
                    }

                    MLog.d("list contanin :" + list);
                    setStringArrayPref(context, "MEME", list);
//                    Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
//                    intent.putExtra(YOUTUBE, playId);

//                    myVideoStorage.listener(playId);
//                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("data", playId);
//                    editor.commit();

//                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//
//                    String data = sharedPreferences.getString("data", "");
//                    MLog.d("preferencec :" + data);


//                    startActivity(intent);

                }

            }
        });
//        setYoutubeData();


    }


    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        //value arrayString type
        //어레이 리스트에 값을 JSON타입으로 변경
        for (int i = 0; i < values.size(); i++) {
            //제이슨 어레이리스트에 풋 밸류 밸류는 play ID 리스트  사이즈만큰
              if
                a.put(values.get(i));
                MLog.d("contains check get JSOn" + a );
        }

        //JSON을 PREPERENCE로
        // playId리스트가 비워져있니?
        //프리페어런스에 값넣는 구문
        if (!values.isEmpty()) {
            MLog.d("preference true");
            editor.putString(key, a.toString());
        } else {
            MLog.d("preference false");
            editor.putString(key, null);
        }
        editor.apply();

        MLog.d("json Set" + a.length());
        getStringArrayPref(context, "MEME");
    }

    private ArrayList<String> getStringArrayPref(Context context, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString("MEME", null);
        ArrayList<String> urls = new ArrayList<String>();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    urls.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MLog.d("Json get :" + urls);

        return urls;
    }

    private void youtubeListener(String videoId) {

//        YouTubePlayerUtils.loadOrCueVideo(
//                myyouTubePlayer, getLifecycle(),
//                videoId, 0f
//        );
//        MLog.d("new Click Clik: " + videoId);


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
                            initYouTubePlayerView(secondVideo);
                        }
                    }
                    setYoutubeData();
                }
            }
        });
    }

    private void initYouTubePlayerView(String secondVideo) {

        getLifecycle().addObserver(binding.youtubePlayerView);

        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        secondVideo, 0f
                );
//                addFullScreenListenerToPlayer();
                myyouTubePlayer = youTubePlayer;
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.youtubePlayerView.release();
    }
}
