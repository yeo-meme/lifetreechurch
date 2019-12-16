package kr.uncode.lifetreechurch.init_ft;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.WeeklyFm.Weekly_Fm;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmMainBinding;
import kr.uncode.lifetreechurch.fm_news.NewMiddleFragment;
import kr.uncode.lifetreechurch.fm_video.VideoFragment;

public class InitFragment extends BaseFragment {
    private ProgressDialog progressDialog;

    FmMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_main, container, false);
//        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuListener();
    }

    private void menuListener() {
        binding.videoMenu.setOnClickListener(this::videoClick);
        binding.weekly.setOnClickListener(this::weeklyClick);
        binding.menuColumn.setOnClickListener(this::videoClick);
        binding.newsMenu.setOnClickListener(this::news);

        binding.introduceBtn.setOnClickListener(this::videoClick);
        binding.introduceChurchBtn.setOnClickListener(this::videoClick);
    }


    private void news(View view) {
        replaceFragment(new NewMiddleFragment(),true);

    }
    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Coming Soon");
        builder.setMessage("업데이트 예정입니다~♡");
        builder.setPositiveButton("네", null);
        builder.create().show();
    }

    private void videoClick(View view) {
//        alert();

        replaceFragment(new VideoFragment(),true);
    }

    private void weeklyClick(View view) {
//        alert();

        replaceFragment(new Weekly_Fm(), true);
//        loading(view);
//        binding.progress.setVisibility(View.VISIBLE);
    }

//    public void loading(View view) {
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        progressDialog = new ProgressDialog(getContext());
//                        progressDialog.setIndeterminate(true);
//                        progressDialog.setMessage("잠시만 기다려 주세요");
//                        progressDialog.show();
//                    }
//                }, 0);
//
//    }
}
