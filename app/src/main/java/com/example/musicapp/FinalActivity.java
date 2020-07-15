package com.example.musicapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    public String path;
    public MediaPlayer player3;
    ToggleButton finalplay;
    Button button;
    TextView v1, songtitle;
    String song;
    public Set<String> a;
    String recordedvoice;
    AudioTrack audioTrack;
    boolean connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

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
                connected = isConnected();
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
                    if(connected == false) {
                        finalplay.setChecked(false);
                        final AlertDialog.Builder builder = new AlertDialog.Builder(FinalActivity.this);
                        builder.setMessage("Please Connect to the Internet to use this feature!");
                        builder.show();
                    }
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
        Intent myintent2 = getIntent();
        int intvalue = myintent2.getIntExtra("svalue", 0);
        if (intvalue == 1) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rap%20Beat%201.mp3?alt=media&token=7a26b8cf-e64e-406b-84d3-ade2984be72e";
        }
        if (intvalue == 2) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rap%20Beat%202.mp3?alt=media&token=0fded1e5-6c7f-4a47-bedd-e1df48e7f516";
        }
        if (intvalue == 3) {
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rap%20Beat%202.mp3?alt=media&token=0fded1e5-6c7f-4a47-bedd-e1df48e7f516";
        }
        if (intvalue == 4) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rock%20Beat%201.mp3?alt=media&token=473246ca-d10e-4f88-b15c-4738070f6966";
        }
        if (intvalue == 5) {
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rock%20Beat%201.mp3?alt=media&token=473246ca-d10e-4f88-b15c-4738070f6966";
        }
        if (intvalue == 6) {
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rock%20Beat%201.mp3?alt=media&token=473246ca-d10e-4f88-b15c-4738070f6966";
        }
        if (intvalue == 7) {
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Rock%20Beat%201.mp3?alt=media&token=473246ca-d10e-4f88-b15c-4738070f6966";
        }
        if (intvalue == 8) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/R%26B%20Beat_1.mp3?alt=media&token=f557d7fd-588f-439e-90cc-3cbac575a615";
        }
        if (intvalue == 9) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/R%26B%20Beat%202.mp3?alt=media&token=a89fb70f-1a91-47a4-b85c-bf0df1584edf";
        }
        if(intvalue == 10) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/R%26B%20Beat%203.mp3?alt=media&token=cc711559-5770-4ee5-8697-7071072a4789";
        }
        if (intvalue == 13) { // Complete
            path = "https://firebasestorage.googleapis.com/v0/b/beats-651c7.appspot.com/o/Country%20beat%201.mp3?alt=media&token=394ccfa6-ca62-4fee-b646-9e821e1b56ba";
        }
            if (player3 != null) {
                stopPlayer();
            }
            if (player3 != null) {
                stopPlayer();
            }
            player3 = new MediaPlayer();
            try {
                player3.setDataSource(path);
                player3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player3.start();
                    }
                });
                player3.prepare();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
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
