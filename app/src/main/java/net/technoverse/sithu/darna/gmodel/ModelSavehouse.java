package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 6/27/17.
 */

public class ModelSavehouse {

    private List<ModelSavehouse> savehouses;

    @SerializedName("id")
    private int savehouse_id;

    @SerializedName("name")
    private String name;

    @SerializedName("block")
    private String block;

    @SerializedName("description")
    private String description;

    @SerializedName("address")
    private String address;

    @SerializedName("township")
    private String township;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("phones")
    private List<ModelPhone> phones;

    public List<ModelPhone> getPhones() {
        return phones;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTownship() {
        return township;
    }

    public List<ModelSavehouse> getSavehouses() {
        return savehouses;
    }

    public int getSavehouse_id() {
        return savehouse_id;
    }

    public String getName() {
        return name;
    }

    public String getBlock() {
        return block;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

}
