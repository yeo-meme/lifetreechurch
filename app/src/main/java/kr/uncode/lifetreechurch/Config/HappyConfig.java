package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.HappyModel;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jinu@timehub.kr on 2019-12-19
 */
public class HappyConfig {
    private RetroApiService apiService;

    public HappyConfig() {
        apiService = BlogConfig.getInstance().getApiService();
    }


    public void happyList(final ResponseCallback<HappyModel> callback) {
        final Call<HappyModel> task = apiService.getHappy();
        task.enqueue(new Callback<HappyModel>() {
            @Override
            public void onResponse(Call<HappyModel> call, Response<HappyModel> response) {
                if (response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<HappyModel> call, Throwable t) {
                callback.response(null);
            }
        });
    }
}
