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

    public void setPage(int page) {
        this.page = page;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }
}
