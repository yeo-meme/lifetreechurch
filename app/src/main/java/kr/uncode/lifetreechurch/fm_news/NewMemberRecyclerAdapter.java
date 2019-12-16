package kr.uncode.lifetreechurch.fm_news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.databinding.FmPicturenewmemBinding;
import kr.uncode.lifetreechurch.lt_main.MainActivity;
import kr.uncode.lifetreechurch.utils.MLog;

public class NewMemberRecyclerAdapter extends RecyclerView.Adapter<NewMemberBaseHolder> {

    private List<String> mData = null;
    FmPicturenewmemBinding binding;
    public NewMemberRecyclerAdapter(List<String> list) {
        mData = list;
        MLog.d("test" + mData);
    }

    @NonNull
    @Override
    public NewMemberBaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FmPicturenewmemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewMemberBaseHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends NewMemberBaseHolder {

        FmPicturenewmemBinding binding;

        public ViewHolder(@NonNull FmPicturenewmemBinding itemView) {
            super(itemView);
            binding = itemView;
        }

        @Override
        protected void clear() {

        }


        public void onBind(int position) {
            super.onBind(position);

        }
    }
}
