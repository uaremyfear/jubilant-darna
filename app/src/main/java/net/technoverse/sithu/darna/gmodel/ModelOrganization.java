package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 9/5/17.
 */

public class ModelOrganization {

    @SerializedName("organization_name")
    private String organization_name;

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

    private List<ModelOrganization> organizations;

    public List<ModelOrganization> getOrganizations() {
        return organizations;
    }

    public String getOrganization_name() {
        return organization_name;
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
