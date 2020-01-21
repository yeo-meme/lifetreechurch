package kr.uncode.lifetreechurch.Market;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import java.util.Calendar;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.databinding.DialogMarketVersionBinding;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.utils.MPref;

/**
 * Created by jinu@timehub.kr on 2020-01-21
 */
public class DialogMarketVersionConfirm extends Dialog {

    private DialogMarketVersionBinding binding;

    private int type = -1;
    private DialogCallback callback;

    public interface DialogCallback {
        void dialogCallbackOK();

        void dialogCallbackCANCEL();
    }

    public void setOnDialogCallback(DialogCallback callback) {this.callback = callback; }


    public void setType(int type) {this.type =type; }
    public DialogMarketVersionConfirm(Context context) { super(context);}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_market_version,null,false);
        setContentView(binding.getRoot());
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        if (type != -1) {
            if (type == MarketUpdate.MARKET_UPDATE_REQUIRED) {
                binding.checkboxMarketUpdateParent.setVisibility(View.GONE);
                binding.tvContent.setText(getContext().getString(R.string.alert_market_update_required));
            } else if (type == MarketUpdate.MARKET_UPDATE_OPTIONAL) {
                binding.checkboxMarketUpdateParent.setVisibility(View.VISIBLE);
                binding.btnCancel.setText(getContext().getString(R.string.str_next));
                binding.tvContent.setText(getContext().getString(R.string.alert_market_update_optional));
            }
        }


        binding.btnOk.setOnClickListener(v -> callback.dialogCallbackOK());
        binding.btnCancel.setOnClickListener(v -> {
            dismiss();
            callback.dialogCallbackCANCEL();
        });

        binding.checkboxMarketUpdate.setOnCheckedChangeListener((buttonView, isChecked) ->
        {
            MLog.d("setOnCheckedChangeListener: " + isChecked);
            if (isChecked) {
                MPref.setMarketUpdateDialogCheckInTime(Calendar.getInstance().getTimeInMillis());
            } else {
                MPref.setMarketUpdateDialogCheckInTime(0);
            }
        });
    }
}
