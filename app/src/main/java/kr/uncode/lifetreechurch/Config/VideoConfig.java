package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoConfig {
    final static String house = "HOUSE";

    private RetroApiService apiService;

    public VideoConfig() {

        apiService = RetrofitConfig.getInstance().getApiService();
    }

    public void videoList(final ResponseCallback<YoutubeResponse> callback) {
        final Call<YoutubeResponse> task = apiService.videoList("snippet", "UCVTQLxRfWz4nypo8q74iE1Q", "AIzaSyD20axF8pwlVcco5B0WMa_LTZM9Cq5VcZY",50);
        task.enqueue(new Callback<YoutubeResponse>() {
            @Override
            public void onResponse(Call<YoutubeResponse> call, Response<YoutubeResponse> response) {
                if (response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<YoutubeResponse> call, Throwable t) {
                callback.response(null);

            }
        });
    }
}
