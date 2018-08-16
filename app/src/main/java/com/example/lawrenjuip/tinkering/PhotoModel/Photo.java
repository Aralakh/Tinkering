package com.example.lawrenjuip.tinkering.PhotoModel;

import com.google.gson.annotations.SerializedName;

public class Photo {
    private int id;
    @SerializedName("url_s") private String urlSmall;
    private String owner;
    private String title;
}
