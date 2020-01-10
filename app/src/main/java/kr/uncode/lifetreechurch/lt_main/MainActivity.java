package kr.uncode.lifetreechurch.lt_main;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.databinding.ActivityMainBinding;
import kr.uncode.lifetreechurch.fm_video.VideoListFragment;
import kr.uncode.lifetreechurch.init_ft.InitFragment;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;
import kr.uncode.lifetreechurch.video_bottom_menu.RecentAdapter;

public class MainActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar));
//        }

        backPlay();
        initPageLoad();
    }

    /**
     * 툴바를 눌렀을때 뒤로가 버튼
     */
    public void backPlay() {
        binding.backKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MLog.d("main back click");
                getSupportFragmentManager().popBackStack();
            }
        });
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        replaceFragment(R.id.main_container, fragment, addToBackStack);
    }


    public void setTollbarTitle(String title) {
    }


    private void initPageLoad() {
        replaceFragment(new InitFragment(), false);
    }


    /**
     * 최근본목록 툴바 모두 삭제 버튼 불러오기
     * @param isShowMenuButton true 불러오기
     */
    public void toolbarMenuButtonController(boolean isShowMenuButton) {

        if (isShowMenuButton) {
            binding.mainRightButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 비디오리스트에서 상단에 최근본 목록 가기 위해 메뉴 버튼 생성
     * @param isShow true일때
     */
    public void recentVideoListGo(boolean isShow) {
        if (isShow) {
            binding.recentGoButton.setVisibility(View.VISIBLE);
        } else {
            binding.recentGoButton.setVisibility(View.GONE);
        }
    }

    /**
     * 메뉴안에 딜리티 키보여주기
     * @param v
     */
    public void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.mene_storage, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();
    }

//    public void videoListTopMenuShow(boolean isShow) {
//
//        if (isShow) {
//            binding.videoMenu.setVisibility(View.VISIBLE);
//            binding.toolbar.setVisibility(View.GONE);
//        }
//    }

    /**
     * 백키 숨기기
     * @param isShow true 일때
     */
    public void backKeyHide(boolean isShow) {
        if (isShow) {
            binding.backKey.setVisibility(View.GONE);
        }
    }

    /**
     * 백키 보여주기
     * @param isShow true 일때
     */
    public void backKeyShow(boolean isShow) {
        if (isShow) {
            binding.backKey.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:

                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        final RealmResults<UserVideo> keyresult = realm.where(UserVideo.class).findAll();
                        keyresult.deleteAllFromRealm();
                        RecentAdapter recentAdapter = new RecentAdapter();
                        recentAdapter.notifyDataSetChanged();


                    }
                });
                replaceFragment(new MyVideoStorage(), false);

                break;

        }
        return false;
    }
}
