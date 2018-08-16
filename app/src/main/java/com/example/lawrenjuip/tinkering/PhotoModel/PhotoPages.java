package com.example.lawrenjuip.tinkering.PhotoModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoPages {
    private int page;
    private int pages;
    @SerializedName("perpage") private int perPage;
    private int total;

    @SerializedName("photo") private List<Photo> photoList;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public List<Photo> getPhotolist(){
        return photoList;
    }
}
