package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Beatpage3 extends AppCompatActivity {
    MediaPlayer player;
    Uri path;
    CheckBox checkBox1;
    CheckBox checkBox2;
    ToggleButton T1, T2;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatpage3);

        T1 = findViewById(R.id.play_RB1);
        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T2.setActivated(false);
                if (T1.isChecked()) {
                    T1.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.randb_beat_1);
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
        T2 = findViewById(R.id.play_RB2);
        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T1.setActivated(false);
                if (T2.isChecked()) {
                    T2.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.randb_beat_2);
                            play();
                        }
                    }).start();
                }
                if (T2.isChecked() == false) {
                    T2.setActivated(false);
                    pause();
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
    }
    public void openMainAct5() {
        Intent intent = new Intent(this, MainActivity5.class);
        if (check == 8) {
            intent.putExtra("check", 8);
        }
        if (check == 9) {
            intent.putExtra("check", 9);
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