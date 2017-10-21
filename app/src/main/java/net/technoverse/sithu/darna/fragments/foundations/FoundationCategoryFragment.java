package net.technoverse.sithu.darna.fragments.foundations;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.adapters.CategoryAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.foundation.FoundationFilterActivity;
import net.technoverse.sithu.darna.fragments.organizations.OrganizationCategoryFragment;
import net.technoverse.sithu.darna.gmodel.ModelCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoundationCategoryFragment extends Fragment implements CategoryAdapter.MainItemClickListener{

    List<ModelCategory> categories;
    SuperRecyclerView sRecyclerView;
    CategoryAdapter categoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_foundation_category, container, false);

        final Call<ModelCategory> categoryCall = MainApi.createService(MainService.class).getFoundationCategory();
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

                categoryAdapter = new CategoryAdapter(getActivity().getApplicationContext(), categories, FoundationCategoryFragment.this);
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
        Bundle args = new Bundle();
        args.putString("type","byCategory");
        args.putInt("category_id", itemClicked.getCategory_id());
        Intent intent = new Intent(getActivity(), FoundationFilterActivity.class);
        intent.putExtras(args);
        startActivity(intent);

//        FoundationFragment nextFrag= new FoundationFragment();
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_frame, nextFrag,"findThisFragment")
//                .addToBackStack(null)
//                .commit();
    }
}
