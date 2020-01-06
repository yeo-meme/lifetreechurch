package kr.uncode.lifetreechurch;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    ItemYoutubeListBinding binding;
    private int mCurrentPosition;


    public BaseViewHolder(@NonNull ItemYoutubeListBinding itemView) {
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
