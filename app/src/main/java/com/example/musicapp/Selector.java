package com.example.musicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Selector extends AppCompatActivity {
    ListView lv_customerList;
    ArrayAdapter lyricArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        lv_customerList = findViewById(R.id.lyrics_list2);
        dataBaseHelper = new DataBaseHelper(Selector.this);
        ShowLyrics();

        lv_customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Selector.this);
                builder.setMessage("Are you sure you want to select this song?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        LyricsModel clickedLyric = (LyricsModel) adapterView.getItemAtPosition(i);
                        String name = clickedLyric.getName();
                        String lyrics = clickedLyric.getLyrics();

                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("lyrics", lyrics);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    private void ShowLyrics() {
        lyricArrayAdapter = new ArrayAdapter<LyricsModel>(Selector.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customerList.setAdapter(lyricArrayAdapter);
    }
}