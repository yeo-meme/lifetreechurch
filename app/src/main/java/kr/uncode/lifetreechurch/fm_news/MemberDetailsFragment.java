package kr.uncode.lifetreechurch.fm_news;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmMemberdetailsBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class MemberDetailsFragment extends BaseFragment {

    private String imgUrl;
    FmMemberdetailsBinding binding;

    public MemberDetailsFragment(String url) {
        imgUrl = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_memberdetails, container, false);
        showImg(imgUrl);

        return binding.getRoot();
    }



    private void showImg(String url) {
        if (url != null) {
            Glide.with(getActivity())
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                             progressOFF();
                            return false;
                        }
                    })
//                        .apply(new RequestOptions().override(displayMetrics.widthPixels - 36, 200))
                    .into(binding.memberDetailsImg);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.d(imgUrl);
    }
}