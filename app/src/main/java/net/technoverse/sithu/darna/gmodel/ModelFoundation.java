package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 9/8/17.
 */

public class ModelFoundation {

    @SerializedName("foundation_name")
    private String foundation_name;

    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("address")
    private String address;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("phones")
    private List<ModelPhone> phones;

    public List<ModelPhone> getPhones() {
        return phones;
    }

    private List<ModelFoundation> foundations;

    public List<ModelFoundation> getFoundations() {
        return foundations;
    }

    public String getFoundation_name() {
        return foundation_name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getImage_url() {
        return image_url;
    }
}
