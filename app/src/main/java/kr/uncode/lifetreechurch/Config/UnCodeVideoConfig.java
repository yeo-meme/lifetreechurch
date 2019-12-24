package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
import kr.uncode.lifetreechurch.Model.UserVideo;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinu@timehub.kr on 2019-12-24
 */
public class UnCodeVideoConfig {

    private RetroApiService apiService;

    public UnCodeVideoConfig() {
        apiService = BlogConfig.getInstance().getApiService();
    }


    public void unCodeVideoList (final ResponseCallback<UnCodeVideoModel> callback) {
        final Call<UnCodeVideoModel> task = apiService.unCodeVideoList("주일오전",0);
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
//    public void jiNuList(final ResponseCallback<BlogWeekly> callback) {
//        final Call<BlogWeekly> task = apiService.getWeekly();
//        task.enqueue(new Callback<BlogWeekly>() {
//            @Override
//            public void onResponse(Call<BlogWeekly> call, Response<BlogWeekly> response) {
//
//                if (response.isSuccessful()) {
//                    MLog.d(response.body().toString());
//                    callback.response(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<BlogWeekly> call, Throwable t) {
//                callback.response(null);
//            }
//        });
    }
