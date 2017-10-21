package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 6/30/17.
 */

public class ModelCategories {

    @SerializedName("categories")
    private List<ModelCategory> categories;

    public List<ModelCategory> getcategories() {
        return categories;
    }
}
