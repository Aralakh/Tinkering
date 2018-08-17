package com.example.lawrenjuip.tinkering.Service;

        import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface PhotoInterface {
    int NUM_OF_PHOTOS_PER_PAGE = 50;
    String API_KEY = "17799f23e3aa7e76d8436fb030d88745";
    String API_FORMAT = "json";
    int NO_JSON_CALLBACK = 1;
    String IMAGE_URL_FORMAT = "url_t";
    String API_METHOD = "flickr.photos.getRecent";
    @GET("rest/?")
    Call<PhotoQueryResponse> getAsyncPhotoQueryResponse(@Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("format") String format,
            @Query("nojsoncallback") int noJsonCallBack,
            @Query("extras") String imageUrlFormat,
            @Query("perpage") int perPage,
            @Query("page") int page);
}

