package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.JinuApiModel;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.RetrofitConfig;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JinuApiCofig {
    private RetroApiService apiService;

    public JinuApiCofig() {
        apiService = RetrofitConfig.getInstance().getApiService();
    }


    public void jinuList(final ResponseCallback<JinuApiModel> callback) {
        final Call<JinuApiModel> task = apiService.getWeekly();
        task.enqueue(new Callback<JinuApiModel>() {
            @Override
            public void onResponse(Call<JinuApiModel> call, Response<JinuApiModel> response) {

                if (response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<JinuApiModel> call, Throwable t) {
                callback.response(null);
            }
        });
    }
}
