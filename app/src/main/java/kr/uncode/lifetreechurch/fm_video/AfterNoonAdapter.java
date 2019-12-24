package kr.uncode.lifetreechurch.fm_video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kr.uncode.lifetreechurch.databinding.FmAfternoonvideolistBinding;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class AfterNoonAdapter extends RecyclerView.Adapter<AfterHolder> {

    FmAfternoonvideolistBinding binding;

    @NonNull
    @Override
    public AfterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FmAfternoonvideolistBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AfterHolder holder, int position) {
        holder.onBind(position);
    }



    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends AfterHolder {

        public ViewHolder(@NonNull FmAfternoonvideolistBinding itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }
}
