package kr.uncode.lifetreechurch.fm_video;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmAfternoonvideolistBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public abstract class AfterHolder extends RecyclerView.ViewHolder {
    FmAfternoonvideolistBinding binding;
    private int mCurrentPosition;

    public AfterHolder(@NonNull FmAfternoonvideolistBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getmCurrentPosition() {
        return mCurrentPosition;
    }
}
