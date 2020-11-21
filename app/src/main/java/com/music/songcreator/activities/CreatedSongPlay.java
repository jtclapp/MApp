package com.music.songcreator.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.music.songcreator.SQLite.CreatedSongModel;
import com.music.songcreator.java_operations.BeatFileSelector;
import com.music.songcreator.java_operations.LoadingHelper;
import com.music.songcreator.R;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreatedSongPlay extends AppCompatActivity {

    MediaPlayer player3;
    File path;
    TextView v1, songtitle;
    ToggleButton finalplay;

    AudioTrack audioTrack;
    LoadingHelper loadingHelper = new LoadingHelper(CreatedSongPlay.this);
    BeatFileSelector beatFileSelector = new BeatFileSelector();
    CreatedSongModel createdSongModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_song_play);

        final SharedPreferences sharedPreferences = getSharedPreferences("isChecked", 0);
        boolean value = sharedPreferences.getBoolean("isChecked",true);
        if(value) {
            ScrollView scrollView = findViewById(R.id.CreatedSongLayout);
            AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
            animationDrawable.setEnterFadeDuration(2000);
            animationDrawable.setExitFadeDuration(4000);
            animationDrawable.start();
        }
        v1 = findViewById(R.id.CStextview);
        v1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (v.getId() == R.id.CStextview) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
        v1.setMovementMethod(new ScrollingMovementMethod());
        songtitle = findViewById(R.id.CSsongtitle);
        finalplay = findViewById(R.id.CSfinalplay);

        Intent myintent = getIntent();
        createdSongModel = (CreatedSongModel) myintent.getSerializableExtra("createdSongModel");
        songtitle.setText(createdSongModel.getSongname());
        v1.setText(createdSongModel.getLyricsname());

        finalplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (finalplay.isChecked()) {
                    finalplay.setActivated(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                FinalPlay(v);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                if (finalplay.isChecked() == false) {
                    finalplay.setActivated(false);
                    stopPlayer();
                }
            }
        });
    }
    public void play() {
        path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatFileSelector.FileSelector(createdSongModel.getBeatnum()));

        if (player3 != null) {
            stopPlayer();
        }
        player3 = new MediaPlayer();
        try {
            player3.setDataSource(String.valueOf(path));
            player3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player3.start();
                }
            });
            player3.prepare();
            if(createdSongModel.getVolume() <= 0) {
                player3.setVolume(1f, 1f);
            }
            else {
                player3.setVolume(createdSongModel.getVolume(), createdSongModel.getVolume());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        player3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finalplay.setChecked(false);
                        if (finalplay.isChecked() == false) {
                            finalplay.setActivated(false);
                            stopPlayer();
                        }
                    }
                });
            }
        });
    }
    public void RecordPlay() throws IOException {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingHelper.startLoadingDialog();
            }
        });
        File file = new File(createdSongModel.getRecordingname());
        int shortSizeInBytes = Short.SIZE / Byte.SIZE;
        int bs = (int) (file.length() / shortSizeInBytes);
        short[] audioData = new short[bs];
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);

        int j = 0;
        while (dataInputStream.available() > 0) {
            audioData[j] = dataInputStream.readShort();
            j++;
        }
        dataInputStream.close();
        if(createdSongModel.getHz() <= 0)
        {
           audioTrack = new AudioTrack(3,11025,2,2,bs,1);
        }
        else {
            audioTrack = new AudioTrack(3, createdSongModel.getHz(), 2, 2, bs, 1);
        }
        play();
        try{
            audioTrack.play();
        }catch (Exception e)
        {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Error Occurred While Trying to Play Recorded Audio",Toast.LENGTH_SHORT).show();
                    finalplay.setChecked(false);
                    if (finalplay.isChecked() == false) {
                        finalplay.setActivated(false);
                        stopPlayer();
                    }
                }
            });
            loadingHelper.dismissDialog();
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingHelper.dismissDialog();
            }
        });
        audioTrack.write(audioData, 0, bs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finalactmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case  R.id.homeitem3:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
        }
        return true;
    }
    public void FinalPlay(View view) throws IOException {
            RecordPlay();
    }
    private void stopPlayer() {
        if (player3 != null) {
            if(audioTrack != null)
            {
                audioTrack.release();
            }
            player3.release();
            player3 = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}