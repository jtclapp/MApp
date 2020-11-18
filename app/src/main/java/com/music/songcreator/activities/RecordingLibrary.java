package com.music.songcreator.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.music.songcreator.R;
import com.music.songcreator.SQLite.CreatedSongModel;
import com.music.songcreator.SQLite.DataBaseHelper;
import com.music.songcreator.SQLite.RecordingModel;

import java.io.File;
import java.util.List;

public class RecordingLibrary extends AppCompatActivity {
    ListView lv_recordinglist;
    ArrayAdapter recordingArrayAdapter;
    DataBaseHelper dataBaseHelper;
    EditText recordingname;
    Button update,update2;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_library);

        mAdView = findViewById(R.id.adView9);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lv_recordinglist = findViewById(R.id.recording_list);
        update = findViewById(R.id.UpdateButton);
        update2 = findViewById(R.id.UpdateButton);
        update.setVisibility(View.INVISIBLE);
        recordingname = findViewById(R.id.editRecording);
        recordingname.setVisibility(View.INVISIBLE);
        dataBaseHelper = new DataBaseHelper(RecordingLibrary.this);
        ShowRecording();

        lv_recordinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(RecordingLibrary.this);
                builder.setMessage("Do you want to edit or delete this recording?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        final AlertDialog.Builder builder1 = new AlertDialog.Builder(RecordingLibrary.this);
                        builder1.setMessage("Are you sure you want to delete?");
                        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RecordingModel clickedRecording = (RecordingModel) adapterView.getItemAtPosition(i);
                                dataBaseHelper.DeleteOneRecording(clickedRecording);
                                ShowRecording();
                            }
                        });
                        builder1.setNegativeButton("No",null);
                        builder1.show();
                    }
                });
                builder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recordingname.setVisibility(View.VISIBLE);
                        update.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecordingModel clickedRecording = (RecordingModel) adapterView.getItemAtPosition(i);
                        String newname = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + recordingname.getText().toString();
                        List<CreatedSongModel> createdSongModelList = dataBaseHelper.getEveryone3();
                        for(int i = 0; i < createdSongModelList.size(); i++)
                        {
                           if(clickedRecording.getName().equals(createdSongModelList.get(i).getRecordingname()))
                           {
                               boolean update2 = dataBaseHelper.updateOneSong(createdSongModelList.get(i),newname);
                           }
                        }
                        boolean update = dataBaseHelper.updateOneRecording(clickedRecording, newname);
                        if(update)
                        {
                            Toast.makeText(RecordingLibrary.this, "Recording Directory Successfully Updated.", Toast.LENGTH_SHORT).show();
                        }
                        if(!update)
                        {
                            Toast.makeText(RecordingLibrary.this, "Error Occurred While Updating.", Toast.LENGTH_SHORT).show();
                        }
                        ShowRecording();
                        recordingname.setVisibility(View.INVISIBLE);
                        update2.setVisibility(View.INVISIBLE);
                        recordingname.getText().clear();
                    }

                });
            }
        });
    }
    private void ShowRecording() {
        recordingArrayAdapter = new ArrayAdapter<>(RecordingLibrary.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone2());
        lv_recordinglist.setAdapter(recordingArrayAdapter);
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
}