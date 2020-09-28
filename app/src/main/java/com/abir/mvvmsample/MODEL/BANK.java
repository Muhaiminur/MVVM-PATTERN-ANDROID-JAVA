package com.abir.mvvmsample.MODEL;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BANK {

    @SerializedName("ImageList")
    @Expose
    private String imageList;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}