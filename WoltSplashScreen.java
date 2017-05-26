package com.wolt.fissha.openinghours;

/**
 * Created by Fissha on 23/05/2017.
 */
/**
 * Created by Fissha on 23/05/2017.
 */

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

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                // Starts WoltMain activity
                Intent i = new Intent(WoltSplashScreen.this, WoltMainActivity.class);
                startActivity(i);

                // closes this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
