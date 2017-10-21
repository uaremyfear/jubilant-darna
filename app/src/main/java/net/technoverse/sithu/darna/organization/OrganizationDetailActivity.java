package net.technoverse.sithu.darna.organization;

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
import net.technoverse.sithu.darna.gmodel.ModelOrganization;
import net.technoverse.sithu.darna.gmodel.ModelPhone;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class OrganizationDetailActivity extends AppCompatActivity {


    TextView txt_name,txt_description,txt_address,txt_phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_detail);

        this.setTitle("Detail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle args = getIntent().getExtras();
        int organization_id = args.getInt("organization_id");

        ApiDataloading(organization_id);
    }

    private void ApiDataloading(int organization_id)
    {
        final Call<ModelOrganization> detailCall = MainApi.createService(MainService.class).getOrganizationById(organization_id);
        detailCall.enqueue(new Callback<ModelOrganization>() {
            @Override
            public void onResponse(Call<ModelOrganization> call, Response<ModelOrganization> response) {
                ModelOrganization organization = response.body();
                Databinding(organization);
            }

            @Override
            public void onFailure(Call<ModelOrganization> call, Throwable t) {

            }
        });
    }

    private void Databinding(ModelOrganization organization)
    {
         txt_name = (TextView) findViewById(R.id.txt_name);
         txt_description = (TextView) findViewById(R.id.txt_description);
         txt_address = (TextView) findViewById(R.id.txt_address);

        ImageView img_title = (ImageView) findViewById(R.id.img_header);

        txt_name.setText(organization.getOrganization_name());
        txt_description.setText(organization.getDescription());
        txt_address.setText(organization.getAddress());

        Glide
                .with( this )
                .load(organization.getImage_url())
                .transition(withCrossFade())
                .apply(fitCenterTransform())
                .into(img_title);

        List<ModelPhone> phones = organization.getPhones();

        PhoneBinding(phones);
    }

    private void PhoneBinding(List<ModelPhone> phones) {

        txt_phone = (TextView) findViewById(R.id.txt_phone);
        String phoneNo = " ";

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

        txt_phone.setText(phoneNo);

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
