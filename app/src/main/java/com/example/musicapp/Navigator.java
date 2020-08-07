package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Navigator extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        mAdView = findViewById(R.id.adView6);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        button = findViewById(R.id.ViewLyrics);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLibrary();
            }
        });
        button2 = findViewById(R.id.ViewRecordings);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordingLibrary();
            }
        });
        button3 = findViewById(R.id.ViewDownloadedBeats);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDownloadedBeats();
            }
        });
        button4 = findViewById(R.id.ViewCreatedSongs);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreatedSongs();
            }
        });
    }
    public void openLibrary() {
        Intent intent = new Intent(this, LyricsLibrary.class);
        startActivity(intent);
    }

    public void openRecordingLibrary() {
        Intent intent = new Intent(this, RecordingLibrary.class);
        startActivity(intent);
    }

    public void openDownloadedBeats()
    {
        Intent intent = new Intent(this,DownloadedBeats.class);
        startActivity(intent);
    }
    public void openCreatedSongs()
    {
        Intent intent = new Intent(this,CreatedSongsLibrary.class);
        startActivity(intent);
    }
}