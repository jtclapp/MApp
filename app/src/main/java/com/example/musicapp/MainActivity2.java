package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    MediaPlayer player;
    Uri path;
    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;
    ToggleButton T1, T2, T3;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        T1 = findViewById(R.id.play_hiphop1);
        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T2.setActivated(false);
                T3.setActivated(false);
                if (T1.isChecked()) {

                    T1.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hiphop_beat_1);
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
        T2 = findViewById(R.id.play_hiphop2);
        T2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T1.setActivated(false);
                T3.setActivated(false);
                if (T2.isChecked()) {
                    T2.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hiphop_beat_2);
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
        T3 = findViewById(R.id.play_hiphop3);
        T3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T1.setActivated(false);
                T2.setActivated(false);
                if (T3.isChecked()) {
                    T3.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_04_out_cold);
                            play();
                        }
                    }).start();
                }
                if (T3.isChecked() == false) {
                    T3.setActivated(false);
                    pause();
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