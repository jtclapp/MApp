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

public class Beatpage3 extends AppCompatActivity {
    MediaPlayer player;
    File path;
    int check;
    SeekBar volumeadj;
    float setVolume;
    RadioGroup radioGroup;
    RadioButton R1,R2,R3,R4,R5;
    ToggleButton FinalPlay;
    Button next;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatpage3);
        mAdView = findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        radioGroup = findViewById(R.id.Group3);
        R1 = findViewById(R.id.RB_checkbox1);
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R1.isChecked()) {
                    check = 8;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#1.mp3");
                    DownloadDialog();
                }
            }
        });
        R2 = findViewById(R.id.RB_checkbox2);
        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R2.isChecked()) {
                    check = 9;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#2.mp3");
                    DownloadDialog();
                }
            }
        });
        R3 = findViewById(R.id.RB_checkbox3);
        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(R3.isChecked())
                {
                    check = 10;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#3.mp3");
                    DownloadDialog();
                }
            }
        });
        R4 = findViewById(R.id.RB_checkbox4);
        R4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(R4.isChecked())
                {
                    check = 11;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#4.mp3");
                    DownloadDialog();
                }
            }
        });
        R5 = findViewById(R.id.RB_checkbox5);
        R5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(R5.isChecked())
                {
                    check = 35;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#5.mp3");
                    DownloadDialog();
                }
            }
        });
        FinalPlay = findViewById(R.id.Beat3Play);
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
        next = findViewById(R.id.nextpage3);
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
        volumeadj = findViewById(R.id.Volume3);
    }
    public void openMainAct5() {
        int id = 3;
        Intent intent = new Intent(this, MainActivity5.class);
        if (check == 8) {
            intent.putExtra("check", 8);
        }
        if (check == 9) {
            intent.putExtra("check", 9);
        }
        if(check == 10)
        {
            intent.putExtra("check",10);
        }
        if(check == 11)
        {
            intent.putExtra("check",11);
        }
        if(check == 35)
        {
            intent.putExtra("check",35);
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
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(Beatpage3.this);
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