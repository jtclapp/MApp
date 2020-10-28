package com.music.songcreator.activities;

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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.music.songcreator.java_operations.BeatFileSelector;
import com.music.songcreator.R;
import com.music.songcreator.SQLite.CreatedSongModel;
import com.music.songcreator.SQLite.DataBaseHelper;

import java.io.File;

public class CreatedSongsLibrary extends AppCompatActivity
{
    ListView lv_songlist;
    ArrayAdapter createdsongArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private AdView mAdView;
    BeatFileSelector beatFileSelector;
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
        beatFileSelector = new BeatFileSelector();
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
                        CreatedSongModel createdSongModel = (CreatedSongModel) adapterView.getItemAtPosition(i);
                        RecordDialog(createdSongModel.getRecordingname(),createdSongModel);
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
    public void RecordDialog(String record,CreatedSongModel createdSongModel)
    {
        File recordpath = new File(record);
        if(recordpath.exists() != true)
        {
            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CreatedSongsLibrary.this);
            builder.setMessage("The Recording Attached To This Song No Longer Exists.");
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
        if(recordpath.exists())
        {
            int beatnum = createdSongModel.getBeatnum();
            DownloadDialog(beatnum,createdSongModel);
        }
    }
    public void DownloadDialog(int intvalue,CreatedSongModel createdSongModel)
    {
        File path = null;
        String beatname = null;
        if (intvalue == 1) // Complete
        { // Complete
            beatname = "Call Me Now";
        }
        if (intvalue == 2) // Complete
        { // Complete
            beatname = "Blame Me";
        }
        if (intvalue == 3) // Complete
        { // Complete
            beatname = "Uppercut";
        }
        if(intvalue == 24) // Complete
        {
            beatname = "Only At Night";
        }
        if(intvalue == 25) // Complete
        {
            beatname = "Too Hot For You";
        }
        if(intvalue == 26) // Complete
        {
            beatname = "Just Go";
        }
        if(intvalue == 27) // Complete
        {
            beatname = "Let Me In";
        }
        if (intvalue == 4) // Complete
        { // Complete
            beatname = "Where Did You Go";
        }
        if (intvalue == 5) // Complete
        { // Complete
            beatname = "Just By Myself";
        }
        if (intvalue == 6) // Complete
        { // Complete
            beatname = "Champion Of The Fight";
        }
        if (intvalue == 7) // Complete
        { // Complete
            beatname = "Unbeatable";
        }
        if(intvalue == 29) // Complete
        {
            beatname = "Dad And I";
        }
        if (intvalue == 8) // Complete
        { // Complete
            beatname = "Late Night";
        }
        if (intvalue == 9) // Complete
        { // Complete
            beatname = "Scary Night";
        }
        if(intvalue == 10) // Complete
        { // Complete
            beatname = "On My Grind";
        }
        if(intvalue == 11) // Complete
        {
            beatname = "On My Mind";
        }
        if(intvalue == 35) // Complete
        {
            beatname = "Out Like A Light";
        }
        if(intvalue == 36) // Complete
        {
            beatname = "No Sleep For The Damned";
        }
        if (intvalue == 13) // Complete
        { // Complete
            beatname = "She's With Someone Else";
        }
        if(intvalue == 14) // Complete
        {
            beatname = "Party Time";
        }
        if(intvalue == 15) // Complete
        {
            beatname = "Her Loss";
        }
        if(intvalue == 16) // Complete
        {
            beatname = "This Is My Town";
        }
        if(intvalue == 17) // Complete
        {
            beatname = "What I Love The Most";
        }
        path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatFileSelector.FileSelector(intvalue));
        if(path.exists() != true) {
            final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CreatedSongsLibrary.this);
            builder.setMessage("Please Download '" + beatname + "' To Be Able To Play This Song.");
            builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intentdownload = new Intent(getApplicationContext(), DownloadedBeats.class);
                    startActivity(intentdownload);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();
        }
        if(path.exists())
        {
            Intent intent= new Intent(CreatedSongsLibrary.this , CreatedSongPlay.class);
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
    }
}