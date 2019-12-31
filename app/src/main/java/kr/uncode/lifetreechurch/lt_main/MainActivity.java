package kr.uncode.lifetreechurch.lt_main;

import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.ActivityMainBinding;
import kr.uncode.lifetreechurch.init_ft.InitFragment;
import kr.uncode.lifetreechurch.video_bottom_menu.MyVideoStorage;
import kr.uncode.lifetreechurch.video_bottom_menu.RecentAdapter;

public class MainActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        initPageLoad();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        replaceFragment(R.id.main_container, fragment, addToBackStack);
    }


    public void setTollbarTitle(String title) {
    }


    private void initPageLoad() {
        replaceFragment(new InitFragment(), false);
    }

    public void toolbarMenuButtonController(boolean isShowMenuButton) {

        if (isShowMenuButton) {
            binding.mainRightButton.setVisibility(View.VISIBLE);
        }


    }


    public void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.mene_storage, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();
    }

    public void videoListTopMenuShow(boolean isShow) {

        if (isShow) {
            binding.videoMenu.setVisibility(View.VISIBLE);
            binding.toolbar.setVisibility(View.GONE);
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
