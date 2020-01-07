package kr.uncode.lifetreechurch.fm_video;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.uncode.lifetreechurch.BaseViewHolder;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    ItemYoutubeListBinding binding;
//    private List<UnCodeVideoModel.Data> UtubeBasket;

    private List<UnCodeVideoModel.Data> list = new ArrayList<>();
    DisplayMetrics displayMetrics = new DisplayMetrics();

    private OnItemClickListener mListener = null;

    public YoutubeRecyclerAdapter() {
    }

    public void clearItem() {
        this.list.clear();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new YoutubeViewHolder(binding,list,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setItems(List<UnCodeVideoModel.Data> youtubeVideos) {
        list = youtubeVideos;
        MLog.d("recyclerAdapter setData method :" + list);
        notifyDataSetChanged();
    }

    public boolean addItem(UnCodeVideoModel.Data youtube) {
        return list.add(youtube);
//        notifyDataSetChanged();'


    }




}

