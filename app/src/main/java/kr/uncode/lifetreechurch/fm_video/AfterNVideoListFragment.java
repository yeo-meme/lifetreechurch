package kr.uncode.lifetreechurch.fm_video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmAfternoonvideolistBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class AfterNVideoListFragment extends BaseFragment {


    private AfterNoonAdapter afterNoonAdapter;
    FmAfternoonvideolistBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater, R.layout.fm_afternoonvideolist,container,false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        afterNoonAdapter = new AfterNoonAdapter();
    }


    private void recyclerClickListener(final YouTubePlayer youTubePlayer) {
    }
}
