package kr.uncode.lifetreechurch.fm_news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kr.uncode.lifetreechurch.Config.NewMemConfig;
import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmNewmemberBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class NewMemberFragment extends BaseFragment {

    private List<NewMemberModel.Data> memberList;
    private LinearLayoutManager mGridLayoutManager;
    FmNewmemberBinding binding;

    private List<String> aa;

    private NewMemberModel.Data getMem;
    private NewMemberRecyclerAdapter mRecyclerAdapter;

    public NewMemConfig newMemConfig;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecyclerAdapter = new NewMemberRecyclerAdapter();
        getNewMember();


    }

    private void setRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.newMemberRecycler.setLayoutManager(layoutManager);
        binding.newMemberRecycler.setAdapter(mRecyclerAdapter);
    }

    private void getNewMember() {
        newMemConfig= new NewMemConfig();
        newMemConfig.memberList(new ResponseCallback<NewMemberModel>() {
            @Override
            public void response(NewMemberModel response) {
                MLog.d(response.data.toString());


                if (response != null) {
                    for (int i=0; i<response.data.size(); i++) {
                        getMem =  response.data.get(i);
                        mRecyclerAdapter.setItems(response.data);

                    }
                }
                setRecycler();
                MLog.d("list " +getMem.title);
//                setRecycler();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_newmember, container, false);
        return binding.getRoot();
    }

}