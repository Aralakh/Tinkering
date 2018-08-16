package com.example.lawrenjuip.tinkering.PhotoModel;

public class PhotoQueryResponse {
    PhotoPages photos;
    String stat;

    public PhotoPages getPhotos() {
        return photos;
    }

    public void setPhotos(PhotoPages photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
