package com.example.musicapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.os.Environment;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import static android.Manifest.*;

public class FinalActivity extends AppCompatActivity
{
    private TextToSpeech textToSpeech;
    private SeekBar seekBarpitch;
    private SeekBar seekBarspeed;
    private TextView textView;
    private Intent intent;
    public Uri path;
    public MediaPlayer player3;
    public Set<String> a;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ActivityCompat.requestPermissions(this,new String[] {permission.RECORD_AUDIO, permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        },"com.google.android.tts");
        a = new HashSet<>();
        a.add("male");
        seekBarpitch = findViewById(R.id.seekBarPitch);
        seekBarspeed = findViewById(R.id.seekBarSpeed);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void LoadAI(View view) throws IOException
    {
        stopPlayer();
        String song = "Press Play Song when you're ready!";
        Voice v = new Voice("en-us-x-sfg#male_2-local",new Locale("en","US"),600,500,true,a);
        float pitch = (float) seekBarpitch.getProgress() / 50;
        if(pitch < 0.1)
        {
            pitch = 0.1f;
        }
        float speed = (float) seekBarspeed.getProgress() / 50;
        if(speed < 0.1)
        {
            speed = 0.1f;
        }
        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        textToSpeech.setVoice(v);
        textToSpeech.speak(song, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void PlayAI(View view) throws IOException
    {
        String song = "Hello\n" +
                "I could stick around and get along with you\n" +
                "Hello\n" +
                "It doesn't really mean that I'm into you\n" +
                "Helloooooooooooooooooooooo\n" +
                "You're alright but I'm here darling to enjoy the party\n" +
                "Don't get too excited 'cus that's all you get from me\n" +
                "Hey\n" +
                "You're alright but I'm here darling\n" +
                "Yeah I think you're cute but really you should know\n" +
                "I just came to say hello\n" +
                "Hello\n" +
                "Hello\n" +
                "Hello\n" +
                "I'm not the kinda girl who'd get messed up with you\n" +
                "Hello\n" +
                "Imma let you try to convince me to\n" +
                "Hello\n" +
                "It's alright I'm getting dizzy just enjoy the party\n" +
                "It's ok with me if you don't have that much to say\n" +
                "Hey\n" +
                "Kinda like this thing but there's something you should know\n" +
                "I just came to say hello\n" +
                "Hello\n" +
                "Hey\n" +
                "Hello\n" +
                "Hello\n" +
                "You're alright but I'm here darling to enjoy the party\n" +
                "Don't get too excited 'cus that's all you get from me\n" +
                "Hey\n" +
                "(You're alright but I'm here darling) don't get too excited 'cus that's all you get from me\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I just came to say hello\n" +
                "Hello\n" +
                "Hello\n" +
                "Hello\n" +
                "I'm not the kinda girl who'd get messed up with you\n" +
                "Hello\n" +
                "Imma let you try to convince me to\n" +
                "Hello\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I'm here darling to enjoy the\n" +
                "I just came to say hello\n" +
                "Hello\n" +
                "Hey\n" +
                "Hey\n" +
                "Hello";
        float pitch = (float) seekBarpitch.getProgress() / 50;
        if(pitch < 0.1)
        {
            pitch = 0.1f;
        }
        float speed = (float) seekBarspeed.getProgress() / 50;
        if(speed < 0.1)
        {
            speed = 0.1f;
        }
        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        textToSpeech.setLanguage(Locale.ENGLISH);
        textToSpeech.speak(song, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    @Override
    protected void onDestroy()
    {
        if(textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
    //    private void createMethod()
//    {
//        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Personal.txt");
//
//        try
//        {
//            if(!file.exists())
//            {
//                file.createNewFile();
//            }
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.append("MY KILLER IS MICHAEL");
//            fileWriter.flush();
//            fileWriter.close();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return;
//        }
//        textToSpeech.speak("The text file has been created. Thank you for using I.",TextToSpeech.QUEUE_FLUSH,null,null);
//    }
public void play(View view)
{
    int i = 0;
    Intent myintent = getIntent();
    int intvalue = myintent.getIntExtra("check",0);
    if(intvalue == 1)
    {
        path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_05_picture_perfect);
    }
    if(intvalue == 2)
    {
        path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_03_streets);
    }
    if(intvalue == 3)
    {
        path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chad_crouch_shipping_lanes);
    }
    if(player3 != null)
    {
        stopPlayer();
    }
    if(player3 == null)
    {
        player3 = MediaPlayer.create(getApplicationContext(),path);
        player3.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                stopPlayer();
            }
        });
    }
    player3.start();
}
public void stop(View view)
{
    stopPlayer();
}
    private void stopPlayer()
    {
        if(player3 != null)
        {
            player3.release();
            player3 = null;
            Toast.makeText(getApplicationContext(),"MediaPlayer Released",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        stopPlayer();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void FinalPlay2(View view) throws IOException
    {
        PlayAI(view);
        play(view);
    }
}