package com.example.lawrenjuip.tinkering.PhotoModel;

import com.google.gson.annotations.SerializedName;

public class Photo {
    private String id;
    @SerializedName("url_s") private String urlSmall;
    @SerializedName("url_t") private String urlThumbnail;
    private String owner;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlSmall() {
        return urlSmall;
    }

    public void setUrlSmall(String urlSmall) {
        this.urlSmall = urlSmall;
    }

    public String getUrlThumbnail() { return urlThumbnail; }

    public void setUrlThumbnail(String urlThumbnail) { this.urlThumbnail = urlThumbnail; }
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
