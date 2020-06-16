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

public class MainActivity2 extends AppCompatActivity
{
    MediaPlayer player;
    Uri path;
    CheckBox checkBox;
    CheckBox checkBox2;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checkBox.isChecked())
                {
                    check = 1;
                    openFinalActivity();
                }
            }
        });
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(checkBox2.isChecked())
                {
                    check = 2;
                    openFinalActivity();
                }
            }
        });
    }
        public void openFinalActivity()
        {
            Intent intent = new Intent(this, FinalActivity.class);

            if(check == 1)
            {
             intent.putExtra("check",1);
            }
            if(check == 2)
            {
                intent.putExtra("check",2);
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
                path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_05_picture_perfect);
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
        public void stop(View view)
        {
            stopPlayer();
        }
//        public void pause(View v)
//        {
//            if(player != null)
//            {
//                player.pause();
//            }
//        }
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
    public void play2(View view)
    {
        if(player != null)
        {
            stopPlayer();
        }
        if(player == null)
        {
            path = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.yung_kartz_03_streets);
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
}