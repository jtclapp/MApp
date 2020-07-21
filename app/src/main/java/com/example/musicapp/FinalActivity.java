package com.example.musicapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import static android.Manifest.permission;

public class FinalActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    public MediaPlayer player3;
    ToggleButton finalplay;
    Button button;
    TextView v1, songtitle;
    String song;
    File path;
    public Set<String> a;
    String recordedvoice;
    AudioTrack audioTrack;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        ScrollView scrollView = findViewById(R.id.final_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        mAdView = findViewById(R.id.adView12);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ActivityCompat.requestPermissions(this, new String[]{permission.RECORD_AUDIO, permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Log Log = null;
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    // int result = tts.setLanguage(Locale.getDefault());
                    // If your device doesn't support language you set above
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Cook simple toast message with message
                        Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
                        android.util.Log.e("textToSpeech", "Language is not supported");
                    }
                    // TTS is not initialized properly
                } else {
                    Toast.makeText(getApplicationContext(), "TextToSpeech Initilization Failed", Toast.LENGTH_SHORT).show();
                    android.util.Log.e("textToSpeech", "Initilization Failed");
                }
            }
        }, "com.google.android.tts");

        a = new HashSet<>();
        button = findViewById(R.id.buttonAI);
        finalplay = findViewById(R.id.finalplay);
        v1 = findViewById(R.id.textView4);
        songtitle = findViewById(R.id.songtitle);

        Intent myintent = getIntent();
        recordedvoice = myintent.getStringExtra("path");
        if (recordedvoice != null) {
            button.setVisibility(View.INVISIBLE);
        }
        if (recordedvoice == null) {
            finalplay.setEnabled(false);
        }
        finalplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (finalplay.isChecked()) {
                    finalplay.setActivated(true);
                    if(recordedvoice != null)
                    {
                        Toast.makeText(FinalActivity.this, "Audio is loading! Please wait...", Toast.LENGTH_SHORT).show();
                    }
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
        songbuilder();
    }
    public void LoadAI(View view) throws IOException {
        stopPlayer();
        Intent myintent2 = getIntent();
        Intent myintent3 = getIntent();
        Intent myintent4 = getIntent();
        Intent myintent5 = getIntent();
        Intent myintent6 = getIntent();
        float speed2 = myintent2.getFloatExtra("speed", 0);
        float pitch2 = myintent3.getFloatExtra("pitch", 0);
        String vname = myintent4.getStringExtra("voicename");
        String voicelang = myintent5.getStringExtra("voicelang");
        String voicecountry = myintent6.getStringExtra("voicecountry");
        Voice voice = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            voice = new Voice(vname, new Locale(voicelang, voicecountry), 600, 500, true, a);
        } else {
            textToSpeech.setLanguage(new Locale(voicelang, voicecountry));
        }
        String text = "Your AI Voice is now loaded!";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.setVoice(voice);
        }
        textToSpeech.setSpeechRate(speed2);
        textToSpeech.setPitch(pitch2);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
        button.setVisibility(View.INVISIBLE);
        finalplay.setEnabled(true);
    }

    public void PlayAI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(song, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(song, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    private void songbuilder() {
        Intent edit_intent = getIntent();
        Intent title_intent = getIntent();
        String song_title = title_intent.getStringExtra("songtitle");
        String edittext = edit_intent.getStringExtra("editTextData");
        v1.setText(edittext);
        songtitle.setText(song_title);
        v1.setMovementMethod(new ScrollingMovementMethod());
        song = edittext;
    }
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
    public void play() {
        Intent myintent2 = getIntent();
        Intent myintent = getIntent();
        int intvalue = myintent2.getIntExtra("svalue", 0);
        float final_volume = myintent.getFloatExtra("volume",0);
        if (intvalue == 1) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#1.mp3");
        }
        if (intvalue == 2) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#2.mp3");
        }
        if (intvalue == 3) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#3.mp3");
        }
        if(intvalue == 24)
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#4.mp3");
        }
        if(intvalue == 25)
        {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "HipHop_Beat#5.mp3");
        }
        if (intvalue == 4) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#1.mp3");
        }
        if (intvalue == 5) {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#1.mp3");
        }
        if (intvalue == 6) {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#1.mp3");
        }
        if (intvalue == 7) {
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Rock_Beat#1.mp3");
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
        if (intvalue == 13) { // Complete
            path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + "Country_Beat#1.mp3");
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
                player3.setVolume(final_volume,final_volume);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            player3.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {

                        finalplay.setActivated(false);
                }
            });
}
    public void RecordPlay() throws IOException {
        File file = new File(recordedvoice);
        Intent HZintent = getIntent();
        Intent Bufferintent = getIntent();
        int SampleHZ = HZintent.getIntExtra("sampleRateInHz", 0);
        int bs = Bufferintent.getIntExtra("buffersizeinbytes", 0);
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

        audioTrack = new AudioTrack(3, SampleHZ, 2, 2, bs, 1);
        play();
        audioTrack.play();
        audioTrack.write(audioData, 0, bs);
    }
    private void stopPlayer() {
        if (player3 != null && recordedvoice != null) {
            audioTrack.release();
            player3.release();
            player3 = null;
        }
        if (player3 != null) {
            textToSpeech.stop();
            player3.release();
            player3 = null;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
    public void FinalPlay(View view) throws IOException {
        if (recordedvoice != null) {
            RecordPlay();
        } else {
            PlayAI();
            play();
        }
    }
}
