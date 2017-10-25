package com.pgmail.martsulg.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 25.10.2017.
 */

public class ResponseProfile {

    @SerializedName("urls")
    private Urls links;

    public Urls getLinks() {
        return links;
    }

    public void setLinks(Urls links) {
        this.links = links;
    }
}
