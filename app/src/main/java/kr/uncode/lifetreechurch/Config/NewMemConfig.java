package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.ResponseCallback;
import kr.uncode.lifetreechurch.RetroApiService;
import kr.uncode.lifetreechurch.utils.MLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMemConfig {
    private RetroApiService apiService;

    public NewMemConfig() {
        apiService = BlogConfig.getInstance().getApiService();
    }


    public void memberList(final ResponseCallback<NewMemberModel> callback) {
        final Call<NewMemberModel>task = apiService.getNewmember();
        task.enqueue(new Callback<NewMemberModel>() {
            @Override
            public void onResponse(Call<NewMemberModel> call, Response<NewMemberModel> response) {

                callback.response(response.body());
                MLog.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<NewMemberModel> call, Throwable t) {

                callback.response(null);
            }
        });
    }

}
