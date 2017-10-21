package net.technoverse.sithu.darna.fragments.savehouses;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.adapters.SavehouseStateAdapter;
import net.technoverse.sithu.darna.savehouse.SavehouseDetailActivity;
import net.technoverse.sithu.darna.adapters.SavehouseAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelSavehouse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sithu on 6/22/17.
 */


public class SavehouseFragment extends Fragment implements SavehouseAdapter.MainItemClickListener{


    SuperRecyclerView sRecyclerView;
    List<ModelSavehouse> savehouses = new ArrayList<>();
    SavehouseAdapter savehouseAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_savehouse,container,false);

        getActivity().setTitle("Savehouse");

        Call<ModelSavehouse> savehouseCall = null;

        Bundle bundle = getArguments();


        if(bundle != null)
        {
            Log.d("BUNDLE", "BUNDLE" + bundle.getString("type"));
            if (bundle.getString("type").equals("byLocation"))
            {

                int i = bundle.getInt("city_id");

                savehouseCall = MainApi.createService(MainService.class).getSavehouseByCity(i);
            }
            else if (bundle.getString("type").equals("byCategory"))
            {
                int i = bundle.getInt("category_id");

                savehouseCall = MainApi.createService(MainService.class).getSavehouseByCategory(i);
            }
        }
        else
        {
            savehouseCall = MainApi.createService(MainService.class).getAllSavehouse();
        }

//        savehouseCall = MainApi.createService(MainService.class).getAllSavehouse();

        SavehouseAPILoading(savehouseCall, view);
        return view;
    }

    private void SavehouseAPILoading(Call<ModelSavehouse> savehouseCall, final View view) {
        savehouseCall.enqueue(new Callback<ModelSavehouse>() {
            @Override
            public void onResponse(Call<ModelSavehouse> call, Response<ModelSavehouse> response) {
                savehouses  = response.body().getSavehouses();

                sRecyclerView = (SuperRecyclerView) view.findViewById( R.id.savehouse_recycler );


                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);

                savehouseAdapter = new SavehouseAdapter(getActivity().getApplicationContext(), savehouses, SavehouseFragment.this);
                sRecyclerView.setAdapter(savehouseAdapter);
            }

            @Override
            public void onFailure(Call<ModelSavehouse> call, Throwable t) {
                Log.d("FAILURE", "onFailure: FIAL FUCK");
            }
        });
    }

    @Override
    public void onItemClick(ModelSavehouse itemClicked) {
        Bundle args = new Bundle();
        args.putInt("savehouse_id", itemClicked.getSavehouse_id());
        Intent intent = new Intent(getActivity(), SavehouseDetailActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }
}
