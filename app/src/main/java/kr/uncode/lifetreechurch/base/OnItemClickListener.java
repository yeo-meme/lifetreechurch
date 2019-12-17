package kr.uncode.lifetreechurch.base;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NewMemberModel;

public interface OnItemClickListener<T> {
    void onListItemClick(List<NewMemberModel.Data> aa, int position);
}
