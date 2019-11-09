package com.minhal.fyp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minhal.fyp.R;

public class SplashActivity extends AppCompatActivity {

    //Duration of wait
    private final int SPLASH_DISPLAY_LENGTH=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        },SPLASH_DISPLAY_LENGTH);

    }
}
