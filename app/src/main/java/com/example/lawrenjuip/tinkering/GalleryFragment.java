package com.example.lawrenjuip.tinkering;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lawrenjuip.tinkering.PhotoModel.Photo;
import com.example.lawrenjuip.tinkering.PhotoModel.PhotoQueryResponse;
import com.example.lawrenjuip.tinkering.Service.FlickrService;
import com.example.lawrenjuip.tinkering.Service.PhotoInterface;

import java.util.List;

import retrofit2.Call;

public class GalleryFragment extends Fragment {
    int mCurrentPage = 1;
    private final int mNumOfPhotosPerPage = 25;
    private RecyclerView mPhotoRecyclerView;
    private GridLayoutManager mPhotoGridLayoutManager;

    public static GalleryFragment newInstance(){
        return new GalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //have to add a callback so the ui will be updated when onResponse() is called.
        PhotoInterface photoInterface = FlickrService.createService(PhotoInterface.class);
        Call<PhotoQueryResponse> call = photoInterface.getAsyncPhotoQueryResponse(mNumOfPhotosPerPage, mCurrentPage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View galleryView = inflater.inflate(R.layout.fragment_gallery, container, false);

        mPhotoRecyclerView = galleryView.findViewById(R.id.photo_recycler_view);
        mPhotoGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mPhotoRecyclerView.setLayoutManager(mPhotoGridLayoutManager);
        return galleryView;
    }

    private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mPhotoImageView;
        private Photo mPhoto;

        public PhotoHolder(View itemView){
            super(itemView);
            mPhotoImageView =(ImageView) itemView;
            itemView.setOnClickListener(this);
        }

        public void bindPhoto(Photo photo){
            mPhoto = photo;
        }

        @Override
        public void onClick(View itemView){
            //do something eventually
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{
        private List<Photo> mPhotoList;

        public PhotoAdapter(List<Photo> photos){
            mPhotoList = photos;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.photo_list_item_view, viewGroup, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position){
            Photo photo = mPhotoList.get(position);
            photoHolder.bindPhoto(photo);
            //Picasso stuff probably goes here?

        }

        @Override
        public int getItemCount(){
            return mPhotoList.size();
        }
    }
}
