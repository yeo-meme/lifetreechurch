package kr.uncode.lifetreechurch.Config;

import java.util.concurrent.TimeUnit;

import kr.uncode.lifetreechurch.RetroApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public String SERVER_URL = "https://www.googleapis.com/";
    private static RetrofitConfig instance = null;
    private Retrofit retrofit;
    private RetroApiService apiService;


    private RetrofitConfig() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(RetroApiService.class);
    }

    public void getSeverUrl(String serverUrl) {
        SERVER_URL = serverUrl;
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
