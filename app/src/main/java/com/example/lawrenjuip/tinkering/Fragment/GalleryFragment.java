package com.example.lawrenjuip.tinkering.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lawrenjuip.tinkering.PhotoModel.Photo;
import com.example.lawrenjuip.tinkering.Adapter.PhotoAdapter;
import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;
import com.example.lawrenjuip.tinkering.R;
import com.example.lawrenjuip.tinkering.Service.FlickrService;
import com.example.lawrenjuip.tinkering.Service.PhotoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {
    int mCurrentPage = 1;
    private final int NUM_OF_PHOTOS_PER_PAGE = 25;
    private final String API_KEY = "17799f23e3aa7e76d8436fb030d88745";
    private final String API_FORMAT = "json";
    private final int NO_JSON_CALLBACK = 1;
    private final String IMAGE_URL_FORMAT = "url_s";
    private final String API_METHOD = "flickr.photos.getRecent";
    private RecyclerView mPhotoRecyclerView;
    private GridLayoutManager mPhotoGridLayoutManager;
    private PhotoAdapter mPhotoAdapter;

    public static GalleryFragment newInstance(){
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //have to add a callback so the ui will be updated when onResponse() is called.
        PhotoInterface photoInterface = FlickrService.createService(PhotoInterface.class);
        Call<PhotoQueryResponse> call = photoInterface.getAsyncPhotoQueryResponse(API_METHOD, API_KEY, API_FORMAT, NO_JSON_CALLBACK, IMAGE_URL_FORMAT, NUM_OF_PHOTOS_PER_PAGE, mCurrentPage);
        call.enqueue(new Callback<PhotoQueryResponse>() {
            @Override
            public void onResponse(Call<PhotoQueryResponse> call, Response<PhotoQueryResponse> response) {
                Log.d("GalleryFragment","Response code: " + response.code());
                setRecyclerViewAdapter(response.body().getPhotos().getPhotolist());
            }

            @Override
            public void onFailure(Call<PhotoQueryResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve photos", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View galleryView = inflater.inflate(R.layout.fragment_gallery, container, false);

        mPhotoRecyclerView = galleryView.findViewById(R.id.photo_recycler_view);
        mPhotoGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mPhotoRecyclerView.setLayoutManager(mPhotoGridLayoutManager);
        return galleryView;
    }

    private void setRecyclerViewAdapter(List<Photo> photoList){
        mPhotoAdapter = new PhotoAdapter(photoList, getContext());
        mPhotoRecyclerView.setAdapter(mPhotoAdapter);
    }
}
