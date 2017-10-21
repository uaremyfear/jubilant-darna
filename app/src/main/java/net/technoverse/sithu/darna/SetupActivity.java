package net.technoverse.sithu.darna;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Random;

public class SetupActivity extends Activity {

    private int TIME_OUT = 3000;
    private static final Random rgenerator = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Resources res = getResources();

        String[] quoteArray = res.getStringArray(R.array.quoteArray);

        String quote = quoteArray[rgenerator.nextInt(quoteArray.length)];
        TextView tv = (TextView) findViewById(R.id.txt_quote);
        tv.setText(quote);


//        if (Build.VERSION.SDK_INT >= 21) {
//            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               switchToMain();
            }
        }, TIME_OUT);
    }

    private void switchToMain() {
        Intent intent = new Intent(SetupActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
