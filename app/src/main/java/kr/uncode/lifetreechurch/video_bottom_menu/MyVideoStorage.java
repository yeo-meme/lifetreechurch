package kr.uncode.lifetreechurch.video_bottom_menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private SharedPreferences pref;
    private String storageUrl = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_myvideostorage, container, false);


        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPref",Context.MODE_PRIVATE);


        String data = sharedPreferences.getString("data","");
        MLog.d("dd :"+data);

    }

    public void listener() {
//        savePreferences();


//        binding.myStorage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savePreferences();
//            }
//        });

    }

    private void savePreferences() {



//        SharedPreferences.Editor editor= pref.edit();
//        getPreferences();
    }

    private void getPreferences() {
        pref = getActivity().getSharedPreferences("hihihi",Context.MODE_PRIVATE);

        MLog.d("shared" +pref);
    }
}
