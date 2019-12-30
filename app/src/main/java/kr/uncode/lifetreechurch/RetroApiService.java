package kr.uncode.lifetreechurch;

import kr.uncode.lifetreechurch.Model.BlogWeekly;
import kr.uncode.lifetreechurch.Model.HappyModel;
import kr.uncode.lifetreechurch.Model.NewMemberModel;
import kr.uncode.lifetreechurch.Model.NoticeModel;
import kr.uncode.lifetreechurch.Model.UnCodeVideoModel;
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
    Call<YoutubeResponse> videoList(@Query("part") String part, @Query("channelId") String channelId, @Query("key") String key, @Query("maxResults")Integer max, @Query("order") String date);


    @Headers({
            "x-uncode-id:5debc01bc96b921a9f7bff7c",
            "x-uncode-key:YokmmOcL68okD1dXFPew9trNgULYXlIwYTakIvZ7"
    })
    @GET("/ttlmc/weekly")
    Call<BlogWeekly> getWeekly();


    @Headers({
            "x-uncode-id:5debc01bc96b921a9f7bff7c",
            "x-uncode-key:YokmmOcL68okD1dXFPew9trNgULYXlIwYTakIvZ7"
    })
    @GET("/ttlmc/newFamily")
    Call<NewMemberModel> getNewmember();


    @Headers({
            "x-uncode-id:5debc01bc96b921a9f7bff7c",
            "x-uncode-key:YokmmOcL68okD1dXFPew9trNgULYXlIwYTakIvZ7"
    })
    @GET("/ttlmc/notice")
    Call<NoticeModel> getNotice();


    @Headers({
            "x-uncode-id:5debc01bc96b921a9f7bff7c",
            "x-uncode-key:YokmmOcL68okD1dXFPew9trNgULYXlIwYTakIvZ7"
    })
    @GET("/ttlmc/column")
    Call<HappyModel> getHappy();


    @Headers({
            "x-uncode-id:5debc01bc96b921a9f7bff7c",
            "x-uncode-key:YokmmOcL68okD1dXFPew9trNgULYXlIwYTakIvZ7"
    })
    @POST("/ttlmc/videos")
    @FormUrlEncoded
    Call<UnCodeVideoModel> unCodeVideoList( @Field("page") Integer page);



}

