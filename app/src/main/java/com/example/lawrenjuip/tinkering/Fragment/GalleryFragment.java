package com.example.lawrenjuip.tinkering.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.example.lawrenjuip.tinkering.Adapter.PaginationScrollListener;
import com.example.lawrenjuip.tinkering.PhotoModel.Photo;
import com.example.lawrenjuip.tinkering.Adapter.PhotoAdapter;
import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;
import com.example.lawrenjuip.tinkering.R;
import com.example.lawrenjuip.tinkering.Service.FlickrService;
import com.example.lawrenjuip.tinkering.Service.PhotoInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lawrenjuip.tinkering.Service.PhotoInterface.IMAGE_URL_FORMAT;
import static com.example.lawrenjuip.tinkering.Service.PhotoInterface.NUM_OF_PHOTOS_PER_PAGE;

public class GalleryFragment extends Fragment {
    private RecyclerView mPhotoRecyclerView;
    private GridLayoutManager mPhotoGridLayoutManager;
    private PhotoAdapter mPhotoAdapter;
    private List<Photo> mPhotolist = new ArrayList<>();
    private int mCurrentPage = 1;
    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mMaxPages = 100;

    public static GalleryFragment newInstance(){
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        callAPI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View galleryView = inflater.inflate(R.layout.fragment_gallery, container, false);

        mPhotoRecyclerView = galleryView.findViewById(R.id.photo_recycler_view);
        mPhotoGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mPhotoRecyclerView.setLayoutManager(mPhotoGridLayoutManager);

        mPhotoRecyclerView.addOnScrollListener(new PaginationScrollListener(mPhotoGridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                mIsLoading = true;
                mCurrentPage++;
                if(mCurrentPage < mMaxPages){
                    callAPI();
                }else{
                    mIsLastPage = true;
                }
            }

            @Override
            public int getTotalPageCount() {
                return mMaxPages;
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        });
        return galleryView;
    }

    private void setRecyclerViewAdapter(List<Photo> photoList){
        mPhotoAdapter = new PhotoAdapter(photoList, getContext());
        mPhotoRecyclerView.setAdapter(mPhotoAdapter);
    }

    private void scaleGridLayout(){
        mPhotoRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = mPhotoRecyclerView.getMeasuredWidth();
                float columnWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getActivity().getResources().getDisplayMetrics());
                int columnNumber = Math.round(width/columnWidth);
                mPhotoGridLayoutManager = new GridLayoutManager(getActivity(), columnNumber);
                mPhotoRecyclerView.setLayoutManager(mPhotoGridLayoutManager);
                mPhotoRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void callAPI(){
        //have to add a callback so the ui will be updated when onResponse() is called.
        PhotoInterface photoInterface = FlickrService.createService(PhotoInterface.class);
        Call<PhotoQueryResponse> call = photoInterface.getAsyncPhotoQueryResponse(IMAGE_URL_FORMAT, NUM_OF_PHOTOS_PER_PAGE, mCurrentPage);
        call.enqueue(new Callback<PhotoQueryResponse>() {
            @Override
            public void onResponse(Call<PhotoQueryResponse> call, Response<PhotoQueryResponse> response) {
                Log.d("GalleryFragment","Response code: " + response.code());
                if(response.isSuccessful()){
                    List<Photo> resultList = response.body().getPhotos().getPhotolist();
                    if(mPhotolist == null || mPhotolist.size() == 0){
                        scaleGridLayout();
                        mPhotolist = resultList;
                        setRecyclerViewAdapter(mPhotolist);
                        mIsLoading = false;
                    }else{
                        int prevSize = mPhotolist.size();
                        mPhotolist.addAll(resultList);
                        mPhotoAdapter.notifyItemRangeChanged(prevSize, mPhotolist.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<PhotoQueryResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve photos", Toast.LENGTH_LONG).show();
            }
        });
    }
}
