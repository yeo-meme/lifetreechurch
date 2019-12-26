package kr.uncode.lifetreechurch.video_bottom_menu;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.databinding.FmRecentcardBinding;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-23
 */
public class RecentAdapter extends RecyclerView.Adapter<RecentViewHolder> {


    private List<UserVideo> userVideos;
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
        return userVideos.size();
    }


    public void setItems(List<UserVideo> temList) {
        userVideos = temList;
        MLog.d("recyclerAdapter setData method :" + userVideos);
        notifyDataSetChanged();
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

        @Override
        public void onBind(int position) {
            super.onBind(position);

            String title = userVideos.get(position).getTitle();
            String image = userVideos.get(position).getImage_Url();
            String videoId = userVideos.get(position).getVideoId();

            MLog.d("bind" + title);
            MLog.d("bind" + image);
            binding.title.setText(title);

            if (image != null) {
                Glide.with(itemView.getContext())
                        .load(image)
                        .into(binding.thumbnail);
            }



        }
    }
}
