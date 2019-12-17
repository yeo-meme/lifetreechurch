package kr.uncode.lifetreechurch.fm_news;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.base.BaseActivity;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmPicturenewmemBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class NewMemberRecyclerAdapter extends RecyclerView.Adapter<NewMemberBaseHolder>{

    private List<NewMemberModel.Data> memberList;


    public NewMemberModel.Data getMember;
    FmPicturenewmemBinding binding;
    private OnItemClickListener mListener = null;

    public NewMemberRecyclerAdapter() {

    }


    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
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
            binding.recyclerViewImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MLog.d("이미지뷰 클릭 이벤트 오키");

                    int pos = getAdapterPosition();

                    if (mListener != null) {
                        mListener.onListItemClick(memberList,pos);

                    }
                }
            });
        }

        @Override
        protected void clear() {

        }



        public void onBind(int position) {
            super.onBind(position);

                Glide.with(itemView.getContext())
                        .load(memberList.get(position).imgurl)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    BaseApplication.getInstance().progressOFF();
                                return false;
                            }
                        })
//                        .apply(new RequestOptions().override(displayMetrics.widthPixels - 36, 200))
                        .into(binding.recyclerViewImage);
        }
    }
}

