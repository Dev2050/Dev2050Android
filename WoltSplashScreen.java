package com.wolt.devname.openinghours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WoltSplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                // Starts WoltMainActivity
                Intent i = new Intent(WoltSplashScreen.this, WoltMainActivity.class);
                startActivity(i);

                // closes this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
