package com.example.lawrenjuip.tinkering.Service;

        import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface PhotoInterface {
    int NUM_OF_PHOTOS_PER_PAGE = 50;
    String API_KEY = "17799f23e3aa7e76d8436fb030d88745";
    String IMAGE_URL_FORMAT = "url_t";
    String GET_RECENTS_METHOD = "flickr.photos.getRecent";

    @GET("rest/?method="+GET_RECENTS_METHOD+"&api_key="+API_KEY+"&format=json&nojsoncallback=1")
    Call<PhotoQueryResponse> getAsyncPhotoQueryResponse(
            @Query("extras") String imageUrlFormat,
            @Query("perpage") int perPage,
            @Query("page") int page);
}

