package com.r.testbl.Service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Unknown on 3/8/2018.
 */

public class RetrofitService {

    public Retrofit getService(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builderHttp = new OkHttpClient.Builder();
        builderHttp.addInterceptor(loggingInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.bukalapak.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builderHttp.build());

        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
