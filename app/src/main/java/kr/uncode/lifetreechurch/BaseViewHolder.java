package kr.uncode.lifetreechurch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    ItemYoutubeListBinding binding;
    private int mCurrentPosition;

    private List<UnCodeVideoModel.Data> aa;
    private OnItemClickListener<UnCodeVideoModel.Data> list;


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
