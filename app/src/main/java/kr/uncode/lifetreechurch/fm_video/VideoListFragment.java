package kr.uncode.lifetreechurch.fm_video;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import kr.uncode.lifetreechurch.Config.UnCodeVideoConfig;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.RecyclerViewDecoration;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.YoutubeRecyclerAdapter;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmMorningvideolistBinding;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class VideoListFragment extends BaseFragment {

//    private static final String MORNING = "MORNING";
//    private static final String AFTERNOON = "AFTERNOON";
//    private static final String WEDNESDAY = "WEDNESDAY";
//    private static final String DAWN = "DAWN";

    private Boolean isFabOpen = false;
    private Animation fab_open, fab_close;

    // 유튜브 객체
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerUtils youTubePlayerUtils;

    //사용자 카테고리 분류 value
    private String player_state = null;

    //어댑터 셋
    private YoutubeRecyclerAdapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //    private YouTubePlayerView youTubePlayerViewLayout;

    //바인딩
    FmMorningvideolistBinding binding;

    //게시되는 유튜브 아이디 저장 값
    private String secondVideo = null;

    //Config 객처
    private UnCodeVideoConfig unCodeVideoConfig;

    private MyVideoStorage myVideoStorage;

    //액티비티 객체
    public Activity activity;


    private ArrayList<String> list = new ArrayList<String>();

    private JSONArray a;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //데이터바인딩 유튜브 라이브러리 적용 어려워서 일단 기본틀로 가려고 주석
        videoListTopMenuShowController(true);

        binding = DataBindingUtil.inflate(inflater, R.layout.fm_morningvideolist, container, false);
        binding.categoryButton.setOnClickListener(this::viewShow);
        return binding.getRoot();
    }

    private void viewShow(View view) {

        binding.checkboxArea.setVisibility(View.VISIBLE);
        anim();
    }

    private void onRadioButtonClicked(View view) {
        MLog.d("categoty click");
    }

    public void anim() {

        if (isFabOpen) {
            binding.checkboxArea.startAnimation(fab_close);
            binding.checkboxArea.setVisibility(View.GONE);
            isFabOpen = false;
        } else {
            binding.checkboxArea.startAnimation(fab_open);
            binding.checkboxArea.setVisibility(View.VISIBLE);
            isFabOpen = true;
        }
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.categoryButton.setOnClickListener(this::viewShow);
        onRadioButtonClicked(view);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        //리사이클러뷰
        mRecyclerAdapter = new YoutubeRecyclerAdapter();

        //realmJSON 내가본 비디오 보관
//        a = new JSONArray();
//        myVideoStorage = new MyVideoStorage();
//        activity = getActivity();
//        if (activity != null && activity instanceof MainActivity)
//
//
//         //JSON 객체 받아오기
//         savedInstanceState = getArguments();
//        String aa = savedInstanceState.getString("MEME");
//        MLog.d("args" + aa);

        //사용자 카테고리 밸류 값 프래그먼트에서 전달받기
//        player_state = aa;

        //그값으로 Retrofit 요청 파라미터 같이 던짐
        /**
         * 유튜브 게시
         */
        getVideoId();


        //어댑터에서 온클릭리스너의 상황을 듣고 있는 리스너
        recyclerClickListener(youTubePlayer);
//        setYoutubeData();

    }

    private void recyclerClickListener(final YouTubePlayer youTubePlayer) {
        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {

                //REALM으로 최근 본 영상기록을 저장하기 위해 클릭이 일어난 데이터를 Realm에 저장
                UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) aa.get(position);
                String playId = items.videoId;
                String title = items.title;
                String imageUrl = items.thumbnail;

                MLog.d("new Click Clik: " + playId + title + imageUrl);


                //이두개의 메세드의 순서가 바뀌면 에러가남
                changingVideo(playId, youTubePlayer);
                //realm 저장
                saveVideo(title, imageUrl, playId);
            }
        });

    }


    //클릭하면 동영상 바꾸기
    private void changingVideo(String video, final YouTubePlayer youTubePlayer) {
        MLog.d("changing video");

//        binding.youtubePlayerView.setEnableAutomaticInitialization();to
        if (video != null && youTubePlayer != null) {
            youTubePlayerUtils.loadOrCueVideo(
                    youTubePlayer, getLifecycle(),
                    video, 0f
            );
        }


    }

    private void saveVideo(String title, String image, String videoId) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserVideo userVideo = realm.createObject(UserVideo.class);
                userVideo.setVideoId(videoId);
                userVideo.setImage_Url(image);
                userVideo.setTitle(title);
                MLog.d("saveVideo" + videoId + image + title);
            }
        });

    }


    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        //value arrayString type
        //어레이 리스트에 값을 JSON타입으로 변경
        for (int i = 0; i < values.size(); i++) {
            //제이슨 어레이리스트에 풋 밸류 밸류는 play ID 리스트  사이즈만큰
            a.put(values.get(i));
            MLog.d("contains check get JSOn" + a);
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


    /**
     *
     */
    private void getVideoId() {
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoList(new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                MLog.d("youtube Model Ok");

                if (response != null) {
                    mRecyclerAdapter.setItems(response.data);

                    for (int a = 0; a < response.data.size(); a++) {
                        if (a == 1) {
                            secondVideo = response.data.get(a).videoId;
                            MLog.d("youtube Model Ok" + secondVideo);

                        }
                    }
                    initYouTubePlayerView(secondVideo);

                }
                setYoutubeData();
//                mRecyclerAdapter.notifyDataSetChanged();

            }
        });
//        unCodeVideoConfig.unCodeVideoList(new ResponseCallback<UnCodeVideoModel>() {
//            @Override
//            public void response(UnCodeVideoModel response) {
//                if (response != null) {
//                    MLog.d("youtube Model Ok");
//                    mRecyclerAdapter.setItems(response.data);
//
//                    for (int a=0;a<response.data.size(); a++) {
//                        if (a ==1) {
//                            String secondVideo = response.data.get(a).videoId;
//                            initYouTubePlayerView(secondVideo);
//                        }
//                    }
//                }
//                setYoutubeData();
//
//            }
//        });
//        videoConfig = new VideoConfig();
//        videoConfig.videoList(new ResponseCallback<YoutubeResponse>() {
//            @Override
//            public void response(YoutubeResponse response) {
//                if (response != null) {
//                    MLog.d("youtubeModel Ok!");
//                    mRecyclerAdapter.setItems(response.items);
//
//                    for (int a = 0; a < response.items.size(); a++) {
//                        if (a == 1) {
//                            String secondVideo = response.items.get(a).id.videoId;
//                            MLog.d("second video:" + secondVideo);
//                            initYouTubePlayerView(secondVideo);
//                        }
//                    }
//                }
//            }
//        });


    }

    private void initYouTubePlayerView(String secondVideo) {

//        getLifecycle().addObserver(binding.youtubePlayerView);

        try {
            binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);

                    recyclerClickListener(youTubePlayer);

                    youTubePlayer.cueVideo(secondVideo, 0f);
//                    YouTubePlayerUtils.loadOrCueVideo(
//                            youTubePlayer,
//                            getLifecycle(),
//                            secondVideo, 0f
//                    );
//                addFullScreenListenerToPlayer();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), "netwok 상태확인 요망", Toast.LENGTH_LONG).show();
        }

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
