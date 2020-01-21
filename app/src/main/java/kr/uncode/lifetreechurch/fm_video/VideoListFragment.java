package kr.uncode.lifetreechurch.fm_video;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.uncode.lifetreechurch.Config.UnCodeVideoConfig;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.RecyclerViewDecoration;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmMorningvideolistBinding;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.utils.Utils;


public class VideoListFragment extends BaseFragment implements OnItemClickListener<UnCodeVideoModel.Data> {


    private boolean fabExpanded = false;

    private Boolean isFabOpen = false;
    private Animation fab_open, fab_close;

    // 유튜브 객체
    private YouTubePlayer mYoutubePlayer;
    private YouTubePlayerUtils youTubePlayerUtils;

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


    //액티비티 객체

    private Integer currentPage = 0;

    private List<UnCodeVideoModel.Data> unCodeModelList = new ArrayList<>();

    private Integer totalItemCount = 10;


    private boolean moring_check = false;
    private boolean after_check = false;
    private boolean wed_check = false;
    private boolean dwan_check = false;

    private boolean all = false;


    private Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //데이터바인딩 유튜브 라이브러리 적용 어려워서 일단 기본틀로 가려고 주석
//        videoListTopMenuShowController(true);


        binding = DataBindingUtil.inflate(inflater, R.layout.fm_morningvideolist, container, false);
//        binding.categoryButton.setOnClickListener(this::viewShow);
//


//        binding.allListButton.allButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//
//                if (b == false) {
//                    MLog.d("touchhhhh");
//                    closeSubMenusFab();
//
//                }
//            }
//        });
        //카테고리아이콘
        mRecyclerAdapter.setOnItemClickListener(this);

//        delayClick();
        drawingIcon();
        //툴바메뉴
        toolbarMenuButtonController(false);
        backKeyShowController(true);
        recentMenuShowController(true);

//        touchListener(binding.getRoot());
        return binding.getRoot();
    }

//    private void touchListener(View view) {
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//               if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
//                   MLog.d("onTouch");
//               }
//                return true;
//            }
//        });
//    }

    private void addCustomActionsToPlayer() {
        Drawable customAction1Icon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_insert_emoticon_black_24dp);
        Drawable customAction2Icon = ContextCompat.getDrawable(getActivity(), R.drawable.ic_insert_emoticon_black_24dp);
        assert customAction1Icon != null;
        assert customAction2Icon != null;

        binding.youtubePlayerView.getPlayerUiController().setCustomAction1(customAction1Icon, view ->
                Toast.makeText(getActivity(), "custom action1 clicked", Toast.LENGTH_SHORT).show());

        binding.youtubePlayerView.getPlayerUiController().setCustomAction2(customAction2Icon, view ->
                Toast.makeText(getActivity(), "custom action1 clicked", Toast.LENGTH_SHORT).show());
    }


    private void removeCustomActionsFromPlayer() {
        binding.youtubePlayerView.getPlayerUiController().showCustomAction1(false);
        binding.youtubePlayerView.getPlayerUiController().showCustomAction2(false);
    }


    private void addFullScreenListenerToPlayer() {
        binding.youtubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                binding.youtubePlayerView.enterFullScreen();

                addCustomActionsToPlayer();
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                binding.youtubePlayerView.exitFullScreen();
                removeCustomActionsFromPlayer();
            }
        });
    }


    /**
     * 플로팅버튼 이미지 입히기
     */
    private void drawingIcon() {
//        binding.allListButton.dawnIcon.setBackground(new ShapeDrawable(new OvalShape()));
//        if (Build.VERSION.SDK_INT >= 21) {
//            binding.allListButton.dawnIcon.setClipToOutline(true);
//        }
        Glide.with(getContext())
                .load(R.drawable.c)
                .circleCrop()
//                .apply(new RequestOptions().circleCrop().centerCrop())
                .into(binding.allListButton.dawnIcon);
        Glide.with(getContext())
                .load(R.drawable.b)
                .circleCrop()
//                .apply(new RequestOptions().circleCrop().centerCrop())
                .into(binding.allListButton.afterIcon);
        Glide.with(getContext())
                .load(R.drawable.a)
                .circleCrop()
//                .apply(new RequestOptions().circleCrop().centerCrop())
                .into(binding.allListButton.morningIcon);
        Glide.with(getContext())
                .load(R.drawable.d)
                .circleCrop()
//                .apply(new RequestOptions().circleCrop().centerCrop())
                .into(binding.allListButton.wedIcon);

    }

//    private void viewShow(View view) {
//        binding.checkboxArea.setVisibility(View.VISIBLE);
//    }


    public void anim() {

        if (fabExpanded) {
            binding.allListButton.allMenuButton.startAnimation(fab_close);
            binding.allListButton.allMenuButton.setVisibility(View.GONE);
            isFabOpen = false;
        } else {
            binding.allListButton.allMenuButton.startAnimation(fab_open);
            binding.allListButton.allMenuButton.setVisibility(View.VISIBLE);
            isFabOpen = true;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.categoryButton.setOnClickListener(this::viewShow);
//        radioGroup(view);
//        recyclerClickListener(mYoutubePlayer);
        MLog.d("num 2");
        MLog.d("onCreated View youTubePlayer" + mYoutubePlayer);


//        binding.allBackground.setOnTouchListener(mTouchEvent);
        //카테고리 선택할때 영상을 바꿔줌
        categoryChanger();

        fabEx();
        //팝업창 애니메이션
        anim();


        //스크롤 마지막에 닿았을때 데이터 새로 불러오기
        scrollChanger();

        //처음 게시물 불러오는 함수덴 온크리에이트에서도 불리고 있었음
//       allList_get(view);
    }


    private View.OnTouchListener mTouchEvent = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int action = motionEvent.getActionMasked();

            if (action == MotionEvent.ACTION_OUTSIDE) {
                MLog.d("touchchchchch");
            }
            return true;
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        progressON("Loading...");
        getVideoId(currentPage);
        scrollChanger();

    }

    private void setmYoutubePlayer() {


    }

    /**
     * 전체보기 버튼 클릭할때 팝업메뉴버튼
     * true = fabExpanded .. 클로즈
     * false = fabExpanded  .. 오픈
     */
    private void fabEx() {

        binding.allListButton.allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true) {
                    closeSubMenusFab();
                    MLog.d("opne :" + fabExpanded);
                } else {
                    openSubMenuFab();
                    MLog.d("opne :" + fabExpanded);

                }
            }
        });
    }

    /**
     * 전체보기 버튼 클릭할때 팝업메뉴버튼
     * true = fabExpanded .. 클로즈
     * false = fabExpanded  .. 오픈
     */
    private void openSubMenuFab() {
        binding.allListButton.morning.setVisibility(View.VISIBLE);
        binding.allListButton.after.setVisibility(View.VISIBLE);
        binding.allListButton.dawn.setVisibility(View.VISIBLE);
        binding.allListButton.wed.setVisibility(View.VISIBLE);
//        binding.allListButton.recent.setVisibility(View.VISIBLE);
        binding.allListButton.callAllListBtn.setVisibility(View.VISIBLE);
        fabExpanded = true;
    }

    /**
     * 전체보기 버튼 클릭할때 팝업메뉴버튼
     * true = fabExpanded .. 클로즈
     * false = fabExpanded  .. 오픈
     */
    private void closeSubMenusFab() {
        binding.allListButton.morning.setVisibility(View.GONE);
        binding.allListButton.after.setVisibility(View.GONE);
        binding.allListButton.dawn.setVisibility(View.GONE);
        binding.allListButton.wed.setVisibility(View.GONE);
//        binding.allListButton.recent.setVisibility(View.GONE);
        binding.allListButton.callAllListBtn.setVisibility(View.GONE);

        fabExpanded = false;

    }


    /**
     * 카테고리별로 스크롤이 마지막이 됐을때 페이징 하기 위해 레트로핏 데이터 호출
     * 307
     *
     * @param categoryId 카테고리별 네임
     * @param pageNum    초기값 0 +1씩 증가! 스크롤해서 > add로 넘어와을때
     */
    private void addVideoCategory(String categoryId, Integer pageNum) {
        progressON();
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoCategoryList(categoryId, pageNum, new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                if (response != null) {
//                    binding.recyclerViewFeed.setAdapter(mRecyclerAdapter);
                    MLog.d("allAll result :" + response.data);
                    mRecyclerAdapter.addItem(response.data);
                    MLog.d("unCodeModeList add :" + unCodeModelList);
                    mRecyclerAdapter.notifyDataSetChanged();
                    binding.recyclerViewFeed.setAdapter(mRecyclerAdapter);
                    progressOFF();
                }
            }
        });
    }

    /**
     * 스크롤 마지막 인덱스를 읽은후 새로운 리사이클러뷰 불러오기
     */
    private void scrollChanger() {
//        LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(binding.recyclerViewFeed.getLayoutManager());
//        int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();

        binding.recyclerViewFeed.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //어댑터가 들고 있는 총 아이템의 갯수
//                 totalItemCount = layoutManager.getItemCount();
            //어댑터가 알고 있는 마지막 아이템 인덱스 반환

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (lastVisibleItemPosition >= totalItemCount - 1) {

                    if (moring_check == true) {
                        MLog.d("scroll : 오전in");
                        String morning = "오전";
                        addVideoCategory(morning, currentPage);
                        MLog.d("마지막 totalItemCount: " + totalItemCount);
                        MLog.d("마지막 lastVisiblNUm : " + lastVisibleItemPosition);
                    }
                    if (after_check == true) {
                        String after = "오후";
                        MLog.d("scroll : 오후");
                        addVideoCategory(after, currentPage);
                        MLog.d("마지막 totalItemCount: " + totalItemCount);
                        MLog.d("마지막 lastVisiblNUm : " + lastVisibleItemPosition);
                    }

                    if (wed_check == true) {
                        String wed = "수요";
                        MLog.d("scroll : 수요일");
                        addVideoCategory(wed, currentPage);
                        MLog.d("마지막 totalItemCount: " + totalItemCount);
                        MLog.d("마지막 lastVisiblNUm : " + lastVisibleItemPosition);
                    }

                    if (dwan_check == true) {
                        String dawn = "새벽";
                        MLog.d("scroll : 새벽");
                        addVideoCategory(dawn, currentPage);
                        MLog.d("마지막 totalItemCount: " + totalItemCount);
                        MLog.d("마지막 lastVisiblNUm : " + lastVisibleItemPosition);
                    }

                    if (all == true) {
                        MLog.d("scroll : 전체");
                        addVideoCategory("", currentPage);
                        MLog.d("here in all");
                    }
//                    MLog.d("마지막 lastVisiblNUm : " + lastVisibleItemPosition);
//                    addVideoAll(currentPage);
//                    MLog.d("마지막 totalItemCount: " + totalItemCount);


                    totalItemCount += 10;
                    currentPage += 1;
                    delayedRecyclerNotice(lastVisibleItemPosition + 1);

                    MLog.d("lastVisible :" + lastVisibleItemPosition);
                }
            }
        });
    }

    /**
     * 리사이클러뷰 데이터 체인지 후 포커스 지정을 위해 시간차 딜레이 주기
     *
     * @param lastVisible
     */
    private void delayedRecyclerNotice(Integer lastVisible) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.recyclerViewFeed.getLayoutManager().scrollToPosition(lastVisible);
                MLog.d("handler Check :" + lastVisible);
            }
        }, 300);
    }

    /**
     * 카테고리 메뉴에서 사용자가 전체보기 눌렀을때 전체 유튜브 리스트를 불러오기
     *
     * @param view
     */
//    private void allList_get(View view) {
//        getVideoId(currentPage);
//    }
//

    /**
     * 카테고리 별 메뉴 클릭시 화면 체인져
     */
    private void categoryChanger() {
        //주일오전
        binding.allListButton.morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressON("Loading...");

                String categoryId = "오전";
                mRecyclerAdapter.clearItem();
                currentPage = 0;
                totalItemCount = 10;
                moring_check = true;
                after_check = false;
                wed_check = false;
                dwan_check = false;
                all = false;

                getVideoCategroyId(categoryId, currentPage);

                closeSubMenusFab();
            }
        });

        //주일오후
        binding.allListButton.after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressON("Loading...");


                String categoryId = "오후";
                mRecyclerAdapter.clearItem();
                moring_check = false;
                after_check = true;
                wed_check = false;
                dwan_check = false;
                all = false;

                currentPage = 0;
                totalItemCount = 10;
                getVideoCategroyId(categoryId, currentPage);
                closeSubMenusFab();


            }
        });

        //새벽예배
        binding.allListButton.dawn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressON("Loading...");


                String categoryId = "새벽";
                mRecyclerAdapter.clearItem();
                moring_check = false;
                after_check = false;
                wed_check = false;
                dwan_check = true;
                all = false;

                currentPage = 0;
                totalItemCount = 10;
                getVideoCategroyId(categoryId, currentPage);
                closeSubMenusFab();

            }
        });

        //수요예배
        binding.allListButton.wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressON("Loading...");

                String categoryId = "수요";
                mRecyclerAdapter.clearItem();
                moring_check = false;
                after_check = false;
                wed_check = true;
                dwan_check = false;
                all = false;
                currentPage = 0;
                totalItemCount = 10;
                getVideoCategroyId(categoryId, currentPage);
                closeSubMenusFab();

            }
        });

        //전체보기
        binding.allListButton.callAllListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressON("Loading...");


                MLog.d("call all list");
                mRecyclerAdapter.clearItem();
                moring_check = false;
                after_check = false;
                wed_check = false;
                dwan_check = false;
                all = true;
//                allList_get(view);
                getVideoCategroyId("", currentPage);
                closeSubMenusFab();

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 카테고리 오픈 클로즈 애니메이션 적용
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        //리사이클러뷰
        mRecyclerAdapter = new YoutubeRecyclerAdapter();

        /**
         * 사용자 유튜브 게시판 처음 들어왔을때 첫게시
         */
        getVideoId(currentPage);


        context = getContext();
        all = true;

        if (Utils.isNetworkConnected(context)) {
        } else {
            Toast.makeText(context, "네트워크 연결이 안되어 있어 실행되지 않습니다~", Toast.LENGTH_LONG).show();
            notConnected();
        }


        //어댑터에서 온클릭리스너의 상황을 듣고 있는 리스너
//        recyclerClickListener(youTubePlayer);

    }


    private void addVideoAll(Integer pageNum) {
        progressON();
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoList(pageNum, new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                if (response != null) {
                    unCodeModelList.addAll(response.data);
                    mRecyclerAdapter.setDataListItem(unCodeModelList);
                    setYoutubeData();
                    progressOFF();
                }
            }
        });
    }

    //
//
//    /**
//     * 리사이클러뷰에서 아이템 클릭이 일어났을때 듣고 있는 리스너
//     *
//     * @param youTubePlayer
//     */

//    public void recyclerClickListener(final YouTubePlayer youTubePlayer) {
//        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener<UnCodeVideoModel.Data>() {
//            @Override
//            public void onListItemClick(List<UnCodeVideoModel.Data> aa, int position) {
//


//
//                MLog.d("click in");
//                //REALM으로 최근 본 영상기록을 저장하기 위해 클릭이 일어난 데이터를 Realm에 저장
//                UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) aa.get(position);
//                String playId = items.videoId;
//                String title = items.title;
//                String imageUrl = items.thumbnail;
////
//                MLog.d("new Click Clik: " + playId + title + imageUrl);
//
//
////                이두개의 메세드의 순서가 바뀌면 에러가남
//                //클릭하면 동영상 바꾸기
//
//                changingVideo(playId, youTubePlayer);
//
//
//                //realm 저장
//                saveVideo(title, imageUrl, playId);
//            }
//        });
//
//    }


    //클릭하면 동영상 바꾸기
    private void changingVideo(String video) {


        MLog.d("youtube plater changing chekc" + mYoutubePlayer);
        mYoutubePlayer.cueVideo(video, 0f);
//        binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//                MLog.d("changing video" + video);
//                MLog.d("changing video youTubePlayer" + youTubePlayer);
////        binding.youtubePlayerView.setEnableAutomaticInitialization();to
//                if (video != null) {
//                    MLog.d("changing video in" + video);
//                    YouTubePlayerUtils.loadOrCueVideo(
//                            youTubePlayer, getLifecycle(),
//                            video, 0f
//                    );
//                }
//            }
//
////            youTubePlayer.cueVideo(video, 0f);
////            recyclerClickListener(youTubePlayer);
//
//        });
    }

    //
//

    /**
     * 최근 봤던 비디오 리얼엠에 저장하기
     *
     * @param title   유튜브 제목
     * @param image   유튜브 썸네일
     * @param videoId 유튜브 비디오 아이디
     */
    private void saveVideo(String title, String image, String videoId) {
        MLog.d("non save metheod");


        final Realm realm = Realm.getDefaultInstance();

        RealmResults<UserVideo> realmResults = realm.where(UserVideo.class).equalTo("videoId", videoId).findAll();


        if (realmResults.size() != 0) {
            MLog.d("realm result :" + realmResults);
        } else {
            MLog.d("realm result : no exsit" + realmResults);
            saveRecentVideo(title, image, videoId);

        }


//        List<String> temp = new ArrayList<>();
//
//        final Realm realm = Realm.getDefaultInstance();
//        RealmResults<UserVideo> realmResults = realm.where(UserVideo.class).findAll();
////        if (realmResults.size() == 0) {
////            saveRecentVideo(title, image, videoId); }
//        MLog.d("realmResult" + realmResults);
//
//
//        if (realmResults.size() != 0) {
//            saveRecentVideo(title, image, videoId);
//            for (int e = 0; e < realmResults.size(); e++) {
//                temp.add(realmResults.get(e).videoId);
//                MLog.d("realmResult videoId" + temp);
//            }
//
//            for (int i = 0; i < temp.size(); i++) {
//                MLog.d("text  aaaaa" + videoId.equals(temp.get(i)));
//            }
//        }

    }

    private void saveRecent(String title, String image, String videoId) {

    }

    private void saveRecentVideo(String title, String image, String videoId) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserVideo userVideo = realm.createObject(UserVideo.class);
                userVideo.setVideoId(videoId);
                userVideo.setImage_Url(image);
                userVideo.setTitle(title);
                MLog.d("saveVideo recent Video" + videoId + image + title);
            }
        });

    }

    //
//
//    /**
//     * 프리페어런스 봤던 아이디 저장하기 -- 사용하지 않음 realm으로 사용중 코드 정리 안되서 아직 안지웠음
//     *
//     * @param context
//     * @param key
//     * @return
//     */
//    private void setStringArrayPref(Context context, String key, ArrayList<String> values) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor editor = prefs.edit();
//
//        //value arrayString type
//        //어레이 리스트에 값을 JSON타입으로 변경
//        for (int i = 0; i < values.size(); i++) {
//            //제이슨 어레이리스트에 풋 밸류 밸류는 play ID 리스트  사이즈만큰
//            a.put(values.get(i));
//            MLog.d("contains check get JSOn" + a);
//        }
//
//        //JSON을 PREPERENCE로
//        // playId리스트가 비워져있니?
//        //프리페어런스에 값넣는 구문
//        if (!values.isEmpty()) {
//            MLog.d("preference true");
//            editor.putString(key, a.toString());
//        } else {
//            MLog.d("preference false");
//            editor.putString(key, null);
//        }
//        editor.apply();
//
//        MLog.d("json Set" + a.length());
//        //프리페어런스 = 현재는realm사용
////        getStringArrayPref(context, "MEME");
//    }
//
//
//    /**
//     * 프리페어런스 봤던 아이디 저장하기 -- 사용하지 않음 realm으로 사용중 코드 정리 안되서 아직 안지웠음
//     *
//     * @param context
//     * @param key
//     * @return
//     */
//    private ArrayList<String> getStringArrayPref(Context context, String key) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        String json = prefs.getString("MEME", null);
//        ArrayList<String> urls = new ArrayList<String>();
//        if (json != null) {
//            try {
//                JSONArray a = new JSONArray(json);
//                for (int i = 0; i < a.length(); i++) {
//                    String url = a.optString(i);
//                    urls.add(url);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        MLog.d("Json get :" + urls);
//
//        return urls;
//    }
//
//
//    private void addVideoAll(Integer currentPage) {
//        unCodeVideoConfig = new UnCodeVideoConfig();
//        unCodeVideoConfig.unCodeVideoList(currentPage, new ResponseCallback<UnCodeVideoModel>() {
//            @Override
//            public void response(UnCodeVideoModel response) {
//                //첫번째 영상을 꺼내기 위해 향상포문 인덱스 알기
//                int index = 0;
//                if (response != null) {
//                    VIDEO_LIST_ITEMS.addAll(response.data);
//                    mRecyclerAdapter.setDataListItem(VIDEO_LIST_ITEMS);
//                    MLog.d("VIDEO_LIST_ITEMS :" + VIDEO_LIST_ITEMS);
//                    //향상 포문으로 모델타입으로 retrofit 리절트를 셋해주기
//                    for (int a =0; a<response.data.size(); a++) {
////                        mRecyclerAdapter.addItem(data);
////                        binding.recyclerViewFeed.smoothScrollToPosition(response.data.size()-1);
//
//
////                        for (int a = 0; a > response.data.size(); a++) {
//                        if (a == 0) {
//                            secondVideo = response.data.get(index).videoId;
//                            MLog.d("youtube Model Ok!! category ID : " + secondVideo);
////                            }
//
//                        }
//
//                        //유튜브 비디오 아이디 넣고 게시하기
//                        initYouTubePlayerView(secondVideo);
////                        setYoutubeData();
//<<<<<<< HEAD
//                        mRecyclerAdapter.notifyDataSetChanged();
//                        binding.recyclerViewFeed.setAdapter(mRecyclerAdapter);
//
//=======
//>>>>>>> 3da1b363619a00139082b40febf507373d37136c
//
//                    }
////                    mRecyclerAdapter.addItem(dataModel);
//                }
//            }
//        });
//    }
//
//    /**
//     * 리사이클러뷰 SCROllLISTENER 의 마지막 인덱스가 되면 호출되는 메서드로
//     * RETROFIT 요청을 불러서 어댑터에 List<데이터>에 데이터를 ADD 하는 메서드로 호출한다
//     *
//     * @param categoryId  오전, 오후,수요, 새벽
//     * @param currentPage 1,2,3,4...... (페이징넘버)
//     */
//    private void addVideoCategory(String categoryId, Integer currentPage) {
//        //컨피그 인스턴스 객체 생성
//        unCodeVideoConfig = new UnCodeVideoConfig();
//
//        // 컨피그의 매개변수 아이디, 페이징수 매개변수 받은 메서드 호출
//        unCodeVideoConfig.unCodeVideoCategoryList(categoryId, currentPage, new ResponseCallback<UnCodeVideoModel>() {
//            @Override
//            public void response(UnCodeVideoModel response) {
//                //첫번째 영상을 꺼내기 위해 향상포문 인덱스 알기
//                int index = 0;
//                if (response != null) {
//                    VIDEO_LIST_ITEMS.addAll(response.data);
//                    mRecyclerAdapter.addItem(VIDEO_LIST_ITEMS);
//                    binding.recyclerViewFeed.setAdapter(mRecyclerAdapter);
//                    //향상 포문으로 모델타입으로 retrofit 리절트를 셋해주기
//                    for (int e =0; e < response.data.size(); e++) {
////                        binding.recyclerViewFeed.smoothScrollToPosition(response.data.size()-1);
//                        dataModel = response.data.get(e);
//
//                        YoutubeRecyclerAdapter.Item item = new YoutubeRecyclerAdapter.Item(dataModel);
////                        for (int a = 0; a > response.data.size(); a++) {
//                        if (e == 0) {
//                            secondVideo = response.data.get(index).videoId;
//                            MLog.d("youtube Model Ok!! category ID : " + secondVideo);
////                            }
//                            //유튜브 비디오 아이디 넣고 게시하기
//                            initYouTubePlayerView(secondVideo);
//
//                        }
//
//
//<<<<<<< HEAD
//
//                    }
//                    setYoutubeData();
//                    mRecyclerAdapter.notifyDataSetChanged();
//=======
////                        mRecyclerAdapter.addItem(dataModel);
////                        MLog.d("어댑터 add Item data :" + dataModel);
//
//                        //유튜브 비디오 아이디 넣고 게시하기
//                        initYouTubePlayerView(secondVideo);
//                        setYoutubeData();
//
//                    }
//
//>>>>>>> 3da1b363619a00139082b40febf507373d37136c
//                }
//
//            }
//        });
//    }
//

    /**
     * 카테고리를 변경하면 아이템을 만들기
     * <p>
     * 873
     *
     * @param categoryId  = 카테고리 키워드
     * @param currentPage = 카테고리 페이지 Number
     */
//
    private void getVideoCategroyId(String categoryId, Integer currentPage) {
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoCategoryList(categoryId, currentPage, new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                MLog.d("youtube Model Ok");
                if (response != null) {
//                    mRecyclerAdapter.clearItem();
                    mRecyclerAdapter.setItems(response.data);
                    for (int a = 0; a > response.data.size(); a++) {
                        if (a == 0) {
                            secondVideo = response.data.get(a).videoId;
                            MLog.d("youtube Model Ok!! category ID : " + secondVideo);
                        }
                    }
                    initYouTubePlayerView(secondVideo);
                    setYoutubeData();
                }

            }
        });

    }
//

    /**
     * 전체 보기 팝어버튼 선택시 유튜브 [전체보기] 불러오기
     */
    private void getVideoId(Integer pageNum) {
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoList(pageNum, new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                MLog.d("youtube Model Ok");

                if (response != null) {
                    mRecyclerAdapter.clearItem();
                    mRecyclerAdapter.setItems(response.data);

                    //게시할 첫번째 비디오 ID 가져오기
                    for (int a = 0; a < response.data.size(); a++) {
                        if (a == 1) {
                            secondVideo = response.data.get(a).videoId;
                            MLog.d("youtube Model Ok" + secondVideo);

                        }
                    }
                    initYouTubePlayerView(secondVideo);

                }
                setYoutubeData();
//                scrollChanger();
//                all = true;
                mRecyclerAdapter.notifyDataSetChanged();


            }
        });


    }


    //
    private void initYouTubePlayerView(String secondVideo) {
//        getLifecycle().addObserver(binding.youtubePlayerView);

        try {
            binding.youtubePlayerView.initialize(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    MLog.d("num 1");
                    MLog.d("youtube Player null check : " + youTubePlayer);
                    mYoutubePlayer = youTubePlayer;
                    MLog.d("youtube Player null check mmmmm : " + mYoutubePlayer);
                    MLog.d("num 5");
//                    addFullScreenListenerToPlayer();
                    youTubePlayer.cueVideo(secondVideo, 0f);
//                recyclerClickListener(mYoutubePlayer);

                    progressOFF();
                }
            });

        } catch (Exception e) {

        }

//        try {
//            binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                @Override
//                public void onReady(YouTubePlayer youTubePlayer) {
//                    super.onReady(youTubePlayer);
//
//                    MLog.d("num 1");
//                    MLog.d("youtube Player null check : " + youTubePlayer);
//                    mYoutubePlayer = youTubePlayer;
//                    MLog.d("youtube Player null check mmmmm : " + mYoutubePlayer);
//                    recyclerClickListener(mYoutubePlayer);
//                    MLog.d("num 5");
//                    youTubePlayer.cueVideo(secondVideo, 0f);
////                    YouTubePlayerUtils.loadOrCueVideo(
////                            youTubePlayer,
////                            getLifecycle(),
////                            secondVideo, 0f
////                    );
////                addFullScreenListenerToPlayer();
//                }
//            });
//        } catch (Exception e) {
//            Toast.makeText(getContext(), "network 상태 확인 요망", Toast.LENGTH_LONG).show();
//        }

    }


    //
//
//    /**
//     * 리사이클러뷰 셋
//     */
    private void setYoutubeData() {
        //패스
        binding.recyclerViewFeed.setHasFixedSize(true);
        //가로세로 설정 매니저
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        // Data View 처리
        binding.recyclerViewFeed.setLayoutManager(layoutManager);

        //기본 구분선(DividerItemDecoration) 추가
        binding.recyclerViewFeed.addItemDecoration(new RecyclerViewDecoration(2));

        binding.recyclerViewFeed.setAdapter(mRecyclerAdapter);
    }

    //
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.youtubePlayerView.release();
    }

    @Override
    public void onListItemClick(List<UnCodeVideoModel.Data> aa, int position) {
        MLog.d("click in");
        //REALM으로 최근 본 영상기록을 저장하기 위해 클릭이 일어난 데이터를 Realm에 저장
        UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) aa.get(position);
        String playId = items.videoId;
        String title = items.title;
        String imageUrl = items.thumbnail;
//
        MLog.d("new Click Clik: " + playId + title + imageUrl);


//                이두개의 메세드의 순서가 바뀌면 에러가남
        //클릭하면 동영상 바꾸기

        changingVideo(playId);

        closeSubMenusFab();
        //realm 저장
        saveVideo(title, imageUrl, playId);
    }


//}
}
