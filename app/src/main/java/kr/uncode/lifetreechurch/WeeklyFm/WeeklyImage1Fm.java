package kr.uncode.lifetreechurch.WeeklyFm;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmWeekly1Binding;
import kr.uncode.lifetreechurch.utils.MLog;

public class WeeklyImage1Fm extends BaseFragment {
    private static final String IMAGE_URL = "WEEKLY_IMAGE";

    private String image_url;

    FmWeekly1Binding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_weekly1, container, false);
//        binding = FragmentMainBinding.inflate(inflater, container, false);
        backKeyHideController(true);
//        closeController(true);
        toolbarController(false);
        binding.closeBtn.setVisibility(View.VISIBLE);

        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            image_url = getArguments().getString(IMAGE_URL); // 전달한 key 값
            MLog.d(image_url);
        }
        getWeeklyImg();
    }

    private void getWeeklyImg() {
        if (image_url != null) {
            Glide.with(binding.getRoot())
                    .load(image_url)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(5000, 5000)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            MLog.d("Image loading failed");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           progressOFF();
                            return false;
                        }
                    })
                    .into(binding.weeklyImage1);

        }

    }
}
