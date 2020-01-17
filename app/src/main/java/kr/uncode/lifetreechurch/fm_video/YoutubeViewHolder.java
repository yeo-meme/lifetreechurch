package kr.uncode.lifetreechurch.fm_video;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
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

    private OnItemClickListener<UnCodeVideoModel.Data> mListener = null;
    private List<UnCodeVideoModel.Data> utubeBasket = new ArrayList<>();
    ItemYoutubeListBinding b;

    private YoutubeRecyclerAdapter youtubeRecyclerAdapter;

    private LinearLayoutManager manager;




    public void setOnItemClickListener(OnItemClickListener<UnCodeVideoModel.Data> listener) {
        this.mListener = listener;
    }
    public YoutubeViewHolder(@NonNull ItemYoutubeListBinding itemView, List<UnCodeVideoModel.Data> data) {
        super(itemView);
        b = itemView;
        this.utubeBasket = data;

//        b.youtubeCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos = getAdapterPosition();
//
//                if (mListener != null) {
//                    mListener.onListItemClick(utubeBasket,pos);
//                    MLog.d("listener worksssss"+ utubeBasket);
//                }
//            }
//        });
    }


//
//    public YoutubeViewHolder(@NonNull ItemYoutubeListBinding itemView) {
//        super(itemView);
//        binding = itemView;
//
//

//    }




    @Override
    protected void clear() {
    }

    public void onBind(int position) {
        super.onBind(position);
//        final UnCodeVideoModel.Data items = (UnCodeVideoModel.Data) VIDEO_LIST.get(position);




//        UnCodeVideoModel.Data items =  (UnCodeVideoModel.Data)VIDEO_LIST.get(position);
        b.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    MLog.d("holder Listenr");
                    mListener.onListItemClick(utubeBasket,getAdapterPosition());
                }
            }
        });

        if (utubeBasket != null) {
            String title = utubeBasket.get(position).title;
            b.youtubeTitle.setText(title);

            String thumbnail = utubeBasket.get(position).thumbnail;
            if (thumbnail != null) {
                Glide.with(itemView.getContext())
                        .load(thumbnail)
                        .into(b.youtubeImg);
            }
        }

//        if (items != null) {
//            String title = items.unCodeList.get(position).title;
//            MLog.d(title);
//            binding.youtubeTitle.setText(title);
//            String thumbnail = items.unCodeList.get(position).thumbnail;
//            if (thumbnail != null) {
//                Glide.with(itemView.getContext())
//                        .load(thumbnail)
//                        .into(binding.youtubeImg);
//            }
//        }
    }
}
