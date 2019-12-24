package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.BlogWeekly;
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


    public void jiNuList(final ResponseCallback<BlogWeekly> callback) {
        final Call<BlogWeekly> task = apiService.getWeekly();
        task.enqueue(new Callback<BlogWeekly>() {
            @Override
            public void onResponse(Call<BlogWeekly> call, Response<BlogWeekly> response) {

                if (response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<BlogWeekly> call, Throwable t) {
                callback.response(null);
            }
        });
    }
}
