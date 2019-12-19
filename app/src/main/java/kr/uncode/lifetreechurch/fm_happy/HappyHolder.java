package kr.uncode.lifetreechurch.fm_happy;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmHappycolumnBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public abstract class HappyHolder extends RecyclerView.ViewHolder {

    FmHappycolumnBinding binding;


    public HappyHolder(@NonNull FmHappycolumnBinding itemView) {
        super(itemView.getRoot());
        binding = itemView;
    }


    protected abstract void clear();

    public void onBind(int position){
        clear();
    }
}
