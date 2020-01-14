package kr.uncode.lifetreechurch.fm_news;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.databinding.FmMemberdetailsBinding;
import kr.uncode.lifetreechurch.lt_main.MainActivity;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-18
 */
public class MemberDetailsActivity extends BaseActivity {

    private String imgUrl = null;
    FmMemberdetailsBinding binding;

    private static String DETAILS_IMAGE_CODE= "MEME";

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fm_memberdetails);
        MLog.d("hi member Activity");

        backPlay();
        Intent intent = getIntent();
        imgUrl= intent.getExtras().getString(DETAILS_IMAGE_CODE);
        setImage();
    }

    public void backPlay() {
        binding.backKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MLog.d("main back click");
                finish();
            }
        });
    }


    private void setImage() {
        if (imgUrl != null) {
            Glide.with(binding.getRoot())
                    .load(imgUrl)
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .override(5000, 5000)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .placeholder(R.drawable.logo)
                    .thumbnail(0.1f)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            BaseApplication.getInstance().progressOFF();
                            return false;
                        }
                    })
                    .into(binding.memberDetailsImg);
        }
    }
}
