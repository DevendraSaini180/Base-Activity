package com.devendrasaini.test.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoApiClient {
    private static String URL = "https://picsum.photos/v2/";
    private static Retrofit retrofit  = null; /* use of Singleton class concept*/

    private PhotoApiClient() {}

    public static Retrofit getApiInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
