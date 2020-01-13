package kr.uncode.lifetreechurch.video_bottom_menu;

import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmRecentcardBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2020-01-13
 */
public class MyVideoStorageHolder extends RecentViewHolder {

    FmRecentcardBinding binding;
    private OnItemClickListener<UserVideo> onItemClickListener;

    private List<UserVideo> clickList;

    private List<UserVideo> userVideoList;

    public MyVideoStorageHolder(@NonNull FmRecentcardBinding itemView, List<UserVideo> userVideoList, OnItemClickListener<UserVideo> listener) {
        super(itemView);
        binding = itemView;
        this.userVideoList = userVideoList;
        this.onItemClickListener = listener;
        MLog.d("userVideoLIst holder" + userVideoList);
    }

    @Override
    protected void clear() {

    }

    @Override
    public void onBind(int position) {
        super.onBind(position);
        String title = userVideoList.get(position).getTitle();
        String image = userVideoList.get(position).getImage_Url();
        String videoId = userVideoList.get(position).getVideoId();

        MLog.d("bind" + title);
        MLog.d("bind" + image);
        binding.title.setText(title);

        if (image != null) {
            Glide.with(itemView.getContext())
                    .load(image)
                    .into(binding.thumbnail);
        }
        binding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MLog.d("storage Click");

                int pos = getAdapterPosition();
                MLog.d("storage Click" +userVideoList.get(pos) );

                if (onItemClickListener != null) {
                    onItemClickListener.onListItemClick(userVideoList,pos);
                }

//                if (userVideoList != null) {
//                    for (int i = 0; i< userVideoList.size(); i++) {
//                        try {
//                            clickList.add(userVideoList.get(i));
//                            MLog.d("click List" + clickList);
//                        } catch (Exception e) {
//                            MLog.d(e.getMessage());
//                        }
//                    }
//                }


            }
        });
    }


}
