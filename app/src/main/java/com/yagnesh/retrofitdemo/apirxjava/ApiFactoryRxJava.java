package com.yagnesh.retrofitdemo.apirxjava;


import com.yagnesh.retrofitdemo.Api.StackOverflowAPI;
import com.yagnesh.retrofitdemo.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Yagnesh on 3/19/2016.
 */

public class ApiFactoryRxJava {
    private static String API_BASE_URL = "https://api.stackexchange.com/2.2/";

    public static StackOverflowAPIRxJava createRetroService() {
        //http Logger like setLogLevel(RestAdapter.LogLevel.FULL)(older)
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) //logging Disable for release build
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        // prepare call in Retrofit 2.0
        return retrofit.create(StackOverflowAPIRxJava.class);

    }
}
