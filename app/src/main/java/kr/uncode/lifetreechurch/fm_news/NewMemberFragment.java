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
import kr.uncode.lifetreechurch.databinding.FmNewmemberBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class NewMemberFragment extends BaseFragment {

    FmNewmemberBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_newmember,container,false);
        MLog.d("new member in");
        return binding.getRoot();
    }
}
