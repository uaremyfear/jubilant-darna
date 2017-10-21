package net.technoverse.sithu.darna.fragments.savehouses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.savehouse.SavehouseCityActivity;
import net.technoverse.sithu.darna.savehouse.SavehouseFilterAcitivity;
import net.technoverse.sithu.darna.adapters.SavehouseCityAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseCity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SavehouseCityFragment extends Fragment implements SavehouseCityAdapter.MainItemClickListener {

    List<ModelSavehouseCity> cities = new ArrayList<>();
    SuperRecyclerView sRecyclerView;
    SavehouseCityAdapter savehouseCityAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceCity) {

        SavehouseCityActivity cityActivity = (SavehouseCityActivity) getActivity();
        int state_id = Integer.parseInt(cityActivity.getState_id());
        final View view = inflater.inflate(R.layout.fragment_savehouse_city,container,false);
        final Call<ModelSavehouseCity> cityCall = MainApi.createService(MainService.class).getCityByState(state_id);
        Log.d("FRAGMENT", "CITY FRAGMENT");
        SavehouseCity(cityCall, view);
        return view;
    }

    private void SavehouseCity(Call<ModelSavehouseCity> cityCall, final View view) {
        cityCall.enqueue(new Callback<ModelSavehouseCity>() {
            @Override
            public void onResponse(Call<ModelSavehouseCity> call, Response<ModelSavehouseCity> response) {
                cities = response.body().getCities();

                sRecyclerView = (SuperRecyclerView) view.findViewById(R.id.city_recycler);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);

                savehouseCityAdapter = new SavehouseCityAdapter(getActivity().getApplicationContext(), cities, SavehouseCityFragment.this);
                sRecyclerView.setAdapter(savehouseCityAdapter);

                Log.d("SUCCESS", "SUCCESS");
            }

            @Override
            public void onFailure(Call<ModelSavehouseCity> call, Throwable t) {
                Log.d("FAILURE", t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(ModelSavehouseCity itemClicked) {
//        if ( Integer.parseInt( itemClicked.getSavehouse_count()) > 0)
//        {
            Bundle args = new Bundle();
            args.putString("type","byLocation");
            args.putInt("city_id", itemClicked.getCity_id());
            Intent intent = new Intent(getActivity(), SavehouseFilterAcitivity.class);
            intent.putExtras(args);
            startActivity(intent);
//        }
//        else
//        {
//            Toast.makeText(getActivity(), "No Savehouse Found", Toast.LENGTH_SHORT).show();
//        }
    }
}
