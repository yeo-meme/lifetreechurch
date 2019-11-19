package kr.uncode.lifetreechurch.lt_main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.ActivityMainBinding;
import kr.uncode.lifetreechurch.init_ft.InitFragment;

public class MainActivity extends BaseActivity {

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


    private void initPageLoad() {
       replaceFragment(new InitFragment(), true);
    }

    public void setTollbarTitle(String title) {
    }
}
