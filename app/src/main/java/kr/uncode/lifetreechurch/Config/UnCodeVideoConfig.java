package kr.uncode.lifetreechurch.Config;

import android.widget.Toast;

import java.io.IOException;

import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.lt_main.MainActivity;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class UnCodeVideoConfig {

    private RetroApiService apiService;

    private MainActivity mainActivity;
    public UnCodeVideoConfig() {
        apiService = BlogConfig.getInstance().getApiService();
    }


    public void unCodeVideoCategoryList(String key,Integer pageNum, final ResponseCallback<UnCodeVideoModel> callback) {
        final Call<UnCodeVideoModel> task = apiService.unCodeCategoryVideoList(key,pageNum);
        MLog.d("key :" +key);
        task.enqueue(new Callback<UnCodeVideoModel>() {
            @Override
            public void onResponse(Call<UnCodeVideoModel> call, Response<UnCodeVideoModel> response) {

                if (response.isSuccessful() && response.body()!=null) {
                    callback.response(response.body());
                    MLog.d("reponse key"+response.body().toString());

                }




            }

            @Override
            public void onFailure(Call<UnCodeVideoModel> call, Throwable t) {
                callback.response(null);

                try {
                    t.getCause();
                    MLog.d("errorResponse"+"" +  t.getCause());
                } catch (Exception e) {
                    Toast.makeText(mainActivity.getApplicationContext(),"서버응답이 없습니다,네트워크환경을 확인해주세요",Toast.LENGTH_LONG).show();
                }


            }
        });
    }


    public void unCodeVideoList (Integer pageNum,final ResponseCallback<UnCodeVideoModel> callback) {
        final Call<UnCodeVideoModel> task = apiService.unCodeVideoList(pageNum);
        task.enqueue(new Callback<UnCodeVideoModel>() {
            @Override
            public void onResponse(Call<UnCodeVideoModel> call, Response<UnCodeVideoModel> response) {

//                if (response.isSuccessful()) {
//                    MLog.d(response.body().toString());
//                    callback.response(response.body());
//                }

                if(response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<UnCodeVideoModel> call, Throwable t) {

                callback.response(null);
            }
        });
    }


//    private RetroApiService apiService;
//
//    public UnCodeWeeklyConfig() {
//        apiService = BlogConfig.getInstance().getApiService();
//    }
//
//
//    public void jiNuList(final ResponseCallback<Weekly> callback) {
//        final Call<Weekly> task = apiService.getWeekly();
//        task.enqueue(new Callback<Weekly>() {
//            @Override
//            public void onResponse(Call<Weekly> call, Response<Weekly> response) {
//
//                if (response.isSuccessful()) {
//                    MLog.d(response.body().toString());
//                    callback.response(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Weekly> call, Throwable t) {
//                callback.response(null);
//            }
//        });
    }
