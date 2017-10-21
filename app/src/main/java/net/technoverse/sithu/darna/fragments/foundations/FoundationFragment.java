package net.technoverse.sithu.darna.fragments.foundations;

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
import net.technoverse.sithu.darna.adapters.FoundationAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.foundation.FoundationDetailActivity;
import net.technoverse.sithu.darna.gmodel.ModelFoundation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sithu on 9/8/17.
 */

public class FoundationFragment extends Fragment implements FoundationAdapter.MainItemClickListener {

    List<ModelFoundation> foundations;
    SuperRecyclerView sRecyclerView;
    FoundationAdapter foundationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_foundation,container,false);

        Call<ModelFoundation> foundationCall = null;

        Bundle bundle = getArguments();

        if(bundle != null) {
            if (bundle.getString("type").equals("byCategory"))
            {
                int i = bundle.getInt("category_id");
                foundationCall = MainApi.createService(MainService.class).getFoundationByCategory(i);
            }
        }
        else {
            foundationCall = MainApi.createService(MainService.class).getAllFoundation();
        }

        FoundationAPILoading(foundationCall, view);

        return view;
    }

    private void FoundationAPILoading(final Call<ModelFoundation> foundationCall, final View view) {

        foundationCall.enqueue(new Callback<ModelFoundation>() {
            @Override
            public void onResponse(Call<ModelFoundation> call, Response<ModelFoundation> response) {
                foundations = response.body().getFoundations();

                sRecyclerView = (SuperRecyclerView) view.findViewById(R.id.foundation_recycler);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);

                foundationAdapter = new FoundationAdapter(getActivity().getApplicationContext(), foundations, FoundationFragment.this);
                sRecyclerView.setAdapter(foundationAdapter);
            }

            @Override
            public void onFailure(Call<ModelFoundation> call, Throwable t) {
                Log.d("FAILURE", "onFailure: ");
            }
        });
    }

    @Override
    public void onItemClick(ModelFoundation itemClicked) {
        Bundle args = new Bundle();
        args.putInt("foundation_id", itemClicked.getId());
        Intent intent = new Intent(getActivity(), FoundationDetailActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }
}
