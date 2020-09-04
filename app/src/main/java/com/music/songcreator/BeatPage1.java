package com.music.songcreator;

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

public class BeatPage1 extends AppCompatActivity {
    MediaPlayer player;
    File path;
    RadioGroup radioGroup;
    RadioButton R1,R2,R3,R4,R5,R6,R7;
    ToggleButton FinalPlay;
    Button next;
    int check;
    SeekBar volumeadj;
    float setVolume;
    private AdView mAdView;
    BeatFileSelector beatFileSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beat1_activity);
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        beatFileSelector = new BeatFileSelector();
        radioGroup = findViewById(R.id.Group1);
        R1 = findViewById(R.id.checkBox);
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    check = 1;
                    DownloadDialog();
            }
        });
        R2 = findViewById(R.id.checkBox2);
        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    check = 2;
                    DownloadDialog();
            }
        });
        R3 = findViewById(R.id.checkBox4);
        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    check = 3;
                    DownloadDialog();
            }
        });
        R4 = findViewById(R.id.checkBox5);
        R4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 24;
                DownloadDialog();
            }
        });
        R5 = findViewById(R.id.checkBox6);
        R5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 25;
                DownloadDialog();
            }
        });
        R6 = findViewById(R.id.checkBox7);
        R6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 26;
                DownloadDialog();
            }
        });
        R7 = findViewById(R.id.checkBox8);
        R7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = 27;
                DownloadDialog();
            }
        });
        FinalPlay = findViewById(R.id.Beat1Play);
        FinalPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
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
        next = findViewById(R.id.nextpage1);
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
        next.setActivated(false);
        volumeadj = findViewById(R.id.Volume);
    }
    public void openMainAct5() {
        int id = 1;
        Intent intent = new Intent(this, MainActivity5.class);
        intent.putExtra("check", check);

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
        path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatFileSelector.FileSelector(check));

        if(path.exists() != true) {
            next.setActivated(false);
            FinalPlay.setEnabled(false);
            final AlertDialog.Builder builder = new AlertDialog.Builder(BeatPage1.this);
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