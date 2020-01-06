package kr.uncode.lifetreechurch.fm_video;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.uncode.lifetreechurch.BaseViewHolder;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    ItemYoutubeListBinding binding;
        private List<Object> basket;
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
        this.VIDEO_LIST_ITEMS.clear();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new YoutubeViewHolder(binding,VIDEO_LIST_ITEMS);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//        final UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) VIDEO_LIST_ITEMS.get(position);
        holder.onBind(position);


    }

    @Override
    public int getItemCount() {
        return VIDEO_LIST_ITEMS == null ? 0 : VIDEO_LIST_ITEMS.size();
    }


    /**
     * 사용자가 카테고리를 선택할때 처음 초기화 리스트를 10장을 불러오기
     *
     * @param youtubeVideos
     */
    public void setItems(List<Object> youtubeVideos) {
        VIDEO_LIST_ITEMS = youtubeVideos;
        notifyDataSetChanged();
    }

    public void addItem(List<Object> youtube) {
        MLog.d("UtubeBasket addItem :" + VIDEO_LIST_ITEMS);
        VIDEO_LIST_ITEMS.add(youtube);
        MLog.d("UtubeBasket addItem  youtube:" + youtube);
        MLog.d("어댑터 total :" + VIDEO_LIST_ITEMS);
        notifyDataSetChanged();
    }


    public static class Item {
        public List<UnCodeVideoModel.Data> unCodeList;


        public Item() {}
        public Item(List<UnCodeVideoModel.Data> ucList) {
            this.unCodeList = ucList;
        }
    }
}