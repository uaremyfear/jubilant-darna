package net.technoverse.sithu.darna.fragments.organizations;

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
import net.technoverse.sithu.darna.adapters.OrganizationAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelOrganization;
import net.technoverse.sithu.darna.organization.OrganizationDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sithu on 9/5/17.
 */

public class OrganizationFragment extends Fragment implements OrganizationAdapter.MainItemClickListener {

    List<ModelOrganization> organizations;
    SuperRecyclerView sRecyclerView;
    OrganizationAdapter organizationAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_organization,container,false);

        Call<ModelOrganization> organizationCall = null;

        Bundle bundle = getArguments();

        if(bundle != null) {
            if (bundle.getString("type").equals("byCategory"))
            {
                int i = bundle.getInt("category_id");
                organizationCall = MainApi.createService(MainService.class).getOrganizationByCategory(i);
            }
        }
        else {
            organizationCall = MainApi.createService(MainService.class).getAllOrganization();
        }

        OrganizationAPILoading(organizationCall, view);

        return view;
    }

    private void OrganizationAPILoading(Call<ModelOrganization> organizationCall,final View view) {
        organizationCall.enqueue(new Callback<ModelOrganization>() {
            @Override
            public void onResponse(Call<ModelOrganization> call, Response<ModelOrganization> response) {
                organizations = response.body().getOrganizations();

                sRecyclerView = (SuperRecyclerView) view.findViewById(R.id.organization_recycler);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);

                organizationAdapter = new OrganizationAdapter(getActivity().getApplicationContext(), organizations, OrganizationFragment.this);
                sRecyclerView.setAdapter(organizationAdapter);
            }

            @Override
            public void onFailure(Call<ModelOrganization> call, Throwable t) {

            }
        });

    }



    @Override
    public void onItemClick(ModelOrganization itemClicked) {
        Bundle args = new Bundle();
        args.putInt("organization_id", itemClicked.getId());
        Intent intent = new Intent(getActivity(), OrganizationDetailActivity.class);
        intent.putExtras(args);
        startActivity(intent);
    }
}
