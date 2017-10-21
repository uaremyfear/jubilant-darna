package net.technoverse.sithu.darna.savehouse;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.technoverse.sithu.darna.MainApplication;
import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelPhone;
import net.technoverse.sithu.darna.gmodel.ModelSavehouseDetail;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class SavehouseDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView txt_name,txt_address,txt_description,txt_phones;
    ImageView img_title;
    float lat,lng;
    String title;
    LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savehouse_detail);

        this.setTitle("Detail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle args = getIntent().getExtras();
        int savehouse_id = args.getInt("savehouse_id");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ApiDataloading(savehouse_id);

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

    private void ApiDataloading(int savehouse_id) {
        final Call<ModelSavehouseDetail> savehouseDetailCall = MainApi.createService(MainService.class).getSavehouseDetail(savehouse_id);
        savehouseDetailCall.enqueue(new Callback<ModelSavehouseDetail>() {
            @Override
            public void onResponse(Call<ModelSavehouseDetail> call, Response<ModelSavehouseDetail> response) {
                ModelSavehouseDetail savehouseDetail = response.body();
                Databinding(savehouseDetail);
            }

            @Override
            public void onFailure(Call<ModelSavehouseDetail> call, Throwable t) {

            }
        });
    }

    private void Databinding(ModelSavehouseDetail savehouseDetail) {


        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_name.setText(savehouseDetail.getSavehouse_name());

        txt_address = (TextView) findViewById(R.id.txt_address);
        txt_address.setText(savehouseDetail.getAddress());

        txt_description = (TextView) findViewById(R.id.txt_description);
        txt_description.setText(savehouseDetail.getDescription());


        SharedPreferences sharedPreferences;
        sharedPreferences = MainApplication.get().getSharePre(getApplicationContext());

        if (!sharedPreferences.getBoolean( Const.CAN_SEE_UNICODE, true )) {
            TypefaceManager typefaceManager;
            typefaceManager = new TypefaceManager( getApplicationContext().getAssets() );
            txt_name.setTypeface( typefaceManager.getShitUnicde() );
            txt_description.setTypeface( typefaceManager.getShitUnicde() );
            txt_address.setTypeface( typefaceManager.getShitUnicde() );
        }

        img_title = (ImageView) findViewById(R.id.img_header);

        Glide
                .with( this )
                .load(savehouseDetail.getImage_url())
                .transition(withCrossFade())
                .apply(fitCenterTransform())
                .into(img_title);

        List<ModelPhone> phones = savehouseDetail.getPhones();

        PhoneBinding(phones);
    }



    private void PhoneBinding(List<ModelPhone> phones) {

        txt_phones = (TextView) findViewById(R.id.txt_phone);
        String phoneNo = "";

//        for(ModelPhone phone : phones) {
//            phoneNo += phone.getPhone_number() + "\n";
//        }

        for(int i = 1; i <= phones.size(); i++) {
            if (i != phones.size())
            {
                phoneNo += phones.get(i-1).getPhone_number() + ", ";
            }
            else
            {
                phoneNo += phones.get(i-1).getPhone_number();
            }
        }

        txt_phones.setText(phoneNo);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        Bundle args = getIntent().getExtras();
        int savehouse_id = args.getInt("savehouse_id");

        final Call<ModelSavehouseDetail> savehouseDetailCall = MainApi.createService(MainService.class).getSavehouseDetail(savehouse_id);
        savehouseDetailCall.enqueue(new Callback<ModelSavehouseDetail>() {
            @Override
            public void onResponse(Call<ModelSavehouseDetail> call, Response<ModelSavehouseDetail> response) {
                ModelSavehouseDetail savehouseDetail = response.body();

                lat = savehouseDetail.getLat();
                lng = savehouseDetail.getLng();
                title = savehouseDetail.getSavehouse_name();

                location = new LatLng(lat, lng);
                googleMap.addMarker(new MarkerOptions().position(location)
                        .title(title));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location , 16.0f));
            }

            @Override
            public void onFailure(Call<ModelSavehouseDetail> call, Throwable t) {

            }
        });
    }
}
