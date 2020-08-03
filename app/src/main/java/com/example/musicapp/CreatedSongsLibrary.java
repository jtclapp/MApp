package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;

public class CreatedSongsLibrary extends AppCompatActivity
{
    ListView lv_songlist;
    ArrayAdapter createdsongArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_songs_library);

        mAdView = findViewById(R.id.adView12);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lv_songlist = findViewById(R.id.CreatedSongs_list);
        dataBaseHelper = new DataBaseHelper(CreatedSongsLibrary.this);
        ShowSong();

        lv_songlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CreatedSongsLibrary.this);
                builder.setMessage("Do you want to play or delete this song?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(CreatedSongsLibrary.this);
                        builder1.setMessage("Are you sure you want to delete?");
                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CreatedSongModel createdSongModel = (CreatedSongModel) adapterView.getItemAtPosition(i);
                                dataBaseHelper.DeleteOneSong(createdSongModel);
                                ShowSong();
                            }
                        });
                        builder1.setNegativeButton("No",null);
                        builder1.show();
                    }
                });
                builder.setNegativeButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(CreatedSongsLibrary.this , CreatedSongPlay.class);
                        CreatedSongModel createdSongModel = (CreatedSongModel) adapterView.getItemAtPosition(i);
                        String Title = createdSongModel.getSongname();
                        String RecordingName = createdSongModel.getRecordingname();
                        String Lyrics = createdSongModel.getLyricsname();
                        int beatnum = createdSongModel.getBeatnum();
                        intent.putExtra("Title",Title);
                        intent.putExtra("RecordingName",RecordingName);
                        intent.putExtra("Lyrics",Lyrics);
                        intent.putExtra("beatnum",beatnum);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
    private void ShowSong() {
        createdsongArrayAdapter = new ArrayAdapter<>(CreatedSongsLibrary.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone3());
        lv_songlist.setAdapter(createdsongArrayAdapter);
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