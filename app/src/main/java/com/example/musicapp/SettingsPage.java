package com.example.musicapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsPage extends AppCompatActivity {
    Button B1,B2,B3;
    Switch S1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        B1 = findViewById(R.id.about_button);
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SettingsPage.this);
                builder.setTitle("About Song Creator");
                builder.setMessage("With Song Creator you can use many different features to create your own personal song. " +
                        "We offer 20 personalized beats to create your song with. Song Creator comes with recording features and over 8 different voice changing effects. " +
                        "You can also create your own personalized AI voice that will sing your created song for you. " +
                        "Song Creator also makes writing your own lyrics a thing of the past with Song Creators lyric generator. " +
                        "We also offer saved content libraries so that you can save all your created lyrics and voice recordings. " +
                        "With all the amazing features offered by Song Creator, the user is allowed to create a personalized song within minutes.\n\n" +
                        "If you have any questions or concerns, please contact us at songcreatorservices@gmail.com.");
                builder.setNegativeButton("Cancel",null);
                builder.show();
            }
        });
        B2 = findViewById(R.id.pp_button);
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
                openURL.setData(Uri.parse("https://sites.google.com/view/songcreatorprivacypolicy"));
                startActivity(openURL);
            }
        });
        B3 = findViewById(R.id.tutorial_button);
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://youtu.be/5HD69ZlYPBI"));
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
            }
        });
        S1 = findViewById(R.id.switch1);
        boolean value = true;
        final SharedPreferences sharedPreferences = getSharedPreferences("isChecked",0);
        value = sharedPreferences.getBoolean("isChecked",value);
        S1.setChecked(value);
        S1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    sharedPreferences.edit().putBoolean("isChecked", true).apply();
                }else {
                    sharedPreferences.edit().putBoolean("isChecked", false).apply();
                }
            }
        });
    }
}