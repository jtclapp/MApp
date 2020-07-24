package com.example.musicapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.File;

public class RecordingLibrary extends AppCompatActivity {
    ListView lv_recordinglist;
    ArrayAdapter recordingArrayAdapter;
    DataBaseHelper dataBaseHelper;
    EditText recordingname;
    Button update, update2;
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
        update.setVisibility(View.INVISIBLE);
        update2 = findViewById(R.id.UpdateButton);
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
                        RecordingModel clickedRecording = (RecordingModel) adapterView.getItemAtPosition(i);
                        dataBaseHelper.DeleteOneRecording(clickedRecording);
                        ShowRecording();
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
                        boolean update = dataBaseHelper.updateOneRecording(clickedRecording, newname);
                        if(update == true)
                        {
                            Toast.makeText(RecordingLibrary.this, "Recording Directory Successfully Updated.", Toast.LENGTH_SHORT).show();
                        }
                        if(update == false)
                        {
                            Toast.makeText(RecordingLibrary.this, "Error Occurred While Updating.", Toast.LENGTH_SHORT).show();
                        }
                        ShowRecording();
                        recordingname.setVisibility(View.INVISIBLE);
                        update2.setVisibility(View.INVISIBLE);
                    }

                });
            }
        });
    }
    private void ShowRecording() {
        recordingArrayAdapter = new ArrayAdapter<RecordingModel>(RecordingLibrary.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone2());
        lv_recordinglist.setAdapter(recordingArrayAdapter);
    }
}