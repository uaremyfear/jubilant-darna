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
import net.technoverse.sithu.darna.adapters.SavehouseStateAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseState;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sithu on 6/22/17.
 */

public class SavehouseLocationFragment extends Fragment implements SavehouseStateAdapter.MainItemClickListener {

    List<ModelSavehouseState> states;
    SuperRecyclerView sRecyclerView;
    SavehouseStateAdapter savehouseStateAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_savehouse_location,container,false);

        final Call<ModelSavehouseState> stateCall = MainApi.createService(MainService.class).getAllSavehouseState();
        SavehouseState(stateCall, view);

        return view;
    }

    private void SavehouseState(Call<ModelSavehouseState> stateCall, final View view) {
        stateCall.enqueue(new Callback<ModelSavehouseState>() {
            @Override
            public void onResponse(Call<ModelSavehouseState> call, Response<ModelSavehouseState> response) {
                states = response.body().getStates();
                sRecyclerView = (SuperRecyclerView) view.findViewById(R.id.location_recycler);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);
                savehouseStateAdapter = new SavehouseStateAdapter(getActivity().getApplicationContext(), states, SavehouseLocationFragment.this);
                sRecyclerView.setAdapter(savehouseStateAdapter);
            }

            @Override
            public void onFailure(Call<ModelSavehouseState> call, Throwable t) {
                Log.d("FAILURE", "onFailure: blah blah");
            }
        });
    }

    @Override
    public void onItemClick(ModelSavehouseState itemClicked) {

//        SavehouseCityFragment savehouseCityFragment = new SavehouseCityFragment();
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

//        Bundle args = new Bundle();
//        args.putInt("state_id", itemClicked.getState_id());

//        savehouseCityFragment.setArguments(args);
//        Log.d("LOCATUON", String.valueOf(itemClicked.getState_id()));
//        ft.replace(R.id.fragment_savehouse_location, savehouseCityFragment, null);
//        ft.commit();


        Bundle args = new Bundle();
        args.putInt("state_id", itemClicked.getState_id());
        Intent intent = new Intent(getActivity(), SavehouseCityActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }
}
