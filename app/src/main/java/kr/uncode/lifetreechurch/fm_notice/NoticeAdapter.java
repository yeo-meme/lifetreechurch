package kr.uncode.lifetreechurch.fm_notice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NoticeModel;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmNoticecardBinding;
import kr.uncode.lifetreechurch.utils.MLog;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeHolder> {
    private OnItemClickListener mListener = null;
    FmNoticecardBinding binding;
    private List<NoticeModel.Data> noticeList;

    public void setData(List<NoticeModel.Data> list) {
        noticeList = list;
        MLog.d("notice" + noticeList);
    }
    @NonNull
    @Override
    public NoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = FmNoticecardBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding) ;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeHolder holder, int position) {
            holder.onBind(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class ViewHolder extends NoticeHolder {
        FmNoticecardBinding binding;
        public ViewHolder(@NonNull FmNoticecardBinding itemView) {
            super(itemView);
            binding = itemView;
            binding.noticeEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (mListener != null) {
                        mListener.onListItemClick(noticeList,pos);
                    }
                }
            });
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            if (noticeList != null) {
                binding.noticeTitle.setText(noticeList.get(position).title);
                BaseApplication.getInstance().progressOFF();
            }
            ;
        }
    }
}
