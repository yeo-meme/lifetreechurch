package kr.uncode.lifetreechurch.fm_notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NoticeModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmMemberdetailsBinding;
import kr.uncode.lifetreechurch.databinding.FmNoticeBinding;
import kr.uncode.lifetreechurch.databinding.FmNoticedetailsBinding;

public class NoticeDetailsFragment extends BaseFragment {

    FmNoticedetailsBinding binding;
    private NoticeModel.Data noticeData;

    public NoticeDetailsFragment(NoticeModel.Data data) {
        noticeData = data;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_noticedetails, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.noticeContent.setText(noticeData.content);
        binding.noticeDate.setText(noticeData.date);
    }
}
