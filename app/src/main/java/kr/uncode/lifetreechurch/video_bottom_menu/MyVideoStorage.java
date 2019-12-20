package kr.uncode.lifetreechurch.video_bottom_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.util.List;

import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.YoutubeRecyclerAdapter;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmMyvideostorageBinding;
import kr.uncode.lifetreechurch.fm_video.YoutubePlayerActivity;
import kr.uncode.lifetreechurch.utils.MLog;

public class MyVideoStorage extends BaseFragment {

    private YoutubeRecyclerAdapter mRecyclerAdapter;
    FmMyvideostorageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_myvideostorage, container, false);
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {

                MLog.d("나도 들어");
//                YoutubeResponse.Items items = (YoutubeResponse.Items) aa.get(position);
//                String playId = items.id.videoId;
//
//                Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
//                intent.putExtra(YOUTUBE, playId);
//                startActivity(intent);
            }
        });
    }
}
