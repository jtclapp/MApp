package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    MediaPlayer player;
    Uri path;
    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;
    ImageButton I1;
    ImageButton I2;
    ImageButton I3;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        I1 = findViewById(R.id.imageButton);
        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.beat1);
                play(v);
            }
        });
        I2 = findViewById(R.id.imageButton3);
        I2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_03_streets);
                play(v);
            }
        });
        I3 = findViewById(R.id.imageButton6);
        I3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_04_out_cold);
                play(v);
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

    public void play(View view) {
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

    public void stop(View view) {
        stopPlayer();
    }

    //    public void pause(View v)
//        {
//            if(player != null)
//            {
//                player.pause();
//            }
//        }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
            Toast.makeText(getApplicationContext(), "MediaPlayer Released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}