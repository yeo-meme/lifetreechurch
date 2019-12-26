package kr.uncode.lifetreechurch.Config;

import kr.uncode.lifetreechurch.RetroApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlogConfig {
    public static final String SEVER_URL = "https://api.uncode.kr:6000/";
    public static BlogConfig instance = null;

    private Retrofit retrofit;
    private RetroApiService apiService;

    public BlogConfig() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(SEVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(RetroApiService.class);

    }

    public static BlogConfig getInstance() {
        if (instance == null) {
            instance = new BlogConfig();
        }
        return instance;
    }

    public RetroApiService getApiService() {return apiService; }
}
