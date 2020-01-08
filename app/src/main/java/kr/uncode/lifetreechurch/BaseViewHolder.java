package kr.uncode.lifetreechurch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
