package com.music.songcreator.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.music.songcreator.R;
import com.music.songcreator.SQLite.AISongModel;
import com.music.songcreator.java_operations.BeatFileSelector;
import com.music.songcreator.java_operations.LoadingHelper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class CreatedAiSongPlay extends AppCompatActivity {

    MediaPlayer player3;
    File path;
    Button LoadAI;
    TextView v1, songtitle;
    ToggleButton finalplay;
    TextToSpeech textToSpeech;
    BeatFileSelector beatFileSelector;
    AISongModel aiSongModel;
    Set<String> a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_ai_song_play);

        final SharedPreferences sharedPreferences = getSharedPreferences("isChecked", 0);
        boolean value = sharedPreferences.getBoolean("isChecked",true);
        if(value) {
            ScrollView scrollView = findViewById(R.id.AI_Song_Play);
            AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
            animationDrawable.setEnterFadeDuration(2000);
            animationDrawable.setExitFadeDuration(4000);
            animationDrawable.start();
        }
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
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
        v1 = findViewById(R.id.AILyrics);
        v1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
        beatFileSelector = new BeatFileSelector();
        songtitle = findViewById(R.id.AISongTitle);
        finalplay = findViewById(R.id.AIplay);
        LoadAI = findViewById(R.id.LoadAI);
        Intent myintent = getIntent();
        aiSongModel = (AISongModel) myintent.getSerializableExtra("aiSongModel");
        songtitle.setText(aiSongModel.getSongTitle());
        v1.setText(aiSongModel.getSong());

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
        Toast.makeText(getApplicationContext(), "Select Load AI First", Toast.LENGTH_LONG).show();
        finalplay.setEnabled(false);
    }
    public void LoadAI(View view) {
        stopPlayer();
        Voice voice = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            voice = new Voice(aiSongModel.getVoicename(), new Locale(aiSongModel.getVoicelang(),aiSongModel.getVoicecountry()), 600, 500, true, a);
        } else {
            textToSpeech.setLanguage(new Locale(aiSongModel.getVoicelang(),aiSongModel.getVoicecountry()));
        }
        String text = "Your AI Voice is now loaded!";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.setVoice(voice);
        }
        textToSpeech.setSpeechRate(aiSongModel.getSpeed());
        textToSpeech.setPitch(aiSongModel.getPitch());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
        LoadAI.setVisibility(View.INVISIBLE);
        finalplay.setEnabled(true);
    }
    public void PlayAI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(aiSongModel.getSong(), TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(aiSongModel.getSong(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    public void play() {
        path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatFileSelector.FileSelector(aiSongModel.getBeatnum()));

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
            player3.setVolume(aiSongModel.getVolume(),aiSongModel.getVolume());
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
        PlayAI();
        play();
    }
    private void stopPlayer() {
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
}