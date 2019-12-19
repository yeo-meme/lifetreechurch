package kr.uncode.lifetreechurch.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.lt_main.MainActivity;
import kr.uncode.lifetreechurch.utils.MPref;

/**
 * Created by yeomeme@gmail.com 2019-11-18
 */
public class BaseFragment extends Fragment {
    private boolean isDialogShow = false;

    Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
        }
    }


    /**
     * 메인 툴바 타이틀 변경 : String
     */
    public void setTitle(String title) {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            MainActivity prarentActivity = (MainActivity) activity;
            prarentActivity.setTollbarTitle(title);
        }
    }


    /**
     *메인 툴바 타이틀 변경 : Resource
     */
    public void setTitle(int resId) {
        setTitle(getString(resId));
    }


    /**
     * 메인 fragment 변경 메서드
     */

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).replaceFragment(R.id.main_container,fragment,addToBackStack);
        }
    }


    /**
     * 액티비티 시작 메서드
     *
     * @param cls  클래스
     * @param currentActivityFinish  <br>
     *                               TRUE : 현재 액티비티 종료 <br>
     *                               FALSE : 현재 액티비티 종료안함.
     */
    public void startActivity(Class cls, boolean currentActivityFinish) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.startActivity(new Intent(activity,cls));
            if (currentActivityFinish) activity.finish();
        }
    }


    public void toolbarMenuButtonController(boolean isShowMenuButton) {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            MainActivity parentActivity = (MainActivity) activity;
//            parentActivity.toolbarMenuButtonController(isShowMenuButton);
        }
    }

    public void progressON() {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            BaseApplication.getInstance().progressON(activity,null);
        }}

    public void progressON(String message) {

        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            BaseApplication.getInstance().progressON(activity,message);
        }


    }

    public void progressOFF() {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            BaseApplication.getInstance().progressOFF();
        }

    }

}
