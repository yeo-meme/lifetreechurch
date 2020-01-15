package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.Weekly;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnCodeWeeklyConfig {
    private RetroApiService apiService;

    public UnCodeWeeklyConfig() {
        apiService = BlogConfig.getInstance().getApiService();
    }


    public void jiNuList(final ResponseCallback<Weekly> callback) {
        final Call<Weekly> task = apiService.getWeekly();
        task.enqueue(new Callback<Weekly>() {
            @Override
            public void onResponse(Call<Weekly> call, Response<Weekly> response) {

                if (response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weekly> call, Throwable t) {
                callback.response(null);
            }
        });
    }
}
