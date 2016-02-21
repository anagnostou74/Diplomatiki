package gr.sextreme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by kanag on 21/Φεβ/2016.
 */
public class Splash extends MainActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        //fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 2 * 1000); // wait for 2 seconds
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}