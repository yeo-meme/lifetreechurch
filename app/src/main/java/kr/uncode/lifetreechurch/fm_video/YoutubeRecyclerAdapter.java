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
    private List<UnCodeVideoModel.Data> UtubeBasket;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    public List<Object> VIDEO_LIST_ITEMS = new ArrayList<>();

    private OnItemClickListener mListener = null;

    public YoutubeRecyclerAdapter() {
    }


    public void setDataListItem(List<Object> items) {
        if (items != null) {
            VIDEO_LIST_ITEMS = items;
            MLog.d("test 어댑터 VIDEO_LIST_ITEMS " + VIDEO_LIST_ITEMS);
            notifyDataSetChanged();
        }

    }
    public void clearItem() {
        this.UtubeBasket.clear();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new YoutubeViewHolder(binding,UtubeBasket);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return UtubeBasket == null ? 0 : UtubeBasket.size();
    }

    public void setItems(List<UnCodeVideoModel.Data> youtubeVideos) {
        UtubeBasket = youtubeVideos;
        MLog.d("recyclerAdapter setData method :" + UtubeBasket);
        notifyDataSetChanged();
    }

    public void addItem(UnCodeVideoModel.Data youtube) {

        try {
            UtubeBasket = (List<UnCodeVideoModel.Data>) youtube;
            MLog.d("UtubeBasket addItem :" + UtubeBasket);
            MLog.d("UtubeBasket addItem  youtube:" + youtube);
            notifyDataSetChanged();
            MLog.d("어댑터 total :" +UtubeBasket);
        } catch (Exception e) {

        }



    }




}

