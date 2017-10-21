package net.technoverse.sithu.darna.api;

import net.technoverse.sithu.darna.gmodel.ModelCategory;
import net.technoverse.sithu.darna.gmodel.ModelFoundation;
import net.technoverse.sithu.darna.gmodel.ModelOrganization;
import net.technoverse.sithu.darna.gmodel.ModelSavehouse;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseCity;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseDetail;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseState;
import net.technoverse.sithu.darna.gmodel.ModelThadin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sithu on 6/22/17.
 */

public interface MainService {

    @GET("savehouse")
    Call<ModelSavehouse> getAllSavehouse();

    @GET("savehouse_category")
    Call<ModelCategory> getAllCategory();

    @GET("savehouse_states")
    Call<ModelSavehouseState> getAllSavehouseState();

    @GET("savehouse/{savehouse_id}")
    Call<ModelSavehouseDetail> getSavehouseDetail(@Path("savehouse_id") int savehouse_id);

    @GET("savehouse_category/{category_id}")
    Call<ModelSavehouse> getSavehouseByCategory(@Path("category_id") int category_id);

    @GET("savehousestatecity/{state_id}")
    Call<ModelSavehouseCity> getCityByState(@Path("state_id") int state_id);

    @GET("savehousebycity/{city_id}")
    Call<ModelSavehouse> getSavehouseByCity(@Path("city_id") int city_id);



    @GET("organization")
    Call<ModelOrganization> getAllOrganization();

    @GET("organization/{organization_id}")
    Call<ModelOrganization> getOrganizationById(@Path("organization_id") int organization_id);

    @GET("organization_category")
    Call<ModelCategory> getOrganizationCategory();


    @GET("organization_category/{category_id}")
    Call<ModelOrganization> getOrganizationByCategory(@Path("category_id") int category_id);



    @GET("foundation")
    Call<ModelFoundation> getAllFoundation();

    @GET("foundation_category")
    Call<ModelCategory> getFoundationCategory();


    @GET("foundation/{foundation_id}")
    Call<ModelFoundation> getFoundationById(@Path("foundation_id") int foundation_id);

    @GET("foundation_category/{category_id}")
    Call<ModelFoundation> getFoundationByCategory(@Path("category_id") int category_id);

    @GET("thadin")
    Call<ModelThadin> getThadin();

    @GET("thadin/{thadin_id}")
    Call<ModelThadin> getThadinDetail(@Path("thadin_id") int thadin_id);

}
