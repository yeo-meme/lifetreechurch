package kr.uncode.lifetreechurch.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BaseActivity extends AppCompatActivity {
    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void clearAllBackStackFragment() {
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }


    public void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerViewId, fragment);
        if (addToBackStack) ft.addToBackStack(null);
        ft.commit();
    }


}
