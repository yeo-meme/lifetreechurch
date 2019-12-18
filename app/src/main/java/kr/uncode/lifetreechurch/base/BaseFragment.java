package kr.uncode.lifetreechurch.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

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

    public static class YoutubeFm extends YouTubePlayerSupportFragment {

        public YoutubeFm() {}

        public static YoutubeFm newInstance(String url) {
            YoutubeFm f = new YoutubeFm();

            Bundle b = new Bundle();
            b.putString("url",url);
            f.init();

            return f;
        }

        private void init() {

            initialize(MPref.DEVELOPE_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                    if (!wasRestored) {
                        youTubePlayer.cueVideo("url");
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
        }
    }
}
