package com.music.songcreator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.MobileAds;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");


        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2300)
                .withBackgroundColor(Color.parseColor("#FFFFFF"));
                config.withLogo(R.mipmap.universal_logo2);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}