package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Dialog.DialogProgress;
import kr.uncode.lifetreechurch.Model.NoticeModel;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeConfig {
    private RetroApiService apiService;

    public NoticeConfig() {
        apiService = BlogConfig.getInstance().getApiService();
    }

    public void noticeList(final ResponseCallback<NoticeModel> callback) {
        final Call<NoticeModel> task = apiService.getNotice();
        task.enqueue(new Callback<NoticeModel>() {
            @Override
            public void onResponse(Call<NoticeModel> call, Response<NoticeModel> response) {
                if (response.isSuccessful()) {
                    MLog.d(response.body().toString());
                    callback.response(response.body());
                }
            }

            @Override
            public void onFailure(Call<NoticeModel> call, Throwable t) {

            }
        });
    }
}
