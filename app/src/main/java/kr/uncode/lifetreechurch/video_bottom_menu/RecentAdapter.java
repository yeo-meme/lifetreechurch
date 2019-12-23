package kr.uncode.lifetreechurch.video_bottom_menu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmRecentcardBinding;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-23
 */
public class RecentAdapter extends RecyclerView.Adapter<RecentViewHolder> {


    FmRecentcardBinding binding;

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FmRecentcardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecentViewHolder {

        FmRecentcardBinding binding;

        public ViewHolder(@NonNull FmRecentcardBinding itemView) {
            super(itemView);
            binding = itemView;
        }

        @Override
        protected void clear() {

        }
    }
}
