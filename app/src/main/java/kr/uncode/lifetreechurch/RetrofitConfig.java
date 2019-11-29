package kr.uncode.lifetreechurch;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    public static final String SERVER_URL = "https://www.googleapis.com";

    private static RetrofitConfig instance = null;
    private Retrofit retrofit;
    private RetroApiService apiService;

    private RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(RetroApiService.class);
    }


    public static RetrofitConfig getInstance() {
        if (instance == null) {
            instance = new RetrofitConfig();
        }
        return instance;
    }


    public RetroApiService getApiService() {
        return apiService;
    }
}
