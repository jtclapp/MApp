package com.example.musicapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class LyricsLibrary extends AppCompatActivity {
    ListView lv_customerList;
    ArrayAdapter lyricArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics_library);
        mAdView = findViewById(R.id.adView8);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lv_customerList = findViewById(R.id.lyrics_list);
        dataBaseHelper = new DataBaseHelper(LyricsLibrary.this);
        ShowLyrics();

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LyricsLibrary.this);
                builder.setMessage("Are you sure you want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        LyricsModel clickedLyric = (LyricsModel) adapterView.getItemAtPosition(i);
                        dataBaseHelper.DeleteOne(clickedLyric);
                        ShowLyrics();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    private void ShowLyrics() {
        lyricArrayAdapter = new ArrayAdapter<LyricsModel>(LyricsLibrary.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(lyricArrayAdapter);
    }
}