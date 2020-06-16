package com.example.musicapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.File;

public class Test extends AppCompatActivity
{
    private Spinner spFrequency;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        spFrequency = (Spinner) findViewById(R.id.frequency);

        String[] arrayOfStrings = new String[8];
        arrayOfStrings[0] = "Ghost";
        arrayOfStrings[1] = "Slow Motion";
        arrayOfStrings[2] = "Robot";
        arrayOfStrings[3] = "Normal";
        arrayOfStrings[4] = "Chipmunk";
        arrayOfStrings[5] = "Funny";
        arrayOfStrings[6] = "Bee";
        arrayOfStrings[7] = "Elephant";

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayOfStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrequency.setAdapter(adapter);
    }
}