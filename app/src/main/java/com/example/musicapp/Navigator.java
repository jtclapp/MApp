package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Navigator extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

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

    }
}