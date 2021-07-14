package com.sriram.task4;

import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("message")
    String url;

    public Images(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
