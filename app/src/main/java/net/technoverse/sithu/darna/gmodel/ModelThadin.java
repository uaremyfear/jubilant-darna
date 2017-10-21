package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 10/22/17.
 */

public class ModelThadin {
    @SerializedName("id")
    private int thadin_id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    @SerializedName("image_url")
    private String image_url;

    private List<ModelThadin> thadins;

    public int getThadin_id() {
        return thadin_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<ModelThadin> getThadins() {
        return thadins;
    }

    public String getImage_url() {
        return image_url;
    }
}

