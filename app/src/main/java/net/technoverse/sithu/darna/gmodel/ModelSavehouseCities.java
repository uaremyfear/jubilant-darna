package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 8/19/17.
 */

public class ModelSavehouseCities {

    @SerializedName("cities")
    private List<ModelSavehouseCity> cities;

    public List<ModelSavehouseCity> getCities() {
        return cities;
    }
}
