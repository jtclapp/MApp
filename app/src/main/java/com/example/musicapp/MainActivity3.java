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

public class MainActivity3 extends AppCompatActivity {
    MediaPlayer player;
    Uri path;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    ImageButton I1;
    ImageButton I2;
    ImageButton I3;
    ImageButton I4;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        I1 = findViewById(R.id.imageButton);
        I1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chad_crouch_shipping_lanes);
                play(v);
            }
        });
        I2 = findViewById(R.id.imageButton3);
        I2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.mid_air_machine_underneath_the_world);
                play(v);
            }
        });
        I3 = findViewById(R.id.imageButton5);
        I3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.the_new_monitors_11_last_day_on_the_job);
                play(v);
            }
        });
        I4 = findViewById(R.id.imageButton7);
        I4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.scott_holmes_04_upbeat_party);
                play(v);
            }
        });
        checkBox1 = findViewById(R.id.checkBox3);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    check = 4;
                    openMainAct5();
                }
            }
        });
        checkBox2 = findViewById(R.id.checkBox5);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    check = 5;
                    openMainAct5();
                }
            }
        });
        checkBox3 = findViewById(R.id.checkBox6);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    check = 6;
                    openMainAct5();
                }
            }
        });
        checkBox4 = findViewById(R.id.checkBox7);
        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox4.isChecked()) {
                    check = 7;
                    openMainAct5();
                }
            }
        });
    }

    public void openMainAct5() {
        Intent intent = new Intent(this, MainActivity5.class);
        if (check == 4) {
            intent.putExtra("check", 4);
        }
        if (check == 5) {
            intent.putExtra("check", 5);
        }
        if (check == 6) {
            intent.putExtra("check", 6);
        }
        if (check == 7) {
            intent.putExtra("check", 7);
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

    //    public void pause(View v)
//    {
//        if(player != null)
//        {
//            player.pause();
//        }
//    }
    public void stop(View view) {
        stopPlayer();
    }

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
