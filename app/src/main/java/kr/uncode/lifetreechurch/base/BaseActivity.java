package kr.uncode.lifetreechurch.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import kr.uncode.lifetreechurch.fm_news.NewMemberFragment;
import kr.uncode.lifetreechurch.utils.MLog;

public class BaseActivity extends AppCompatActivity {
    private FragmentManager fm;

    private OnBackPressedListener mBackListener;

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MLog.d("onback Pressed");

    }

    public interface OnBackPressedListener {
        public void onBack();
    }

    public void progressON(String message) {

        Activity activity = this;
        if (activity instanceof BaseActivity) {
            BaseApplication.getInstance().progressON(activity,message);
        }


    }

    public void progressOFF() {
        Activity activity = this;
        if (activity instanceof BaseActivity) {
            BaseApplication.getInstance().progressOFF();
        }

    }
}
