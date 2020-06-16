package com.example.musicapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity
{
    MediaPlayer player;
    Uri path;
    private ImageButton button;
    CheckBox checkBox1;
    int check;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        button = findViewById(R.id.imageButton6);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openFinalActivity();
            }
        });

        checkBox1 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checkBox1.isChecked())
                {
                    check = 3;
                    openFinalActivity();
                }
            }
        });
    }
    public void openFinalActivity()
    {
        Intent intent = new Intent(this, FinalActivity.class);
        if(check == 3)
        {
            intent.putExtra("check",3);
        }
        startActivity(intent);
    }
    public void play(View view)
    {
        if(player != null)
        {
            stopPlayer();
        }
        if(player == null)
        {
            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chad_crouch_shipping_lanes);
            player = MediaPlayer.create(getApplicationContext(),path);

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
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
    public void stop(View view)
    {
        stopPlayer();
    }
    private void stopPlayer()
    {
        if(player != null)
        {
            player.release();
            player = null;
            Toast.makeText(getApplicationContext(),"MediaPlayer Released",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        stopPlayer();
    }
}
