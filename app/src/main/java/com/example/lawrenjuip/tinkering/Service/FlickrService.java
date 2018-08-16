package com.example.lawrenjuip.tinkering.Service;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrService {
    private static final String API_KEY = "17799f23e3aa7e76d8436fb030d88745";
    private static final String FETCH_RECENTS = "flickr.photos.getRecent";
    private static final String BASE_URL = "https://api.flickr.com/services/rest";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(getURL())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static String getURL() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .addQueryParameter("extras", "url_s");

        return urlBuilder.build().toString();
    }

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
