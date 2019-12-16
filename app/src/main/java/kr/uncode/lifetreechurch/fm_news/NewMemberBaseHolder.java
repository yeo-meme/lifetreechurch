package kr.uncode.lifetreechurch.fm_news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmPicturenewmemBinding;


public abstract class NewMemberBaseHolder extends RecyclerView.ViewHolder {

    FmPicturenewmemBinding binding;
    private int mCurrentPosition;

    public NewMemberBaseHolder(@NonNull FmPicturenewmemBinding itemView) {
        super(itemView.getRoot());
        binding= itemView;
    }

    protected  abstract void  clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getmCurrentPosition() {
        return mCurrentPosition;
    }
}
