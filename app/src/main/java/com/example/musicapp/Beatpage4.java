package com.example.musicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;
import java.io.IOException;

public class Beatpage4 extends AppCompatActivity {

    MediaPlayer player;
    File path;
    int check;
    SeekBar volumeadj;
    float setVolume;
    RadioButton R1;
    RadioGroup radioGroup;
    ToggleButton FinalPlay;
    Button next;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatpage4);
        mAdView = findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        radioGroup = findViewById(R.id.Group4);
        R1 = findViewById(R.id.country_box1);
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R1.isChecked()) {
                    check = 13;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#1.mp3");
                    DownloadDialog();
                }
            }
        });
        FinalPlay = findViewById(R.id.Beat4Play);
        FinalPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FinalPlay.isChecked()) {
                    FinalPlay.setActivated(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DownloadDialog();
                            play();
                        }
                    }).start();
                }
                if (FinalPlay.isChecked() == false) {
                    FinalPlay.setActivated(false);
                    stopPlayer();
                }
            }
        });
        next = findViewById(R.id.nextpage4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(next.isActivated())
                {
                    openMainAct5();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Select a Beat to Continue",Toast.LENGTH_SHORT).show();
                }
            }
        });
        volumeadj = findViewById(R.id.Volume4);
    }
    public void openMainAct5() {
        int id = 4;
        Intent intent = new Intent(this, MainActivity5.class);
        if (check == 13) {
            intent.putExtra("check", 13);
        }
        setVolume = (float) volumeadj.getProgress() / 50;
        if (setVolume < 0.1) {setVolume = 0.1f;}
        intent.putExtra("setVolume",setVolume);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    public void play() {
            if (player != null) {
                stopPlayer();
            }
            player = new MediaPlayer();
            setVolume = (float) volumeadj.getProgress() / 50;
            if (setVolume < 0.1) {setVolume = 0.1f;}
            try {
                player.setDataSource(String.valueOf(path));
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.start();
                    }
                });
                player.prepare();
                player.setVolume(setVolume,setVolume);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//    public void pause() {
//        if (player != null) {
//            player.pause();
//        }
//    }
    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
    public void DownloadDialog()
    {
        if(path.exists() != true) {
            next.setActivated(false);
            FinalPlay.setEnabled(false);
            final AlertDialog.Builder builder = new AlertDialog.Builder(Beatpage4.this);
            builder.setMessage("Please Download This Beat To Play It.");
            builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    radioGroup.clearCheck();
                    Intent intentdownload = new Intent(getApplicationContext(), DownloadedBeats.class);
                    startActivity(intentdownload);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    radioGroup.clearCheck();
                }
            });
            builder.show();
        }
        if(path.exists() == true)
        {
            next.setActivated(true);
            FinalPlay.setEnabled(true);
        }
    }
}