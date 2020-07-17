package com.example.musicapp;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;

public class BeatPage1 extends AppCompatActivity {
    MediaPlayer player;
    File path;
    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;
    ToggleButton T1, T2, T3;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beat1_activity);

        T1 = findViewById(R.id.play_hiphop1);
        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                T2.setActivated(false);
                T3.setActivated(false);
                if (T1.isChecked()) {
                    T1.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#1.mp3");
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
        T2 = findViewById(R.id.play_hiphop2);
        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T1.setActivated(false);
                T3.setActivated(false);
                if (T2.isChecked()) {
                    T2.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#2.mp3");
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
        T3 = findViewById(R.id.play_hiphop3);
        T3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T1.setActivated(false);
                T2.setActivated(false);
                if (T3.isChecked()) {
                    T3.setActivated(true);
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#3.mp3");
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
        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    check = 1;
                    openMainAct5();
                }
            }
        });
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    check = 2;
                    openMainAct5();
                }
            }
        });
        checkBox3 = findViewById(R.id.checkBox4);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    check = 3;
                    openMainAct5();
                }
            }
        });
    }

    public void openMainAct5() {
        Intent intent = new Intent(this, MainActivity5.class);

        if (check == 1) {
            intent.putExtra("check", 1);
        }
        if (check == 2) {
            intent.putExtra("check", 2);
        }
        if (check == 3) {
            intent.putExtra("check", 3);
        }
        startActivity(intent);
    }

    public void play() {

            if (player != null) {
                stopPlayer();
            }
            player = new MediaPlayer();
            try {
                player.setDataSource(String.valueOf(path));
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.start();
                    }
                });
                player.prepare();
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(BeatPage1.this);
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