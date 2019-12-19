package kr.uncode.lifetreechurch.fm_happy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.uncode.lifetreechurch.Model.HappyModel;
import kr.uncode.lifetreechurch.base.BaseApplication;
import kr.uncode.lifetreechurch.base.OnItemClickListener;
import kr.uncode.lifetreechurch.databinding.FmHappycolumnBinding;
import kr.uncode.lifetreechurch.utils.MLog;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public class HappyAdapter extends RecyclerView.Adapter<HappyHolder> {

private List<HappyModel.Data> happyList;

FmHappycolumnBinding binding;
private OnItemClickListener mListener = null;


public HappyAdapter() {}


public void setOnItemClickListener(OnItemClickListener listener) {
    this.mListener = listener;
}

public void setItems(List<HappyModel.Data> happy) {
    happyList = happy;
    notifyDataSetChanged();
    MLog.d("happy recycler :" + happyList);
}
    @NonNull
    @Override
    public HappyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      binding = FmHappycolumnBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HappyHolder holder, int position) {

    holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return happyList.size();
    }


    public class ViewHolder extends HappyHolder {


    FmHappycolumnBinding binding;

        public ViewHolder(@NonNull FmHappycolumnBinding itemView) {
            super(itemView);
            binding = itemView;

            binding.happyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (mListener != null) {
                        mListener.onListItemClick(happyList,pos);
                    }
                }
            });
        }
        @Override
        protected void clear() {

        }
        public void onBind(int position) {
            super.onBind(position);
            if (happyList != null) {
                binding.happyTitle.setText(happyList.get(position).title);
                binding.happyContents.setText(happyList.get(position).content);
//                BaseApplication.getInstance().progressOFF();
            }
            ;
        }
    }
}
