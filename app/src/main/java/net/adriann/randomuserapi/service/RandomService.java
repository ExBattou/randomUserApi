package net.adriann.randomuserapi.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RandomService {

    private static RandomService instance;
    private static String BASE_URL = "https://randomuser.me/";
    private Retrofit retrofit;

    public static RandomService getInstance() {
        if (instance == null) {
            instance = new RandomService();
        }
        return instance;
    }

    private RandomService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client;
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public RandomUseApi getRandomUseApi(){return retrofit.create(RandomUseApi.class);}
}
