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
        if (intvalue == 1) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#1.mp3");
            beatname = "Call Me Now";
        }
        if (intvalue == 2) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#2.mp3");
            beatname = "Blame Me";
        }
        if (intvalue == 3) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#3.mp3");
            beatname = "Uppercut";
        }
        if(intvalue == 24) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#4.mp3");
            beatname = "Only At Night";
        }
        if(intvalue == 25) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#5.mp3");
            beatname = "Too Hot For You";
        }
        if (intvalue == 4) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#1.mp3");
            beatname = "Where Did You Go";
        }
        if (intvalue == 5) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#2.mp3");
            beatname = "Just By Myself";
        }
        if (intvalue == 6) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#3.mp3");
            beatname = "Champion Of The Fight";
        }
        if (intvalue == 7) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#4.mp3");
            beatname = "Unbeatable";
        }
        if(intvalue == 29) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#5.mp3");
            beatname = "Dad And I";
        }
        if (intvalue == 8) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#1.mp3");
            beatname = "Late Night";
        }
        if (intvalue == 9) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#2.mp3");
            beatname = "Scary Night";
        }
        if(intvalue == 10) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#3.mp3");
            beatname = "On My Grind";
        }
        if(intvalue == 11) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#4.mp3");
            beatname = "On My Mind";
        }
        if(intvalue == 35) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#5.mp3");
            beatname = "Out Like A Light";
        }
        if (intvalue == 13) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#1.mp3");
            beatname = "She's With Someone Else";
        }
        if(intvalue == 14) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#2.mp3");
            beatname = "Party Time";
        }
        if(intvalue == 15) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#3.mp3");
            beatname = "Her Loss";
        }
        if(intvalue == 16) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#4.mp3");
            beatname = "This Is My Town";
        }
        if(intvalue == 17) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#5.mp3");
            beatname = "What I Love The Most";
        }

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