package kr.uncode.lifetreechurch;

import kr.uncode.lifetreechurch.Model.JinuApiModel;
import kr.uncode.lifetreechurch.Model.YoutubeResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroApiService {

    @GET("/youtube/v3/search")
    Call<YoutubeResponse> videoList(@Query("part") String part, @Query("channelId") String channelId, @Query("key") String key);


    @Headers({
            "x-uncode-id: 5debc01bc96b921a9f7bff7c",
            "x-uncode-key: YokmmOcL68okD1dXFPew9trNgULYXlIwYTakIvZ"
    })
    @GET("/weekly")
    Call<JinuApiModel> getWeekly();
}


