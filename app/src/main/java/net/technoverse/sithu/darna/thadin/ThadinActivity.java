package net.technoverse.sithu.darna.thadin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.adapters.ThadinAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelThadin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThadinActivity extends AppCompatActivity implements ThadinAdapter.MainItemClickListener {

    List<ModelThadin> thadins;
    SuperRecyclerView sRecyclerView;
    ThadinAdapter thadinAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thadin);

        this.setTitle("News");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Call<ModelThadin> thadinCall = MainApi.createService(MainService.class).getThadin();

        ThadinAPILoading(thadinCall);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void ThadinAPILoading(Call<ModelThadin> organizationCall) {
        organizationCall.enqueue(new Callback<ModelThadin>() {
            @Override
            public void onResponse(Call<ModelThadin> call, Response<ModelThadin> response) {
                thadins = response.body().getThadins();

                sRecyclerView = (SuperRecyclerView) findViewById(R.id.thadin_recycler);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getApplicationContext() , LinearLayoutManager.VERTICAL ,false);
                sRecyclerView.setLayoutManager(linearLayoutManager);

                thadinAdapter = new ThadinAdapter( getApplicationContext(), thadins, ThadinActivity.this);
                sRecyclerView.setAdapter(thadinAdapter);
            }

            @Override
            public void onFailure(Call<ModelThadin> call, Throwable t) {
                Log.d("THADIN FIAL", "onFailure: ");
            }
        });

    }

    @Override
    public void onItemClick(ModelThadin itemClicked) {
        Bundle bundle = new Bundle();
        bundle.putInt("id",itemClicked.getThadin_id());
    }
}
