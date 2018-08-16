package com.example.lawrenjuip.tinkering.Service;

        import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface PhotoInterface {

    @GET("rest/?")
    Call<PhotoQueryResponse> getAsyncPhotoQueryResponse(@Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallBack,
            @Query("extras") String imageUrlFormat,
            @Query("perpage") int perPage,
            @Query("page") int page);
}

