package net.technoverse.sithu.darna;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



import net.technoverse.sithu.darna.utlis.Const;
import net.technoverse.sithu.darna.utlis.TypefaceManager;


/**
 * Created by sumyatmon on 9/23/16.
 */
public class MainApplication extends Application {


    public static TypefaceManager typefaceManager;
    protected static MainApplication instance;
    private SharedPreferences sharedPreferences;

    public MainApplication() {
        super();
        instance = this;

    }

    public static MainApplication get() {
        if(instance == null)
        {
            instance = new MainApplication();
        }
        return instance;
    }

    public SharedPreferences getSharePre(Context context) {
//        if(mSecurePrefs==null){
//            mSecurePrefs = new SecurePreferences(this, "", "my_prefs");
//            SecurePreferences.setLoggingEnabled(true);
//        }

        sharedPreferences= context.getSharedPreferences(Const.MYPREF, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

//    public void setSharedPreferences(Context context){
//        sharedPreferences= context.getSharedPreferences(Const.MYPREF, Context.MODE_PRIVATE);
////        SPEditor= SPEditor.edit();
//    }


    public SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

       // TypefaceProvider.registerDefaultIconSets();
        typefaceManager = new TypefaceManager(getAssets());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
