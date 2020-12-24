package com.music.songcreator.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.music.songcreator.SQLite.AISongModel;
import com.music.songcreator.java_operations.BeatFileSelector;
import com.music.songcreator.SQLite.CreatedSongModel;
import com.music.songcreator.SQLite.DataBaseHelper;
import com.music.songcreator.java_operations.LoadingHelper;
import com.music.songcreator.R;

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
    int intvalue;
    int hz;
    float beatvolume;
    float speed2;
    float pitch2;
    String vname;
    String voicelang;
    String voicecountry;
    BeatFileSelector beatFileSelector;
    LoadingHelper loadingHelper = new LoadingHelper(FinalActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        final SharedPreferences sharedPreferences = getSharedPreferences("isChecked", 0);
        boolean value = sharedPreferences.getBoolean("isChecked",true);
        if(value) {
            ScrollView scrollView = findViewById(R.id.final_layout);
            AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
            animationDrawable.setEnterFadeDuration(2000);
            animationDrawable.setExitFadeDuration(4000);
            animationDrawable.start();
        }
        ActivityCompat.requestPermissions(this, new String[]{permission.RECORD_AUDIO, permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
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
        button = findViewById(R.id.buttonAI);
        finalplay = findViewById(R.id.finalplay);
        v1 = findViewById(R.id.textView4);
        songtitle = findViewById(R.id.songtitle);
        song = "";
        beatFileSelector = new BeatFileSelector();

        Intent myintent4 = getIntent();
        speed2 = myintent4.getFloatExtra("speed", 0);
        Intent myintent5 = getIntent();
        pitch2 = myintent5.getFloatExtra("pitch", 0);
        Intent myintent6 = getIntent();
        vname = myintent6.getStringExtra("voicename");
        Intent myintent7 = getIntent();
        voicelang = myintent7.getStringExtra("voicelang");
        Intent myintent8 = getIntent();
        voicecountry = myintent8.getStringExtra("voicecountry");
        Intent HZintent = getIntent();
        hz = HZintent.getIntExtra("sampleRateInHz", 0);
        Intent myintent3 = getIntent();
        beatvolume = myintent3.getFloatExtra("volume",0);
        Intent myintent2 = getIntent();
        intvalue = myintent2.getIntExtra("svalue", 0);
        Intent myintent = getIntent();
        recordedvoice = myintent.getStringExtra("path");


        if (recordedvoice != null) {
            button.setVisibility(View.INVISIBLE);
        }
        if (recordedvoice == null) {
            Toast.makeText(getApplicationContext(), "Select Load AI First", Toast.LENGTH_LONG).show();
            finalplay.setEnabled(false);
        }
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
        v1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (v.getId() == R.id.textView4) {
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
        songbuilder();
    }
    public void LoadAI(View view) {
        stopPlayer();
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
        Intent AI_intent = getIntent();
        String song_title = title_intent.getStringExtra("songtitle");
        String edittext = edit_intent.getStringExtra("editTextData");

        song = AI_intent.getStringExtra("song");
        v1.setText(edittext);
        songtitle.setText(song_title);
        v1.setMovementMethod(new ScrollingMovementMethod());
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

        path = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatFileSelector.FileSelector(intvalue));

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
                player3.setVolume(beatvolume,beatvolume);
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
        Intent Bufferintent = getIntent();

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
        if(bs > 0) {
            audioTrack = new AudioTrack(3,hz, 2, 2, bs, 1);
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
        if(audioTrack != null) {
            audioTrack.write(audioData, 0, bs);
        }
    }
    private void stopPlayer() {
        if (player3 != null && recordedvoice != null) {
            if(audioTrack != null)
            {
                audioTrack.release();
            }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.createdsongdownloadmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case  R.id.homeitem3:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
                break;

            case R.id.downloadcreatedsong:
                DataBaseHelper dataBaseHelper;
                boolean success;
                if(recordedvoice != null) {
                    CreatedSongModel createdSongModel;
                    try {
                        createdSongModel = new CreatedSongModel(-1, songtitle.getText().toString(),v1.getText().toString(),recordedvoice,intvalue,hz,beatvolume);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error Saving Song!", Toast.LENGTH_SHORT).show();
                        createdSongModel = new CreatedSongModel(-1, "Error", "Error", "Error", 0,0,0);
                    }
                    dataBaseHelper = new DataBaseHelper(FinalActivity.this);
                    success = dataBaseHelper.addOneSong(createdSongModel);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "Song was successfully saved!", Toast.LENGTH_SHORT).show();
                    }
                    if (!success) {
                        Toast.makeText(getApplicationContext(), "Error occurred when trying to save song.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    AISongModel aiSongModel;
                    try{
                        aiSongModel = new AISongModel(-1,songtitle.getText().toString(),v1.getText().toString(),vname,voicelang,voicecountry,speed2,pitch2,intvalue,beatvolume);
                    } catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Error Saving AI Song!", Toast.LENGTH_SHORT).show();
                        aiSongModel = new AISongModel(-1, "Error", "Error", "Error", "Error","Error",0,0,0,0);
                    }
                    dataBaseHelper = new DataBaseHelper(FinalActivity.this);
                    success = dataBaseHelper.addOneAISong(aiSongModel);
                    if (success) {
                        Toast.makeText(getApplicationContext(), "AI Song was successfully saved!", Toast.LENGTH_SHORT).show();
                    }
                    if (!success) {
                        Toast.makeText(getApplicationContext(), "Error occurred when trying to save AI song.", Toast.LENGTH_SHORT).show();
                    }
                }
               break;
        }
        return true;
    }
}
