package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 8/17/17.
 */

public class ModelSavehouseDetail {

    @SerializedName("id")
    private int savehouse_id;

    @SerializedName("name")
    private String savehouse_name;

    @SerializedName("block")
    private String block;

    @SerializedName("township")
    private String township;

    @SerializedName("address")
    private String address;

    @SerializedName("phones")
    private List<ModelPhone> phones;

    @SerializedName("lat")
    private float lat;

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    @SerializedName("lng")

    private float lng;

    public List<ModelPhone> getPhones() {
        return phones;
    }

    public int getSavehouse_id() {
        return savehouse_id;
    }

    public String getSavehouse_name() {
        return savehouse_name;
    }

    public String getBlock() {
        return block;
    }

    public String getTownship() {
        return township;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String image_url;


}
