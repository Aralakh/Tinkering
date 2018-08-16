package com.example.lawrenjuip.tinkering.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lawrenjuip.tinkering.PhotoModel.Photo;
import com.example.lawrenjuip.tinkering.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder>{
    private List<Photo> mPhotoList;
    private Context mContext;

    public PhotoAdapter(List<Photo> photos, Context context){
        mPhotoList = photos;
        mContext = context;

    }

    public class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        private ImageView mPhotoImageView;
        private Photo mPhoto;

        public PhotoHolder(View itemView){
            super(itemView);
            mView = itemView;
            mPhotoImageView = mView.findViewById(R.id.photo_item_view);
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

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
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
