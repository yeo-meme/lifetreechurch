package kr.uncode.lifetreechurch.fm_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmNewsBinding;
import kr.uncode.lifetreechurch.databinding.FmNewsmiddleBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class NewsFragment extends BaseFragment {

    FmNewsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = DataBindingUtil.inflate(inflater,R.layout.fm_news,container,false);
        MLog.d("new in");

        return binding.getRoot();
    }
}
