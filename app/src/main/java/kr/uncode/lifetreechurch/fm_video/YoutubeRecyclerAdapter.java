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
//    private List<UnCodeVideoModel.Data> UtubeBasket;

    private List<UnCodeVideoModel.Data> list = new ArrayList<>();
    private List<Object> basket;
    DisplayMetrics displayMetrics = new DisplayMetrics();
//    public List<Object> VIDEO_LIST_ITEMS = new ArrayList<>();

    private OnItemClickListener mListener = null;

    public YoutubeRecyclerAdapter() {
    }


    public void setDataListItem(List<UnCodeVideoModel.Data> items) {


        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                try {
                    list.add(items.get(i));
                } catch (Exception e) {
                    MLog.d(e.getMessage());
                }
            }
            MLog.d("video All" + list);
            notifyDataSetChanged();
        }

    }

    public void clearItem() {
        this.list.clear();
        notifyDataSetChanged();

//        this.VIDEO_LIST_ITEMS.clear();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new YoutubeViewHolder(binding, list);
//        return new YoutubeViewHolder(binding,VIDEO_LIST_ITEMS);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//        final UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) VIDEO_LIST_ITEMS.get(position);
        holder.onBind(position);


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

//    public void setItems(List<UnCodeVideoModel.Data> youtubeVideos) {
//        list = youtubeVideos;
//        MLog.d("recyclerAdapter setData method :" + list);
//        notifyDataSetChanged();
//    }

    public void addItem(List<UnCodeVideoModel.Data> youtube) {

        if (youtube != null) {
            for (int i = 0; i < youtube.size(); i++) {
                try {
                    list.add(youtube.get(i));
                } catch (Exception e) {
                    MLog.d(e.getMessage());
                }
            }
            MLog.d("morning video All" + list);
            notifyDataSetChanged();
        }

    }


    /**
     * 사용자가 카테고리를 선택할때 처음 초기화 리스트를 10장을 불러오기
     *
     * @param youtubeVideos
     */
    public void setItems(List<UnCodeVideoModel.Data> youtubeVideos) {



        if (youtubeVideos != null) {
            for (int e = 0; e<youtubeVideos.size(); e++) {
                try {
                    list.add(youtubeVideos.get(e));
                } catch (Exception i) {
                    MLog.d(i.getMessage());
                }
            }
        }
        notifyDataSetChanged();
        MLog.d("reset data first :" + list);
    }


}