package net.technoverse.sithu.darna.foundation;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.technoverse.sithu.darna.MainApplication;
import net.technoverse.sithu.darna.R;
import net.technoverse.sithu.darna.api.MainApi;
import net.technoverse.sithu.darna.api.MainService;
import net.technoverse.sithu.darna.gmodel.ModelFoundation;
import net.technoverse.sithu.darna.gmodel.ModelPhone;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class FoundationDetailActivity extends AppCompatActivity {

    TextView txt_name , txt_description , txt_address , txt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_detail);

        this.setTitle("Detail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle args = getIntent().getExtras();
        int foundation_id = args.getInt("foundation_id");

        ApiDataloading(foundation_id);
    }

    private void ApiDataloading(int foundation_id)
    {
        final Call<ModelFoundation> detailCall = MainApi.createService(MainService.class).getFoundationById(foundation_id);
        detailCall.enqueue(new Callback<ModelFoundation>() {
            @Override
            public void onResponse(Call<ModelFoundation> call, Response<ModelFoundation> response) {
                ModelFoundation foundation = response.body();
                Databinding(foundation);
            }

            @Override
            public void onFailure(Call<ModelFoundation> call, Throwable t) {

            }
        });
    }

    private void Databinding(ModelFoundation foundation)
    {
         txt_name = (TextView) findViewById(R.id.txt_name);
         txt_description = (TextView) findViewById(R.id.txt_description);
         txt_address = (TextView) findViewById(R.id.txt_address);
         txt_phone = (TextView) findViewById(R.id.txt_phone);

        ImageView img_title = (ImageView) findViewById(R.id.img_header);

        SharedPreferences sharedPreferences;
        sharedPreferences = MainApplication.get().getSharePre(getApplicationContext());

        if (!sharedPreferences.getBoolean( Const.CAN_SEE_UNICODE, true )) {
            TypefaceManager typefaceManager;
            typefaceManager = new TypefaceManager( getApplicationContext().getAssets() );
            txt_name.setTypeface( typefaceManager.getShitUnicde() );
            txt_description.setTypeface( typefaceManager.getShitUnicde() );
            txt_address.setTypeface( typefaceManager.getShitUnicde() );
            txt_phone.setTypeface(typefaceManager.getShitUnicde());
        }

        txt_name.setText(foundation.getFoundation_name());
        txt_description.setText(foundation.getDescription());
        txt_address.setText(foundation.getAddress());

        Glide
                .with( this )
                .load(foundation.getImage_url())
                .transition(withCrossFade())
                .apply(fitCenterTransform())
                .into(img_title);

        List<ModelPhone> phones = foundation.getPhones();

        PhoneBinding(phones);
    }

    private void PhoneBinding(List<ModelPhone> phones) {

        txt_phone = (TextView) findViewById(R.id.txt_phone);
        String phoneNo = "";

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

        txt_phone.setText(phoneNo);
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
