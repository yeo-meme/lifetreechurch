package kr.uncode.lifetreechurch;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    ItemYoutubeListBinding binding;
    private List<UnCodeVideoModel.Data> UtubeBasket;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    private OnItemClickListener mListener = null;

    public YoutubeRecyclerAdapter() {
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
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

    public class ViewHolder extends BaseViewHolder {

        ItemYoutubeListBinding binding;

        public ViewHolder(@NonNull ItemYoutubeListBinding itemView) {
            super(itemView);
            binding = itemView;

            binding.youtubeCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    MLog.d("youtube card Click");
                    if (mListener != null) {
                        mListener.onListItemClick(UtubeBasket,pos);
                    }
                }
            });
        }

        @Override
        protected void clear() {
        }

        public void onBind(int position) {
            super.onBind(position);


            String title = UtubeBasket.get(position).title;

            MLog.d(title);
            binding.youtubeTitle.setText(title);

            String thumbnail = UtubeBasket.get(position).thumbnail;
            if (thumbnail != null) {
                Glide.with(itemView.getContext())
                        .load(thumbnail)
                        .into(binding.youtubeImg);
            }
        }
    }


}

