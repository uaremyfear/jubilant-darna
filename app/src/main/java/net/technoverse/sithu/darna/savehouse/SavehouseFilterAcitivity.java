package net.technoverse.sithu.darna.savehouse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.fragments.savehouses.SavehouseFragment;

public class SavehouseFilterAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savehouse_filter_acitivity);

        Fragment fragment = new SavehouseFragment();

        Bundle args = getIntent().getExtras();
        String type = args.getString("type");

        Bundle bundle = new Bundle();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if(type.equals("byLocation"))
        {
            bundle.putString("type","byLocation");
            bundle.putInt("city_id",args.getInt("city_id"));
        }
        else if(type.equals("byCategory")){
            bundle.putString("type","byCategory");
            bundle.putInt("category_id",args.getInt("category_id"));
        }

        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).commit();
//        .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

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
