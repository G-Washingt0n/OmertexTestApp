package com.pgmail.martsulg.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 25.10.2017.
 */

public class Urls {

    @SerializedName("thumb")
    private String download;

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
}
