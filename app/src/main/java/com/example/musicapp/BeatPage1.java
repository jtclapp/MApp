package com.example.musicapp;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.IOException;

public class BeatPage1 extends AppCompatActivity {
    MediaPlayer player;
    File path;
    RadioButton R1,R2,R3;
    ToggleButton FinalPlay;
    Button next;
    int check;
    SeekBar volumeadj;
    float setVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beat1_activity);


        R1 = findViewById(R.id.checkBox);
        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R1.isChecked()) {
                    check = 1;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#1.mp3");
                    DownloadDialog();
                    next.setEnabled(true);
                }
            }
        });
        R2 = findViewById(R.id.checkBox2);
        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R2.isChecked()) {
                    check = 2;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#2.mp3");
                    DownloadDialog();
                    next.setEnabled(true);
                }
            }
        });
        R3 = findViewById(R.id.checkBox4);
        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (R3.isChecked()) {
                    check = 3;
                    path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#3.mp3");
                    DownloadDialog();
                    next.setEnabled(true);
                }
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
                openMainAct5();
            }
        });
        volumeadj = findViewById(R.id.Volume);
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(BeatPage1.this);
            builder.setMessage("Please Download This Beat To Play It.");
            builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FinalPlay.setActivated(false);
                    Intent intentdownload = new Intent(getApplicationContext(), DownloadedBeats.class);
                    startActivity(intentdownload);
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        }
    }
}