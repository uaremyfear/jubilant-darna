package net.technoverse.sithu.darna.gmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sithu on 6/30/17.
 */

public class ModelCategory {

    @SerializedName("id")
    private int category_id;

    @SerializedName("category_name")
    private String category_name;

    @SerializedName("savehouse_count")
    private String savehouse_count;



    private List<ModelCategory> categories;


    public List<ModelCategory> getCategories() {
        return categories;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getSavehouse_count() {
        return savehouse_count;
    }
}
