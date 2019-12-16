package kr.uncode.lifetreechurch.fm_news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.databinding.FmPicturenewmemBinding;
import kr.uncode.lifetreechurch.databinding.ItemYoutubeListBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class NewMemberRecyclerAdapter extends RecyclerView.Adapter<NewMemberBaseHolder> {

    private List<NewMemberModel.Data> memberList;

    public NewMemberModel.Data getMember;
    FmPicturenewmemBinding binding;
    public NewMemberRecyclerAdapter() {

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
        return memberList.size();
    }



    public void setItems(List<NewMemberModel.Data> member) {
        memberList = member;
        MLog.d("recyclerAdapter setData method :" + memberList);
        notifyDataSetChanged();
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

                Glide.with(itemView.getContext())
                        .load(memberList.get(position).imgurl)
//                        .apply(new RequestOptions().override(displayMetrics.widthPixels - 36, 200))
                        .into(binding.recyclerViewImage);
        }
    }
}
