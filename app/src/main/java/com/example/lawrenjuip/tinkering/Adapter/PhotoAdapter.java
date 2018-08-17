package com.example.lawrenjuip.tinkering.Adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lawrenjuip.tinkering.PhotoModel.Photo;
import com.example.lawrenjuip.tinkering.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder>{
    private List<Photo> mPhotoList;
    private Context mContext;
    private Drawable mPlaceholder;

    public PhotoAdapter(List<Photo> photos, Context context){
        mPhotoList = photos;
        mContext = context;

    }

    public class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public ImageView mPhotoImageView;

        public PhotoHolder(View itemView){
            super(itemView);
            mView = itemView;
            mPhotoImageView = mView.findViewById(R.id.photo_item_view);
            itemView.setOnClickListener(this);
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
        mPlaceholder = createPlaceholder();

        Picasso.get()
                .load(photo.getUrlThumbnail())
                .error(R.drawable.ic_image_error)
                .placeholder(mPlaceholder)
                .into(photoHolder.mPhotoImageView);

    }

    @Override
    public int getItemCount(){
        return mPhotoList.size();
    }

    private Drawable createPlaceholder(){
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = null;
        Drawable placeholder = null;

        try{
            inputStream = assetManager.open("portrait_placeholder.png");
            placeholder = Drawable.createFromStream(inputStream, null);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream == null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return placeholder;
    }
}
