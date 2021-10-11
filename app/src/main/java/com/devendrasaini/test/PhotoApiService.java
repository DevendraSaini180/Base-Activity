package com.devendrasaini.test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApiService {

    @GET("list")
    Call<List<PhotoModel>> getPhotos();
}
