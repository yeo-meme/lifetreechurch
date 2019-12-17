package kr.uncode.lifetreechurch.fm_notice;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmNoticecardBinding;

public abstract class NoticeHolder extends RecyclerView.ViewHolder {
    FmNoticecardBinding binding;
    private int mCurrentPosition;

    public NoticeHolder(@NonNull FmNoticecardBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }

    protected  abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getmCurrentPosition() {
        return mCurrentPosition;
    }
}
