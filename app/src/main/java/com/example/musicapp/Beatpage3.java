package com.example.musicapp;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;

public class Beatpage3 extends AppCompatActivity {
    MediaPlayer player;
    File path;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    ToggleButton T1, T2, T3, T4;
    int check;
    SeekBar volumeadj;
    float setVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatpage3);

        T1 = findViewById(R.id.play_RB1);
        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T2.setActivated(false);
                T3.setActivated(false);
                T4.setActivated(false);
                if (T1.isChecked()) {
                    T1.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#1.mp3");
                    DownloadDialog();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            play();
                        }
                    }).start();
                }
                if (T1.isChecked() == false) {
                    T1.setActivated(false);
                    stopPlayer();
                }
            }
        });
        T2 = findViewById(R.id.play_RB2);
        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T1.setActivated(false);
                T3.setActivated(false);
                T4.setActivated(false);
                if (T2.isChecked()) {
                    T2.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#2.mp3");
                    DownloadDialog();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            play();
                        }
                    }).start();
                }
                if (T2.isChecked() == false) {
                    T2.setActivated(false);
                    stopPlayer();
                }
            }
        });
        T3 = findViewById(R.id.play_RB3);
        T3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                T1.setActivated(false);
                T2.setActivated(false);
                T4.setActivated(false);
                if (T3.isChecked()) {
                    T3.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#3.mp3");
                    DownloadDialog();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            play();
                        }
                    }).start();
                }
                if (T3.isChecked() == false) {
                    T3.setActivated(false);
                    stopPlayer();
                }
            }
        });
        T4 = findViewById(R.id.play_RB4);
        T4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                T1.setActivated(false);
                T2.setActivated(false);
                T3.setActivated(false);
                if (T4.isChecked()) {
                    T4.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#4.mp3");
                    DownloadDialog();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            play();
                        }
                    }).start();
                }
                if (T4.isChecked() == false) {
                    T4.setActivated(false);
                    stopPlayer();
                }
            }
        });
        checkBox1 = findViewById(R.id.RB_checkbox1);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    check = 8;
                    openMainAct5();
                }
            }
        });
        checkBox2 = findViewById(R.id.RB_checkbox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    check = 9;
                    openMainAct5();
                }
            }
        });
        checkBox3 = findViewById(R.id.RB_checkbox3);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checkBox3.isChecked())
                {
                    check = 10;
                    openMainAct5();
                }
            }
        });
        checkBox4 = findViewById(R.id.RB_checkbox4);
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox4.isChecked())
                {
                    check = 11;
                    openMainAct5();
                }
            }
        });
        volumeadj = findViewById(R.id.Volume3);
    }
    public void openMainAct5() {
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
        setVolume = (float) volumeadj.getProgress() / 50;
        if (setVolume < 0.1) {setVolume = 0.1f;}
        intent.putExtra("setVolume",setVolume);
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(Beatpage3.this);
            builder.setMessage("Please Download This Beat To Play It.");
            builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intentdownload = new Intent(getApplicationContext(), DownloadedBeats.class);
                    startActivity(intentdownload);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        }
    }
}