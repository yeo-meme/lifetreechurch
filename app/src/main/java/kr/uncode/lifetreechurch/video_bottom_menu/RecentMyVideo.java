package kr.uncode.lifetreechurch.video_bottom_menu;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmRecentmyvideoBinding;
import kr.uncode.lifetreechurch.utils.MLog;

import static android.content.Context.MODE_PRIVATE;

public class RecentMyVideo extends BaseFragment {

    FmRecentmyvideoBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_recentmyvideo, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", MODE_PRIVATE);
        String nVal = pref.getString("data","" );
        MLog.d("getPref :" + nVal);

    }
}
