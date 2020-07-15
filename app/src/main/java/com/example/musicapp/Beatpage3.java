package com.example.musicapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class Beatpage3 extends AppCompatActivity {
    MediaPlayer player;
    String path;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    ToggleButton T1, T2, T3;
    int check;
    boolean connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatpage3);

        T1 = findViewById(R.id.play_RB1);
        T1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connected = isConnected();
                T2.setActivated(false);
                T3.setActivated(false);
                if (T1.isChecked()) {
                    T1.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/R%26B%20Beat_1.mp3?alt=media&token=f557d7fd-588f-439e-90cc-3cbac575a615";
                            play();
                        }
                    }).start();
                    if(connected == false) {
                        T1.setChecked(false);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Beatpage3.this);
                        builder.setMessage("Please Connect to the Internet to use this feature!");
                        builder.show();
                    }
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
                connected = isConnected();
                T1.setActivated(false);
                T3.setActivated(false);
                if (T2.isChecked()) {
                    T2.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/R%26B%20Beat%202.mp3?alt=media&token=a89fb70f-1a91-47a4-b85c-bf0df1584edf";
                            play();
                        }
                    }).start();
                    if(connected == false) {
                        T2.setChecked(false);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Beatpage3.this);
                        builder.setMessage("Please Connect to the Internet to use this feature!");
                        builder.show();
                    }
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
                connected = isConnected();
                T1.setActivated(false);
                T2.setActivated(false);
                if (T3.isChecked()) {
                    T3.setActivated(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/R%26B%20Beat%203.mp3?alt=media&token=cc711559-5770-4ee5-8697-7071072a4789";
                            play();
                        }
                    }).start();
                    if(connected == false) {
                        T3.setChecked(false);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Beatpage3.this);
                        builder.setMessage("Please Connect to the Internet to use this feature!");
                        builder.show();
                    }
                }
                if (T3.isChecked() == false) {
                    T3.setActivated(false);
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
        startActivity(intent);
    }
    public boolean isConnected()
    {
        boolean result;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }
    public void play() {

            if (player != null) {
                stopPlayer();
            }
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
//    public void pause() {
//        if (player != null) {
//            player.pause();
//        }
//    }

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