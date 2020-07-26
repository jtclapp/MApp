package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsPage extends AppCompatActivity {
    Button B1,B2;
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
                builder.setMessage(" ");
                builder.setNegativeButton("Cancel",null);
                builder.show();
            }
        });
        B2 = findViewById(R.id.pp_button);
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(SettingsPage.this);
                builder2.setTitle("Privacy Policy Of Song Creator");
                builder2.setMessage(" ");
                builder2.setNegativeButton("Cancel",null);
                builder2.show();
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
                    sharedPreferences.edit().putBoolean("isChecked", false).apply();;
                }
            }
        });
    }
}