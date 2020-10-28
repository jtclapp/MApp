package com.music.songcreator.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.music.songcreator.R;
import com.music.songcreator.SQLite.DataBaseHelper;
import com.music.songcreator.SQLite.LyricsModel;

public class LyricsLibrary extends AppCompatActivity {
    ListView lv_lyricsList;
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

        lv_lyricsList = findViewById(R.id.lyrics_list);
        dataBaseHelper = new DataBaseHelper(LyricsLibrary.this);
        ShowLyrics();

        lv_lyricsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        lyricArrayAdapter = new ArrayAdapter<>(LyricsLibrary.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_lyricsList.setAdapter(lyricArrayAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finalactmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case  R.id.homeitem3:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
        }
        return true;
    }
}