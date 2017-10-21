package net.technoverse.sithu.darna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.technoverse.sithu.darna.foundation.FoundationActivity;
import net.technoverse.sithu.darna.organization.OrganizationActivity;
import net.technoverse.sithu.darna.savehouse.SavehouseActivity;
import net.technoverse.sithu.darna.thadin.ThadinActivity;
import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;

import org.w3c.dom.Text;

import me.myatminsoe.mdetect.MDetect;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MDetect.INSTANCE.init(this);

        SetFonts();
    }

    private void SetFonts() {

        MainApplication mainApp = MainApplication.get();
        //mainApp.setSharedPreferences(getApplicationContext());
        SharedPreferences mSharedPref = mainApp.getSharePre(getApplicationContext());
        if (MDetect.INSTANCE.isUnicode()){
            mSharedPref.edit().putBoolean(Const.CAN_SEE_UNICODE, true).apply();
        } else {
            mSharedPref.edit().putBoolean(Const.CAN_SEE_UNICODE, false).apply();

            TypefaceManager typefaceManager;
            typefaceManager = new TypefaceManager( getApplicationContext().getAssets() );

            TextView text_savehouse = (TextView) findViewById(R.id.text_savehouse);
            TextView text_organization = (TextView) findViewById(R.id.text_organization);
            TextView text_foundation = (TextView) findViewById(R.id.text_foundation);
            TextView text_news = (TextView) findViewById(R.id.text_news);
//            TextView text_event = (TextView) findViewById(R.id.text_event);

            text_savehouse.setTypeface( typefaceManager.getShitUnicde() );
            text_organization.setTypeface( typefaceManager.getShitUnicde() );
            text_foundation.setTypeface( typefaceManager.getShitUnicde() );
            text_news.setTypeface( typefaceManager.getShitUnicde() );
//            text_event.setTypeface( typefaceManager.getShitUnicde() );


        }
    }

    @Override
    public void onBackPressed() {
        Log.d("BACKPRESSED", "Is DONE");
        System.exit(0);
//        super.onBackPressed();
    }

    public void callSavehouse(View v)
    {
        Intent intent = new Intent(MainActivity.this,SavehouseActivity.class);
        startActivity(intent);
    }

    public void callOrganization(View v)
    {
        Intent intent = new Intent(MainActivity.this, OrganizationActivity.class);
        startActivity(intent);
    }

    public void callFoundation(View v)
    {
        Intent intent = new Intent(MainActivity.this, FoundationActivity.class);
        startActivity(intent);
    }

    public void callNews(View v)
    {
        Intent intent = new Intent(MainActivity.this, ThadinActivity.class);
        startActivity(intent);
    }

    public void callEvent(View v)
    {
        Toast.makeText(this, "Content Coming Soon", Toast.LENGTH_SHORT).show();
    }
}
