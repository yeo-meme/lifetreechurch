package kr.uncode.lifetreechurch.fm_notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kr.uncode.lifetreechurch.Config.NoticeConfig;
import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.Model.NoticeModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmNoticeBinding;
import kr.uncode.lifetreechurch.fm_news.MemberDetailsFragment;
import kr.uncode.lifetreechurch.utils.MLog;

public class NoticeFragment extends BaseFragment {

    private LinearLayoutManager linearLayoutManager;
    private NoticeConfig noticeConfig;
    FmNoticeBinding binding;
    private NoticeAdapter noticeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_notice,container,false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noticeAdapter = new NoticeAdapter();
        getContent();

        noticeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {
               NoticeModel.Data ee = (NoticeModel.Data)aa.get(position);
                MLog.d("notice click"+ee);
                replaceFragment(new NoticeDetailsFragment(ee),true);
            }
        });
    }

    private void getContent() {
        noticeConfig = new NoticeConfig();
        noticeConfig.noticeList(new ResponseCallback<NoticeModel>() {
            @Override
            public void response(NoticeModel response) {
                MLog.d("hi notice" + response);
                    noticeAdapter.setData(response.data);
                setAdapter();
            }
        });

    }

    private void setAdapter() {
        linearLayoutManager = new LinearLayoutManager(getContext());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.noticeFeed.setLayoutManager(linearLayoutManager);
        binding.noticeFeed.setAdapter(noticeAdapter);
    }
}
