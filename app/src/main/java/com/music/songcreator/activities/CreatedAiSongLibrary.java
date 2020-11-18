package com.music.songcreator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.music.songcreator.R;
import com.music.songcreator.SQLite.AISongModel;
import com.music.songcreator.SQLite.CreatedSongModel;
import com.music.songcreator.SQLite.DataBaseHelper;
import com.music.songcreator.java_operations.BeatFileSelector;
import com.music.songcreator.java_operations.DownloadedBeats;

import java.io.File;

public class CreatedAiSongLibrary extends AppCompatActivity {
    ListView lv_songlist;
    ArrayAdapter AISongArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private AdView mAdView;
    BeatFileSelector beatFileSelector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_ai_song_library);

//        mAdView = findViewById(R.id.adView13);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        lv_songlist = findViewById(R.id.CreatedAISongs_list);
        dataBaseHelper = new DataBaseHelper(CreatedAiSongLibrary.this);
        beatFileSelector = new BeatFileSelector();
        ShowAISong();

        lv_songlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(CreatedAiSongLibrary.this);
                builder.setMessage("Do you want to play or delete this AI song?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(CreatedAiSongLibrary.this);
                        builder1.setMessage("Are you sure you want to delete?");
                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AISongModel aiSongModel = (AISongModel) adapterView.getItemAtPosition(i);
                                dataBaseHelper.DeleteOneAISong(aiSongModel);
                                ShowAISong();
                            }
                        });
                        builder1.setNegativeButton("No",null);
                        builder1.show();
                    }
                });
                builder.setNegativeButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AISongModel aiSong = (AISongModel) adapterView.getItemAtPosition(i);
                        DownloadDialog(aiSong);
                    }
                });
                builder.show();
            }
        });

    }
    private void ShowAISong() {
        AISongArrayAdapter = new ArrayAdapter<>(CreatedAiSongLibrary.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone4());
        lv_songlist.setAdapter(AISongArrayAdapter);
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
    public void DownloadDialog(AISongModel aiSongModel)
    {
        File path = null;
        String beatname = beatFileSelector.FileSelector(aiSongModel.getBeatnum());
        path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatname);
        if(path.exists() != true) {
            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CreatedAiSongLibrary.this);
            builder.setMessage("Please Download '" + beatname + "' To Be Able To Play This Song.");
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
        if(path.exists())
        {
            Intent intent= new Intent(CreatedAiSongLibrary.this , CreatedSongPlay.class);
            intent.putExtra("aiSongModel", (Parcelable) aiSongModel);
            startActivity(intent);
        }
    }
}