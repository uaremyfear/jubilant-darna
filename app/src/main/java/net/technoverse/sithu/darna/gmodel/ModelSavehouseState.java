package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 8/17/17.
 */

public class ModelSavehouseState {

    @SerializedName("id")
    private int state_id;

    @SerializedName("state_name")
    private String state_name;

    private List<ModelSavehouseState> states;

    public int getState_id() {
        return state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public List<ModelSavehouseState> getStates() {
        return states;
    }
}
