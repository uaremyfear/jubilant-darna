package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 8/17/17.
 */

public class ModelSavehouseStates {

    @SerializedName("states")
    private List<ModelSavehouseState> states;

    public List<ModelSavehouseState> getStates() {
        return states;
    }
}
