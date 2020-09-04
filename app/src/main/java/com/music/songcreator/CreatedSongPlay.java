package com.music.songcreator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreatedSongPlay extends AppCompatActivity {
    MediaPlayer player3;
    int intvalue;
    File path;
    TextView v1, songtitle;
    ToggleButton finalplay;
    String recordedvoice;
    AudioTrack audioTrack;
    SeekBar volumeadj;
    float setVolume;
    Spinner spFrequency;
    ArrayAdapter<String> adapter;
    LoadingHelper loadingHelper = new LoadingHelper(CreatedSongPlay.this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_song_play);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final SharedPreferences sharedPreferences = getSharedPreferences("isChecked", 0);
        boolean value = sharedPreferences.getBoolean("isChecked",true);
        if(value) {
            ScrollView scrollView = findViewById(R.id.CreatedSongLayout);
            AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
            animationDrawable.setEnterFadeDuration(2000);
            animationDrawable.setExitFadeDuration(4000);
            animationDrawable.start();
        }
        String[] arrayOfStrings = new String[9];
        arrayOfStrings[0] = "Slow Motion";
        arrayOfStrings[1] = "Bass";
        arrayOfStrings[2] = "Alto";
        arrayOfStrings[3] = "Normal";
        arrayOfStrings[4] = "Tenor";
        arrayOfStrings[5] = "Soprano";
        arrayOfStrings[6] = "Coloratura Soprano";
        arrayOfStrings[7] = "Helium";
        arrayOfStrings[8] = "2x Speed";

        spFrequency = findViewById(R.id.CSVoice_spinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOfStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrequency.setAdapter(adapter);
        spFrequency.setSelection(3,true);
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
        volumeadj = findViewById(R.id.CreatedSongVolume);
        finalplay = findViewById(R.id.CSfinalplay);

        Intent myintent = getIntent();
        Intent myintent2 = getIntent();
        Intent myintent3 = getIntent();
        Intent myintent4 = getIntent();
        songtitle.setText(myintent3.getStringExtra("Title"));
        v1.setText(myintent2.getStringExtra("Lyrics"));
        recordedvoice = myintent4.getStringExtra("RecordingName");
        intvalue = myintent.getIntExtra("beatnum",0);

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
        setVolume = (float) volumeadj.getProgress() / 50;
        if (setVolume < 0.1) {setVolume = 0.1f;}
        if (intvalue == 1) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#1.mp3");
        }
        if (intvalue == 2) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#2.mp3");
        }
        if (intvalue == 3) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#3.mp3");
        }
        if(intvalue == 24) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#4.mp3");
        }
        if(intvalue == 25) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#5.mp3");
        }
        if (intvalue == 4) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#1.mp3");
        }
        if (intvalue == 5) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#2.mp3");
        }
        if (intvalue == 6) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#3.mp3");
        }
        if (intvalue == 7) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#4.mp3");
        }
        if(intvalue == 29) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#5.mp3");
        }
        if (intvalue == 8) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#1.mp3");
        }
        if (intvalue == 9) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#2.mp3");
        }
        if(intvalue == 10) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#3.mp3");
        }
        if(intvalue == 11) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#4.mp3");
        }
        if(intvalue == 35) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "R&B_Beat#5.mp3");
        }
        if (intvalue == 13) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#1.mp3");
        }
        if(intvalue == 14) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#2.mp3");
        }
        if(intvalue == 15) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#3.mp3");
        }
        if(intvalue == 16) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#4.mp3");
        }
        if(intvalue == 17) // Complete
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#5.mp3");
        }
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
            player3.setVolume(setVolume,setVolume);
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
        File file = new File(recordedvoice);
        int i;
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
        i = GetHZ();
        audioTrack = new AudioTrack(3, i, 2, 2, bs, 1);
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
    public int GetHZ() {
        //arrayOfStrings[0] = "Slow Motion";
        //arrayOfStrings[1] = "Bass";
        //arrayOfStrings[2] = "Alto";
        //arrayOfStrings[3] = "Normal";
        //arrayOfStrings[4] = "Tenor";
        //arrayOfStrings[5] = "Soprano";
        //arrayOfStrings[6] = "Coloratura Soprano";
        //arrayOfStrings[7] = "Helium";
        //arrayOfStrings[8] = "2x Speed";
        int i = 0;
        String str = (String) spFrequency.getSelectedItem();
        if (str.equals("Slow Motion")) {
            i = 6400;
        }
        if (str.equals("Bass")) {
            i = 8450;
        }
        if (str.equals("Alto")) {
            i = 9800;
        }
        if (str.equals("Normal")) {
            i = 11025;
        }
        if(str.equals("Tenor"))
        {
            i = 12800;
        }
        if (str.equals("Soprano")) {
            i = 14100;
        }
        if (str.equals("Coloratura Soprano")) {
            i = 15000;
        }
        if (str.equals("Helium")) {
            i = 20000;
        }
        if (str.equals("2x Speed")) {
            i = 24200;
        }
        return i;
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