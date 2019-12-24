package kr.uncode.lifetreechurch.WeeklyFm;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kr.uncode.lifetreechurch.Config.UnCodeWeeklyConfig;
import kr.uncode.lifetreechurch.Model.BlogWeekly;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmWeeklyBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class Weekly_Fm extends BaseFragment {

    private ProgressDialog progressDialog;


    private static final String IMAGE_URL = "WEEKLY_IMAGE";
    private String weekly1Url;
    private String weekly2Url;
    private UnCodeWeeklyConfig jinuWeeklyConfig;

    FmWeeklyBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_weekly, container, false);
        ClickCheck();
        progressON("Loading...");
        return binding.getRoot();
    }

    private void ClickCheck() {
        binding.weeklyImage1.setOnClickListener(this::weekly1DetailView);
        binding.weeklyImage2.setOnClickListener(this::weekly2DetailView);
    }


    private void weekly1DetailView(View view) {

        progressON("Loading...");

        MLog.d();
        Fragment fragment = new WeeklyImage1Fm(); // Fragment 생성
        Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
        bundle.putString(IMAGE_URL, weekly1Url); // key , value
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fmt = fm.beginTransaction();
        fragment.setArguments(bundle);
        fmt.replace(R.id.main_container, fragment).addToBackStack(null).commit();
    }


    private void weekly2DetailView(View view) {
        progressON("Loading...");

        MLog.d();
        Fragment fragment = new WeeklyImage2Fm(); // Fragment 생성
        Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
        bundle.putString(IMAGE_URL, weekly2Url); // key , value
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fmt = fm.beginTransaction();
        fragment.setArguments(bundle);
        fmt.replace(R.id.main_container, fragment).addToBackStack(null).commit();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        progressDialog = new ProgressDialog(getContext());
//        binding.progress.setVisibility(View.GONE);
        getJinNu(view);
    }


    private void startProgress() {

        progressON("Loading...");

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressOFF();
//            }
//        }, 3500);

    }

    private void getJinNu(View view) {
        jinuWeeklyConfig = new UnCodeWeeklyConfig();
        jinuWeeklyConfig.jiNuList(new ResponseCallback<BlogWeekly>() {
            @Override
            public void response(BlogWeekly response) {
                if (response != null) {
                    MLog.d("jinuAPI ok!!");
                    for (int i = 0; i < response.data.size(); i++) {
                        BlogWeekly.Data blogWeekly = response.data.get(i);
                        weekly1Url = blogWeekly.imgurl1;
                        weekly2Url = blogWeekly.imgurl2;
                        MLog.d("data get ImageUrl : " + weekly1Url);
                        MLog.d("data get ImageUrl : " + weekly2Url);
                    }
                    showWeekly(view);

                }
            }
        });
    }

    private void showWeekly(View view) {


        if (weekly1Url != null) {

            Glide.with(binding.getRoot())
                    .load(weekly1Url)
                    .override(3000, 3000)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            MLog.d("Glide failed");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                         MLog.d("glide sucess");
                            progressOFF();
                            return false;
                        }
                    })
                    .into(binding.weeklyImage1);
        }

        if (weekly2Url != null) {
            Glide.with(binding.getRoot())
                    .load(weekly2Url)
                    .override(3000, 3000)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.weeklyImage2);

        }

    }
}
