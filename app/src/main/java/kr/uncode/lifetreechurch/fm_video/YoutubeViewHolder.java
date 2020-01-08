package kr.uncode.lifetreechurch.fm_video;

import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.uncode.lifetreechurch.BaseViewHolder;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2020-01-06
 */
public class YoutubeViewHolder extends BaseViewHolder {

    private OnItemClickListener mListener = null;
    private List<UnCodeVideoModel.Data> utubeBasket;
    ItemYoutubeListBinding binding;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public YoutubeViewHolder(@NonNull ItemYoutubeListBinding itemView, List<UnCodeVideoModel.Data> getUtubeList,OnItemClickListener listener) {
        super(itemView);
        binding = itemView;
        this.utubeBasket = getUtubeList;
        this.mListener = listener;
    }


    public YoutubeViewHolder(@NonNull ItemYoutubeListBinding itemView) {
        super(itemView);
        binding = itemView;


        binding.youtubeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if (mListener != null) {
                }
            }
        });
    }




    @Override
    protected void clear() {
    }

    public void onBind(int position) {
        super.onBind(position);
//        final UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) VIDEO_LIST.get(position);

//        UnCodeVideoModel.Data items =  (UnCodeVideoModel.Data)VIDEO_LIST.get(position);

        YoutubeRecyclerAdapter.Item items = new YoutubeRecyclerAdapter.Item();

        if (items != null) {
            String title = items.unCodeList.get(position).title;
            MLog.d(title);
            binding.youtubeTitle.setText(title);
            String thumbnail = items.unCodeList.get(position).thumbnail;
            if (thumbnail != null) {
                Glide.with(itemView.getContext())
                        .load(thumbnail)
                        .into(binding.youtubeImg);
            }
        }
    }
}
