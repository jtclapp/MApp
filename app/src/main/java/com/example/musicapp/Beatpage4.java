package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Beatpage4 extends AppCompatActivity {

    MediaPlayer player;
    String path;
    CheckBox checkBox1;
    ToggleButton T1;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatpage4);

        T1 = findViewById(R.id.play_country1);
        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T1.isChecked()) {
                    T1.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Country%20beat%201.mp3?alt=media&token=394ccfa6-ca62-4fee-b646-9e821e1b56ba";
                            play();
                        }
                    }).start();
                }
                if (T1.isChecked() == false) {
                    T1.setActivated(false);
                    pause();
                }
            }
        });
        checkBox1 = findViewById(R.id.country_box1);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    check = 13;
                    openMainAct5();
                }
            }
        });
    }

    public void openMainAct5() {
        Intent intent = new Intent(this, MainActivity5.class);
        if (check == 13) {
            intent.putExtra("check", 13);
        }
        startActivity(intent);
    }

    public void play() {
        if (player != null) {
            stopPlayer();
        }
        player = new MediaPlayer();
        try {
            player.setDataSource(path);
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

    public void pause() {
        if (player != null) {
            player.pause();
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
}