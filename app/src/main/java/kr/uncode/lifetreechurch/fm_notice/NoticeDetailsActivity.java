package kr.uncode.lifetreechurch.fm_notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NoticeModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmNoticedetailsBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-18
 */
public class NoticeDetailsActivity extends BaseActivity {

    private NoticeAdapter noticeAdapter;

    FmNoticedetailsBinding binding;
    private static String NOTICE_DETAILS_CODE = "MEME";
    private static String NOTICE_DETAILS_DATE = "JIN";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fm_noticedetails);

        MLog.d("hello notice details activity");



        Intent intent = getIntent();
        String content = intent.getExtras().getString(NOTICE_DETAILS_CODE);
        String date = intent.getExtras().getString(NOTICE_DETAILS_DATE);
        binding.noticeContent.setText(content);
        binding.noticeDate.setText(date);
        binding.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
