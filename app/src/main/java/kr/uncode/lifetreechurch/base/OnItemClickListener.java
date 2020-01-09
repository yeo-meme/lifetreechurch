package kr.uncode.lifetreechurch.base;

import java.util.List;

import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;

public interface OnItemClickListener<T> {
    void onListItemClick(List<UnCodeVideoModel.Data> aa, int position);
}
