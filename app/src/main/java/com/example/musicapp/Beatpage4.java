package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Beatpage4 extends AppCompatActivity {

    MediaPlayer player;
    Uri path;
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
                            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.country_beat_1);
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
        if (player == null) {
            player = MediaPlayer.create(getApplicationContext(), path);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });

        }
        player.start();
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