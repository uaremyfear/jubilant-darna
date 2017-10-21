package net.technoverse.sithu.darna.thadin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelThadin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThadinDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thadin_detail);

        this.setTitle("Detail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle args = getIntent().getExtras();
        int thadin_id = args.getInt("id");

        Call<ModelThadin> thadinCall = MainApi.createService(MainService.class).getThadinDetail(thadin_id);

        ThadinAPILoading(thadinCall);
    }

    private void ThadinAPILoading(Call<ModelThadin> thadinCall) {
        thadinCall.enqueue(new Callback<ModelThadin>() {
            @Override
            public void onResponse(Call<ModelThadin> call, Response<ModelThadin> response) {
                ModelThadin thadin = response.body();


            }

            @Override
            public void onFailure(Call<ModelThadin> call, Throwable t) {

            }
        });
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
}
