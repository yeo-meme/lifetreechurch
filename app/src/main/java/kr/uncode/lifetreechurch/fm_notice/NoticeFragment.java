package kr.uncode.lifetreechurch.fm_notice;

import android.content.Intent;
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
    private static String NOTICE_DETAILS_CODE = "MEME";
    private static String NOTICE_DETAILS_DATE = "JIN";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_notice, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noticeAdapter = new NoticeAdapter();
        getContent();
        toolbarController(true);


//        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().popBackStack();
//
//            }
//        });
        noticeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {
                NoticeModel.Data ee = (NoticeModel.Data) aa.get(position);
                MLog.d("notice click" + ee);


                Intent intent = new Intent(getActivity(), NoticeDetailsActivity.class);
                intent.putExtra(NOTICE_DETAILS_CODE,ee.content);
                intent.putExtra(NOTICE_DETAILS_DATE,ee.date);
                startActivity(intent);
//                replaceFragment(new NoticeDetailsFragment(ee),true);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.backKey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().popBackStack();
//            }
//        });
//    }
    }
    private void setAdapter() {
        linearLayoutManager = new LinearLayoutManager(getContext());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.noticeFeed.setLayoutManager(linearLayoutManager);
        binding.noticeFeed.setAdapter(noticeAdapter);
    }

}
