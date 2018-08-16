package com.example.lawrenjuip.tinkering.Service;

import com.example.lawrenjuip.tinkering.PhotoModel.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoManager {
    private List<Photo> mPhotoList = new ArrayList<>();

    public List<Photo> getmPhotoList() {
        return mPhotoList;
    }

    public void setmPhotoList(List<Photo> mPhotoList) {
        this.mPhotoList = mPhotoList;
    }
}
