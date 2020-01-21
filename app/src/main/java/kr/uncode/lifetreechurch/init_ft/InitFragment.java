package kr.uncode.lifetreechurch.init_ft;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import kr.uncode.lifetreechurch.IntroduceBottomActivity.IntroduceActivity;
import kr.uncode.lifetreechurch.IntroduceBottomActivity.IntroduceChActivity;
import kr.uncode.lifetreechurch.Market.DialogMarketVersionConfirm;
import kr.uncode.lifetreechurch.Market.MarketUpdate;
import kr.uncode.lifetreechurch.R;
import kr.uncode.lifetreechurch.WeeklyFm.Weekly_Fm;
import kr.uncode.lifetreechurch.base.BaseFragment;
import kr.uncode.lifetreechurch.databinding.FmMainBinding;
import kr.uncode.lifetreechurch.fm_happy.HappyColumnFragment;
import kr.uncode.lifetreechurch.fm_news.NewMiddleFragment;
import kr.uncode.lifetreechurch.fm_video.VideoListFragment;
import kr.uncode.lifetreechurch.utils.MLog;
import kr.uncode.lifetreechurch.utils.MPref;
import kr.uncode.lifetreechurch.utils.Utils;

public class InitFragment extends BaseFragment implements MarketUpdate.MarketUpdateTaskCallback {
    private ProgressDialog progressDialog;
    private DialogMarketVersionConfirm dialog;
    private Context context;
    FmMainBinding binding;
    private boolean isVersionCheckRunning = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fm_main, container, false);
//        binding = FragmentMainBinding.inflate(inflater, container, false);
        context = getContext();
        backKeyHideController(true);
        recentMenuShowController(false);
        toolbarMenuButtonController(false);


        //dialog init
        dialog = new DialogMarketVersionConfirm(getContext());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (dialog != null && dialog.isShowing()) dialog.cancel();
    }


    private void startActivityGoogleMarket() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=kr.uncode.lifetreechurch"));
            intent.setPackage("kr.uncode.lifetreechurch");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    public static void isNetworkConnected(Context context) {
//
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//        if (isConnected) {
//            MLog.d("network"+isConnected);
//        } else {
//            MLog.d("network"+isConnected);
//            Toast.makeText(context,"네트워크가 꺼져 있습니다~ 네트워크 환경이 필요합니다~",Toast.LENGTH_LONG).show();
//
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuListener();
    }

    private void menuListener() {
        binding.videoMenu.setOnClickListener(this::videoClick);
        binding.weekly.setOnClickListener(this::weeklyClick);
        binding.menuColumn.setOnClickListener(this::happyClick);
        binding.newsMenu.setOnClickListener(this::news);

        binding.introduceBtn.setOnClickListener(this::introduce);
//        binding.introduceBtn.setOnClickListener(this::videoClick);
        binding.introduceChurchBtn.setOnClickListener(this::introduceChurch);

    }

    private void introduceChurch(View view) {
        Intent intent = new Intent(getActivity(), IntroduceChActivity.class);
        startActivity(intent);
    }

    private void introduce(View view) {
        Intent intent = new Intent(getActivity(), IntroduceActivity.class);
        startActivity(intent);

    }


    private void happyClick(View view) {

        if (Utils.isNetworkConnected(context)) {
            progressON("Loading...");
            MLog.d("넷워크 true happy");

            replaceFragment(new HappyColumnFragment(), true);

        } else {
            MLog.d("넷워크 false happy");

            Toast.makeText(context, "네트워크 환경을 확인해주세요~", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        MLog.i();

        //앱 체크
        if (!isVersionCheckRunning && Utils.isNetworkConnected(getActivity())) {
            isVersionCheckRunning = true;
            MarketUpdate checkTask = new MarketUpdate(getActivity());
            checkTask.setTaskCallback(this);
            checkTask.execute();
        }
    }

    private void news(View view) {

        if (Utils.isNetworkConnected(context)) {
            replaceFragment(new NewMiddleFragment(), true);
            MLog.d("넷워크 true news");

        } else {
            MLog.d("넷워크 false news");

            Toast.makeText(context, "네트워크 환경을 확인해주세요~", Toast.LENGTH_LONG).show();
        }


    }

    private void alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Coming Soon");
        builder.setMessage("업데이트 예정입니다~♡");
        builder.setPositiveButton("네", null);
        builder.create().show();
    }

    private void videoClick(View view) {
//        alert();


        if (Utils.isNetworkConnected(context)) {
            replaceFragment(new VideoListFragment(), true);
            MLog.d("넷워크 true video");

        } else {
            MLog.d("넷워크 false video");
            Toast.makeText(context, "네트워크 환경을 확인해주세요~", Toast.LENGTH_LONG).show();
        }

//        String player_state = "주일오전";
//        replaceFragment(new VideoFragment(),true);
    }

    private void weeklyClick(View view) {
//        alert();

        if (Utils.isNetworkConnected(context)) {
            replaceFragment(new Weekly_Fm(), true);
            MLog.d("넷워크 true weekly");

        } else {
            MLog.d("넷워크 false weekly");

            Toast.makeText(context, "네트워크 환경을 확인해주세요~", Toast.LENGTH_LONG).show();
        }
//        loading(view);
//        binding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void marketUpdate(int type) {

        isVersionCheckRunning = false;

        switch (type) {
            case MarketUpdate.MARKET_UPDATE_NOTHING:
                MLog.i("MARKET_UPDATE_NOTHING");
                break;
            case MarketUpdate.MARKET_UPDATE_REQUIRED:
                MLog.i("MARKET_UPDATE_REQUIRED");
                dialog.setOnDialogCallback(new DialogMarketVersionConfirm.DialogCallback() {
                    @Override
                    public void dialogCallbackOK() {
                        startActivityGoogleMarket();
                    }

                    @Override
                    public void dialogCallbackCANCEL() {
                        getActivity().finish();
                    }
                });


                dialog.setType(type);
                Utils.displayDialog(dialog, getActivity());
                break;
            case MarketUpdate.MARKET_UPDATE_OPTIONAL:
                MLog.i("MARKET_UPDATE_OPTIONAL");
                dialog.setOnDialogCallback(new DialogMarketVersionConfirm.DialogCallback() {
                    @Override
                    public void dialogCallbackOK() {
                        startActivityGoogleMarket();
                    }

                    @Override
                    public void dialogCallbackCANCEL() {

                    }
                });
                dialog.setType(type);


                if (MPref.isMarketUpdateDialogShow()) {
                    Utils.displayDialog(dialog,getActivity());
                } else {
                    MLog.d("next Activity");
                }
                break;
        }
    }

//    public void loading(View view) {
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        progressDialog = new ProgressDialog(getContext());
//                        progressDialog.setIndeterminate(true);
//                        progressDialog.setMessage("잠시만 기다려 주세요");
//                        progressDialog.show();
//                    }
//                }, 0);
//
//    }
}
