package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 8/19/17.
 */

public class ModelSavehouseCity {

    @SerializedName("id")
    private int city_id;

    @SerializedName("city_name")
    private String city_name;

    private List<ModelSavehouseCity> cities;

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public List<ModelSavehouseCity> getCities() {
        return cities;
    }
}
