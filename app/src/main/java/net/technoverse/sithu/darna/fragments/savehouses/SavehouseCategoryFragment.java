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
import android.widget.Toast;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.savehouse.SavehouseFilterAcitivity;
import net.technoverse.sithu.darna.adapters.CategoryAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sithu on 6/22/17.
 */

public class SavehouseCategoryFragment extends Fragment implements CategoryAdapter.MainItemClickListener{

    List<ModelCategory> categories;
    SuperRecyclerView sRecyclerView;
    CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_savehouse_category,container,false);

        final Call<ModelCategory> categoryCall = MainApi.createService(MainService.class).getAllCategory();
        CategoryAPILoading(categoryCall, view);

        return view;
    }

    private void CategoryAPILoading(Call<ModelCategory> categoryCall, final View view) {
        categoryCall.enqueue(new Callback<ModelCategory>() {
            @Override
            public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
                categories = response.body().getCategories();

                sRecyclerView = (SuperRecyclerView) view.findViewById(R.id.cateogry_recycler);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);

                categoryAdapter = new CategoryAdapter(getActivity().getApplicationContext(), categories, SavehouseCategoryFragment.this);
                sRecyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<ModelCategory> call, Throwable t) {
                Log.d("FAILURE", "onFailure: blah blah");
            }
        });
    }

    @Override
    public void onItemClick(ModelCategory itemClicked) {
//        SavehouseFragment savehouseFragment = new SavehouseFragment();
//        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_savehouse, savehouseFragment, null);
//        ft.commit();

        if ( Integer.parseInt( itemClicked.getSavehouse_count()) > 0)
        {
            Bundle args = new Bundle();
            args.putString("type","byCategory");
            args.putInt("category_id", itemClicked.getCategory_id());
            Intent intent = new Intent(getActivity(), SavehouseFilterAcitivity.class);
            intent.putExtras(args);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getActivity(), "No Savehouse Found", Toast.LENGTH_SHORT).show();
        }
    }
}
