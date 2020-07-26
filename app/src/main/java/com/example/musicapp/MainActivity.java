package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button storagebutton;
    private Button settingsbutton;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeatpage1();
            }
        });
        button2 = findViewById(R.id.button5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeatpage2();
            }
        });
        button3 = findViewById(R.id.R_B_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeatpage3();
            }
        });
        button4 = findViewById(R.id.country_button);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBeatpage4();
            }
        });
        storagebutton = findViewById(R.id.storagebutton);
        storagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNav();
            }
        });
        settingsbutton = findViewById(R.id.settingsbutton);
        settingsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });
    }
    public void openBeatpage1() {
        Intent intent = new Intent(this, BeatPage1.class);
        startActivity(intent);
    }

    public void openBeatpage2() {
        Intent intent = new Intent(this, BeatPage2.class);
        startActivity(intent);
    }

    public void openBeatpage3() {
        Intent intent = new Intent(this, Beatpage3.class);
        startActivity(intent);
    }

    public void openBeatpage4() {
        Intent intent = new Intent(this, Beatpage4.class);
        startActivity(intent);
    }
    public void openNav()
    {
        Intent intent = new Intent(this,Navigator.class);
        startActivity(intent);
    }
    public void openSettings()
    {
        Intent intent = new Intent(this,SettingsPage.class);
        startActivity(intent);
    }
}