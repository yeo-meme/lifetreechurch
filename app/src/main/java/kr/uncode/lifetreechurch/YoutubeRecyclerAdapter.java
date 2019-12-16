package kr.uncode.lifetreechurch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.uncode.lifetreechurch.Model.SnippetResponse;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class YoutubeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int CHILD = 1;
    ItemYoutubeListBinding binding;
    private List<YoutubeResponse.Items> UtubeBasket;
    public static final int VIEW_TYPE_NOMAL = 1;
    private List<YoutubeVideoModel> mYoutubeVideos;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    private String temVideoId;

    public YoutubeRecyclerAdapter() {
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemYoutubeListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
       return UtubeBasket.size();
    }

    public void setItems(List<YoutubeResponse.Items> youtubeVideos) {
        UtubeBasket = youtubeVideos;
        MLog.d("recyclerAdapter setData method :" + UtubeBasket);
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        ItemYoutubeListBinding binding;
        public ViewHolder(@NonNull ItemYoutubeListBinding itemView) {
            super(itemView);
            binding = itemView;

        }

        @Override
        protected void clear() {
        }

        public void onBind(int position) {
            super.onBind(position);
            YoutubeResponse.Items.Id videoId = UtubeBasket.get(position).id;
            temVideoId = videoId.videoId;
            MLog.d("YoutubeRecyclerAdap onBind temString :" + temVideoId);

            String temTitle = UtubeBasket.get(position).snippet.title;
            MLog.d(temTitle);

            binding.textViewTitle.setText(temTitle);
//
//            final YoutubeVideoModel mYoutubeVideo = mYoutubeVideos.get(position);
//            ((Activity) itemView.getContext()).getWindowManager()
//                    .getDefaultDisplay()
//                    .getMetrics(displayMetrics);
//            int width = displayMetrics.widthPixels;
//            if (mYoutubeVideo.getTitle() != null) {
//                binding.textViewTitle.setText(mYoutubeVideo.getTitle());
//            }

            String idUrl = "https://img.youtube.com/vi/" + temVideoId + "/" + "mqdefault.jpg";
            MLog.d("파싱한 아이디 값 : "+ idUrl);
            if (temVideoId != null) {
                Glide.with(itemView.getContext())
                        .load(idUrl)
//                        .apply(new RequestOptions().override(displayMetrics.widthPixels - 36, 200))
                        .into(binding.imageViewItem);
            }
        }
    }


}

