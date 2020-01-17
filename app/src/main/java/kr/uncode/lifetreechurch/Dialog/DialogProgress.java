package kr.uncode.lifetreechurch.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.databinding.DialogProgressBinding;


public class DialogProgress extends Dialog {

    private AnimationDrawable animationDrawable;
    private Handler handler;
    private Runnable runnable = this::dismiss;

    public DialogProgress(@NonNull Context context) {
        super(context);
    }

    public static void dismiss(final DialogProgress progress) {
        if (progress != null && progress.isShowing()) progress.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogProgressBinding binding = DialogProgressBinding.inflate(getLayoutInflater(), null, false);
        setContentView(binding.getRoot());

        //취소 불가
        setCancelable(false);

        //배경투명
        if (getWindow() != null) getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        handler = new Handler();
        binding.ivLoading.setImageDrawable(animationDrawable);

    }

    @Override
    public void show() {
        super.show();
        if (animationDrawable != null && !animationDrawable.isRunning()) animationDrawable.start();
        handler.postDelayed(runnable, TimeUnit.SECONDS.toMillis(10));
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (animationDrawable != null && animationDrawable.isRunning()) animationDrawable.stop();
        if (handler != null && runnable != null) handler.removeCallbacks(runnable);
    }
}
