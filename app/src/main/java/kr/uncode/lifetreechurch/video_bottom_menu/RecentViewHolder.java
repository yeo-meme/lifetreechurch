package kr.uncode.lifetreechurch.video_bottom_menu;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmRecentcardBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-23
 */
public abstract class RecentViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;
    FmRecentcardBinding binding;

    public RecentViewHolder(@NonNull FmRecentcardBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

}
