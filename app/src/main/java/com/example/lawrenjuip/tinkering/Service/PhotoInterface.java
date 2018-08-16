package com.example.lawrenjuip.tinkering.Service;

import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoInterface {
    @GET("flickr.photos.getRecent")
    Call<PhotoQueryResponse> getAsyncPhotoQueryResponse(@Query("perpage") int perpage, @Query("page") int page);
}
