package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sithu on 9/10/17.
 */

public class ModelPhone {

    @SerializedName("phone_number")
    private String phone_number;

    public String getPhone_number() {
        return phone_number;
    }
}
