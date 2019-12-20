package kr.uncode.lifetreechurch.fm_happy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kr.uncode.lifetreechurch.Config.HappyConfig;
import kr.uncode.lifetreechurch.Model.HappyModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmHappycolumnBinding;
import kr.uncode.lifetreechurch.databinding.FmHappyfeedBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public class HappyColumnFragment extends BaseFragment {


    private static final String HAPPY_TITLE = "MEME";
    private static final String HAPPY_CON ="JIN";
    private static final String HAPPY_URL ="JINMEME";
    private HappyAdapter happyAdapter;
    private HappyConfig happyConfig;
    FmHappyfeedBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        happyAdapter = new HappyAdapter();
        happyAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onListItemClick(List aa, int position) {

                HappyModel.Data ee = (HappyModel.Data) aa.get(position);

                String title = ee.title;
                String contents = ee.content;
                String url = ee.url;


                Intent intent = new Intent(getActivity(),HappyActivity.class);
                intent.putExtra(HAPPY_TITLE,title);
                intent.putExtra(HAPPY_CON,contents);
                intent.putExtra(HAPPY_URL,url);
                startActivity(intent);
            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater,R.layout.fm_happyfeed,container,false);
        getHappyColumn();
        return binding.getRoot();
    }

    private void getHappyColumn() {
        happyConfig = new HappyConfig();
        happyConfig.happyList(new ResponseCallback<HappyModel>() {
            @Override
            public void response(HappyModel response) {
                MLog.d("happy response oK");

                for (int i=0; i<response.data.size(); i++) {
                    HappyModel.Data happy = response.data.get(i);
                    String happyTitle = happy.title;
                    String happyContent = happy.content;

                    MLog.d("happy get" + happyTitle);
                    MLog.d("happy get" + happyContent);
                }
                happyAdapter.setItems(response.data);
                setRecycler();
            }
        });
    }


    private void setRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.happyFeed.setLayoutManager(layoutManager);
        binding.happyFeed.setAdapter(happyAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.happyCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),HappyActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
