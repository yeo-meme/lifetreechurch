package kr.uncode.lifetreechurch.lt_main;

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

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.ActivityMainBinding;
import kr.uncode.lifetreechurch.init_ft.InitFragment;

public class MainActivity extends BaseActivity implements PopupMenu.OnMenuItemClickListener {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        initPageLoad();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        replaceFragment(R.id.main_container, fragment, addToBackStack);
    }


    public void setTollbarTitle(String title) {}
    private void initPageLoad() {
       replaceFragment(new InitFragment(), false);
    }

    public void toolbarMenuButtonController(boolean isShowMenuButton) {

        if (isShowMenuButton) {binding.mainRightButton.setVisibility(View.VISIBLE);}

//        binding.mainRightButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu popupMenu = new PopupMenu(getBaseContext(),view);
//                MenuInflater inflater = popupMenu.getMenuInflater();
//                Menu menu = popupMenu.getMenu();
//                inflater.inflate(R.menu.mene_storage,menu);
//
//
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        switch (menuItem.getItemId()) {
//
//                            case R.id.delete :
//                                Toast.makeText(getApplicationContext(),"클릭",Toast.LENGTH_LONG).show();
//                                break;
//                        }
//                        return false;
//                    }
//                });
//            }
//        });
    }


    public void showPopup(View v) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.mene_storage,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.show();

    }



    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:
                Toast.makeText(this,"기록삭제",Toast.LENGTH_LONG).show();
                break;

        }
        return false;
    }
}
