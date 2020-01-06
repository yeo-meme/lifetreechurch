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

    public YoutubeViewHolder(@NonNull ItemYoutubeListBinding itemView, List<UnCodeVideoModel.Data> getUtubeList) {
        super(itemView);
        binding = itemView;
        this.utubeBasket = getUtubeList;

        binding.youtubeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if (mListener != null) {
                    mListener.onListItemClick(utubeBasket, pos);
                }
            }
        });
    }

    @Override
    protected void clear() {
    }

    public void onBind(int position) {
        super.onBind(position);
        if (utubeBasket != null) {
            String title = utubeBasket.get(position).title;
            MLog.d(title);
            binding.youtubeTitle.setText(title);
            String thumbnail = utubeBasket.get(position).thumbnail;
            if (thumbnail != null) {
                Glide.with(itemView.getContext())
                        .load(thumbnail)
                        .into(binding.youtubeImg);
            }
        }
    }
}
