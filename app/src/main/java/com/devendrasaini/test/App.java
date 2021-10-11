package com.devendrasaini.test;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static String URL = "https://picsum.photos/v2/";
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = getApiInstance();
    }

    public static Retrofit getApiInstance() {
        return (retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build());
    }


}
