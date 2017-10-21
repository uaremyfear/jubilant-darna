package net.technoverse.sithu.darna.savehouse;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.SuperRecyclerView;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.adapters.SavehouseCityAdapter;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.fragments.savehouses.SavehouseCityFragment;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseCity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavehouseCityActivity extends AppCompatActivity {

    private String state_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savehouse_city);

        Bundle args = getIntent().getExtras();
        int id = args.getInt("state_id");

        this.setTitle("Townships");

        this.state_id = String.valueOf(id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Fragment fragment = new SavehouseCityFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).commit();
    }

    public String getState_id() {
        return state_id;
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
