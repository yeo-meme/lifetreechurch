package kr.uncode.lifetreechurch.fm_video;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
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

import java.util.ArrayList;
import java.util.List;

import kr.uncode.lifetreechurch.Config.UnCodeVideoConfig;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.RecyclerViewDecoration;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmDawnvideolistBinding;
import kr.uncode.lifetreechurch.databinding.FmMorningvideolistBinding;
import kr.uncode.lifetreechurch.utils.MLog;


public class VideoListFragment extends BaseFragment {

    private boolean fabExpanded = false;

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


    //액티비티 객체
    public Activity activity;


    private Integer lastVisible;
    private JSONArray a;

    private Integer currentPage = 0;

    boolean lastitemVisibleFlag = false;

    private List<UnCodeVideoModel.Data> unCodeModelList = new ArrayList<>();

    private Integer totalItemCount = 10;

    private List<UnCodeVideoModel.Data> dataModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //데이터바인딩 유튜브 라이브러리 적용 어려워서 일단 기본틀로 가려고 주석
        videoListTopMenuShowController(true);

        binding = DataBindingUtil.inflate(inflater, R.layout.fm_morningvideolist, container, false);
//        binding.categoryButton.setOnClickListener(this::viewShow);

        return binding.getRoot();
    }

//    private void viewShow(View view) {
//        binding.checkboxArea.setVisibility(View.VISIBLE);
//        anim();
//    }


//    public void anim() {
//
//        if (isFabOpen) {
//            binding.checkboxArea.startAnimation(fab_close);
//            binding.checkboxArea.setVisibility(View.GONE);
//            isFabOpen = false;
//        } else {
//            binding.checkboxArea.startAnimation(fab_open);
//            binding.checkboxArea.setVisibility(View.VISIBLE);
//            isFabOpen = true;
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.categoryButton.setOnClickListener(this::viewShow);
//        radioGroup(view);
//        categoryChanger(view);
        fabEx();
//        scrollChanger();
//        allList_get(view);
    }

    private void fabEx() {
        binding.allListButton.categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true) {
                    if (fabExpanded == true) {
                    }
                    closeSubMenusFab();
                } else {
                    openSubMenuFab();
                }
            }
        });
    }

    private void openSubMenuFab() {
        binding.allListButton.morning.setVisibility(View.VISIBLE);
        binding.allListButton.after.setVisibility(View.VISIBLE);
        binding.allListButton.dawn.setVisibility(View.VISIBLE);
        binding.allListButton.wed.setVisibility(View.VISIBLE);
        binding.allListButton.recent.setVisibility(View.VISIBLE);

        fabExpanded = true;
    }

    private void closeSubMenusFab() {
        binding.allListButton.morning.setVisibility(View.GONE);
        binding.allListButton.after.setVisibility(View.GONE);
        binding.allListButton.dawn.setVisibility(View.GONE);
        binding.allListButton.wed.setVisibility(View.GONE);
        binding.allListButton.recent.setVisibility(View.GONE);

        fabExpanded = false;

    }


//
//
//    /**
//     * 리사이클러뷰 페이징 처리 _ 마지막 position 듣기
//     * 현재 스크롤되고 있는 이벤트에 대해서 받아볼 수 있음
//     */
//    private void scrollChanger() {
//        binding.recyclerViewFeed.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//
//                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(binding.recyclerViewFeed.getLayoutManager());
//                //어댑터가 들고 있는 총 아이템의 갯수
////                int totalItemCount = layoutManager.getItemCount();
//
//                //어댑터가 알고 있는 마지막 아이템 인덱스 반환
//                int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
//
//
//                MLog.d("lastVisible :" + lastVisible);
//
//
//<<<<<<< HEAD
//=======
//                // 9
//>>>>>>> 3da1b363619a00139082b40febf507373d37136c
//                if (lastVisible >= totalItemCount - 3) {
//                    MLog.d("마지막 ");
//
//                    MLog.d("마지막 : " + totalItemCount);
//                    totalItemCount += 10;
//                    progressON("Loading...");
//                    currentPage += 1;
//
//<<<<<<< HEAD
////                    switch (binding.radio.getCheckedRadioButtonId()) {
////                        case R.id.checkBoxMorning: {
////                            MLog.d("어댑터 마지막 인덱스 상태  오전 :" + lastVisible);
////                            MLog.d("어댑터 totalItemCount : 10 :증가 현재 값은 오전" + totalItemCount);
////                            addVideoCategory("오전", currentPage);
//////                           getVideoCategroyId("오전",currentPage);
////
////                            delayedRecyclerNotice(lastVisible);
////
////                            progressOFF();
////                            break;
////                        }
////                        case R.id.checkBoxAfter: {
////                            MLog.d("마지막 인덱스 상태 오후 :" + lastVisible);
////                            MLog.d("totalItemCount : 10 :증가 현재 값은 오후" + totalItemCount);
////                            addVideoCategory("오후", currentPage);
////                            delayedRecyclerNotice(lastVisible);
////
////                            progressOFF();
////                            break;
////                        }
////
////                        case R.id.checkBoxWend: {
////                            MLog.d("마지막 인덱스 상태 수요 :" + lastVisible);
////                            MLog.d("totalItemCount : 10 :증가 현재 값은 수요" + totalItemCount);
////                            addVideoCategory("수요", currentPage);
////                            delayedRecyclerNotice(lastVisible);
////
////                            progressOFF();
////
////                            break;
////                        }
////                        case R.id.checkBoxDawn: {
////                            MLog.d("마지막 인덱스 상태 새벽 :" + lastVisible);
////                            MLog.d("totalItemCount : 10 :증가 현재 값은 새벽" + totalItemCount);
////                            addVideoCategory("새벽", currentPage);
////                            delayedRecyclerNotice(lastVisible);
////
////                            progressOFF();
////
////                            break;
////                        }
////                        default:
////
////                            addVideoAll(currentPage);
////                            delayedRecyclerNotice(lastVisible);
////                            progressOFF();
////
////                            break;
////                    }
//=======
//                    switch (binding.radio.getCheckedRadioButtonId()) {
//                        case R.id.checkBoxMorning: {
//                            MLog.d("어댑터 마지막 인덱스 상태  오전 :" + lastVisible);
//                            MLog.d("어댑터 totalItemCount : 10 :증가 현재 값은 오전" + totalItemCount);
//
//                            MLog.d("어댑터 currentPage :" + currentPage);
//                            addVideoCategory("오전", currentPage);
////                           getVideoCategroyId("오전",currentPage);
//
//                            delayedRecyclerNotice(lastVisible);
//
//                            progressOFF();
//                            break;
//                        }
//                        case R.id.checkBoxAfter: {
//                            MLog.d("어댑터 마지막 인덱스 상태  오전 :" + lastVisible);
//                            MLog.d("어댑터 totalItemCount : 10 :증가 현재 값은 오전" + totalItemCount);
//
//                            MLog.d("어댑터 currentPage :" + currentPage);
//                            addVideoCategory("오후", currentPage);
//                            delayedRecyclerNotice(lastVisible);
//
//                            progressOFF();
//                            break;
//                        }
//
//                        case R.id.checkBoxWend: {
//                            MLog.d("어댑터 마지막 인덱스 상태  오전 :" + lastVisible);
//                            MLog.d("어댑터 totalItemCount : 10 :증가 현재 값은 오전" + totalItemCount);
//
//                            MLog.d("어댑터 currentPage :" + currentPage);
//
//                            addVideoCategory("수요", currentPage);
//                            delayedRecyclerNotice(lastVisible);
//
//                            progressOFF();
//
//                            break;
//                        }
//                        case R.id.checkBoxDawn: {
//                            MLog.d("어댑터 마지막 인덱스 상태  오전 :" + lastVisible);
//                            MLog.d("어댑터 totalItemCount : 10 :증가 현재 값은 오전" + totalItemCount);
//
//                            MLog.d("어댑터 currentPage :" + currentPage);
//
//                            addVideoCategory("새벽", currentPage);
//                            delayedRecyclerNotice(lastVisible);
//
//                            progressOFF();
//
//                            break;
//                        }
//                        default:
//
//
//                            addVideoAll(currentPage);
//                            delayedRecyclerNotice(lastVisible);
//                            progressOFF();
//
//                            break;
//                    }
//>>>>>>> 3da1b363619a00139082b40febf507373d37136c
//                }
//            }
//        });
//    }
//
//    /**
//     * 페이징 포커스를 리사이클러뷰가 알아채기 위해 시간차를 줌
//     *
//     * @param lastVisible 사용자가 포커스한 마지막 position
//     */
//    private void delayedRecyclerNotice(Integer lastVisible) {
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                binding.recyclerViewFeed.getLayoutManager().scrollToPosition(lastVisible);
//
//            }
//        }, 500);
//    }
//
//    /**
//     * 카테고리 메뉴에서 사용자가 전체보기 눌렀을때 전체 유튜브 리스트를 불러오기
//     *
//     * @param view
//     */
//    private void allList_get(View view) {
////        binding.allListButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                getVideoId(currentPage);
////                binding.radio.clearCheck();
//////                binding.allListButton.setVisibility(View.GONE);
////                binding.checkboxArea.setVisibility(View.VISIBLE);
////            }
////        });
//    }
//
//
//    /**
//     * 사용자 카테고리 체크 체인져 _ 해당 카테고리 별로 유튜브 카테고리 검색
//     *
//     * @param view
//     */
//<<<<<<< HEAD
////    private void categoryChanger(View view) {
////        binding.radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(RadioGroup radioGroup, int ch) {
////                switch (ch) {
////                    case R.id.checkBoxMorning: {
////                        //탭별 카테고리 키워드
////                        String categoryId = "오전";
////                        MLog.d("사용자 changer 오전");
////                        //탭별 페이징 넘버 기본값
////                        mRecyclerAdapter.clearItem();
////                        currentPage = 0;
////                        //페이징 라스트 경우 초기화 값
////                        totalItemCount = 10;
////                        // 스크롤 라스트 감지
////
//////                        addVideoCategory(categoryId, currentPage);
////                        //첫 탭시 로드
////                         getVideoCategroyId(categoryId, currentPage);
////
////                        //전체 보기 비디오 보이기
//////                        binding.allListButton.setVisibility(View.VISIBLE);
////                        //카테고리 자동 숨기기
////                        hideCategoryArea();
////
////                        break;
////                    }
////
////                    case R.id.checkBoxAfter: {
////                        String categoryId = "오후";
////                        MLog.d("사용자 changer 오후");
////                        mRecyclerAdapter.clearItem();
////
////                        currentPage = 0;
////                        totalItemCount = 10;
////
////
//////                        addVideoCategory(categoryId,currentPage);
////
//=======
//    private void categoryChanger(View view) {
//
//        binding.radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int ch) {
//                switch (ch) {
//                    case R.id.checkBoxMorning: {
//                        //탭별 카테고리 키워드
//                        String categoryId = "오전";
//                        MLog.d("사용자 changer 오전");
//                        //탭별 페이징 넘버 기본값
//                        mRecyclerAdapter.clearItem();
//                        currentPage = 0;
//                        //페이징 라스트 경우 초기화 값
//                        totalItemCount = 10;
//                        // 스크롤 라스트 감지
//
////                        addVideoCategory(categoryId, currentPage);
//                        //첫 탭시 로드
//                        getVideoCategroyId(categoryId, currentPage);
//
//                        //전체 보기 비디오 보이기
//                        binding.allListButton.setVisibility(View.VISIBLE);
//                        //카테고리 자동 숨기기
//                        hideCategoryArea();
//
//                        break;
//                    }
//
//                    case R.id.checkBoxAfter: {
//                        String categoryId = "오후";
//                        MLog.d("사용자 changer 오후");
//                        mRecyclerAdapter.clearItem();
//
//                        currentPage = 0;
//                        totalItemCount = 10;
//
//
////                        addVideoCategory(categoryId,currentPage);
//
//                        getVideoCategroyId(categoryId, currentPage);
//                        binding.allListButton.setVisibility(View.VISIBLE);
//                        hideCategoryArea();
//
//                        break;
//                    }
//
//                    case R.id.checkBoxWend: {
//                        String categoryId = "수요";
//                        MLog.d("사용자 changer 수요");
//                        mRecyclerAdapter.clearItem();
//
//                        currentPage = 0;
//                        totalItemCount = 10;
//
////                        addVideoCategory(categoryId,currentPage);
//                        getVideoCategroyId(categoryId, currentPage);
//                        binding.allListButton.setVisibility(View.VISIBLE);
//                        hideCategoryArea();
//
//                        break;
//                    }
//
//                    case R.id.checkBoxDawn: {
//                        String categoryId = "새벽";
//                        MLog.d("사용자 changer 새벽");
//                        mRecyclerAdapter.clearItem();
//
//                        currentPage = 0;
//                        totalItemCount = 10;
//                        getVideoCategroyId(categoryId, currentPage);
//>>>>>>> 3da1b363619a00139082b40febf507373d37136c
////                        getVideoCategroyId(categoryId, currentPage);
////                        binding.allListButton.setVisibility(View.VISIBLE);
////                        hideCategoryArea();
////
////                        break;
////                    }
////
////                    case R.id.checkBoxWend: {
////                        String categoryId = "수요";
////                        MLog.d("사용자 changer 수요");
////
////                        currentPage = 0;
////                        totalItemCount = 10;
////
//////                        addVideoCategory(categoryId,currentPage);
////                        getVideoCategroyId(categoryId, currentPage);
////                        binding.allListButton.setVisibility(View.VISIBLE);
////                        hideCategoryArea();
////
////                        break;
////                    }
////
////                    case R.id.checkBoxDawn: {
////                        String categoryId = "새벽";
////                        MLog.d("사용자 changer 새벽");
////
////                        currentPage = 0;
////                        totalItemCount = 10;
////                        getVideoCategroyId(categoryId, currentPage);
//////                        getVideoCategroyId(categoryId, currentPage);
////                        binding.allListButton.setVisibility(View.VISIBLE);
////
////                        hideCategoryArea();
////                        break;
////                    }
////
////
////                }
////            }
////        });
////
////    }
//
//
//    /**
//     * 카테고리 숨기기 딜레이
//     */
//    private void hideCategoryArea() {
//
////        new Handler().postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                binding.checkboxArea.setVisibility(View.GONE);
////                isFabOpen = false;
////
////            }
////        }, 1000);
//
//    }
//
//
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 카테고리 오픈 클로즈 애니메이션 적용
//        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
//        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);

        //리사이클러뷰
        mRecyclerAdapter = new YoutubeRecyclerAdapter();

        /**
         * 유튜브 게시
         */
        getVideoId(currentPage);


        //어댑터에서 온클릭리스너의 상황을 듣고 있는 리스너
        recyclerClickListener(youTubePlayer);

    }

    //
//
//    /**
//     * 리사이클러뷰에서 아이템 클릭이 일어났을때 듣고 있는 리스너
//     *
//     * @param youTubePlayer
//     */
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
//                changingVideo(playId, youTubePlayer);
                //realm 저장
//                saveVideo(title, imageUrl, playId);
            }
        });

    }

    //
//
//    //클릭하면 동영상 바꾸기
//    private void changingVideo(String video, final YouTubePlayer youTubePlayer) {
//        MLog.d("changing video");
//
//        MLog.d("changing video"+ video);
////        binding.youtubePlayerView.setEnableAutomaticInitialization();to
//        if (video != null && youTubePlayer != null) {
////            recyclerClickListener(youTubePlayer);
//            youTubePlayer.cueVideo(video, 0f);
//
//        }
//
//
//    }
//
//
//    /**
//     * 최근 봤던 비디오 리얼엠에 저장하기
//     *
//     * @param title   유튜브 제목
//     * @param image   유튜브 썸네일
//     * @param videoId 유튜브 비디오 아이디
//     */
//    private void saveVideo(String title, String image, String videoId) {
//        final Realm realm = Realm.getDefaultInstance();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                UserVideo userVideo = realm.createObject(UserVideo.class);
//                userVideo.setVideoId(videoId);
//                userVideo.setImage_Url(image);
//                userVideo.setTitle(title);
//                MLog.d("saveVideo" + videoId + image + title);
//            }
//        });
//
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
//    /**
//     * 카테고리별 유튜브 영상 긁어오기
//     *
//     * @param categoryId  = 카테고리 키워드
//     * @param currentPage = 카테고리 페이지 Number
//     */
//
//    private void getVideoCategroyId(String categoryId, Integer currentPage) {
//        unCodeVideoConfig = new UnCodeVideoConfig();
//        unCodeVideoConfig.unCodeVideoCategoryList(categoryId, currentPage, new ResponseCallback<UnCodeVideoModel>() {
//            @Override
//            public void response(UnCodeVideoModel response) {
//                MLog.d("youtube Model Ok");
//
//
//                if (response != null) {
//
//                    VIDEO_LIST_ITEMS.addAll(response.data);
//                    mRecyclerAdapter.setItems(VIDEO_LIST_ITEMS);
////                    mRecyclerAdapter.setItems(response.data);
//
//                    for (int a = 0; a > response.data.size(); a++) {
//                        if (a == 0) {
//                            secondVideo = response.data.get(a).videoId;
//                            MLog.d("youtube Model Ok!! category ID : " + secondVideo);
//
//                        }
//                    }
//                    initYouTubePlayerView(secondVideo);
//                    setYoutubeData();
//                }
//
//            }
//        });
//
//    }
//
//    /**
//     * 전체 보기
//     */
    private void getVideoId(Integer currentPage) {
        unCodeVideoConfig = new UnCodeVideoConfig();
        unCodeVideoConfig.unCodeVideoList(currentPage, new ResponseCallback<UnCodeVideoModel>() {
            @Override
            public void response(UnCodeVideoModel response) {
                MLog.d("youtube Model Ok");

                if (response != null) {
                    mRecyclerAdapter.setItems(unCodeModelList);

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
//                mRecyclerAdapter.notifyDataSetChanged();

            }
        });


    }

    //
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
            Toast.makeText(getContext(), "network 상태 확인 요망", Toast.LENGTH_LONG).show();
        }

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
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        binding.youtubePlayerView.release();
//    }
//}
}