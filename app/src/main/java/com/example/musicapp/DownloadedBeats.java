package com.example.musicapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DownloadedBeats extends AppCompatActivity {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    CheckBox CB1,CB2,CB3,CB4,CB5,CB6,CB7,CB8;
    ImageButton B1;
    String child;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_beats);

        B1 = findViewById(R.id.DownloadButton);
        CB1 = findViewById(R.id.BeatCheckBox1);
        CB2 = findViewById(R.id.BeatCheckBox2);
        CB3 = findViewById(R.id.BeatCheckBox3);
        CB4 = findViewById(R.id.BeatCheckBox4);
        CB5 = findViewById(R.id.BeatCheckBox5);
        CB6 = findViewById(R.id.BeatCheckBox6);
        CB7 = findViewById(R.id.BeatCheckBox7);
        CB8 = findViewById(R.id.BeatCheckBox8);

        CB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rap Beat 1.mp3";
                path = "HipHop_Beat#1.mp3";
            }
        });
        CB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rap Beat 2.mp3";
                path = "HipHop_Beat#2.mp3";
            }
        });
        CB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rap_Beat_3.mp3";
                path = "HipHop_Beat#3.mp3";
            }
        });
        CB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rock Beat 1.mp3";
                path = "Rock_Beat#1.mp3";
            }
        });
        CB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "R&B Beat_1.mp3";
                path = "R&B_Beat#1.mp3";
            }
        });
        CB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "R&B Beat 2.mp3";
                path = "R&B_Beat#2.mp3";
            }
        });
        CB7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "R&B Beat 3.mp3";
                path = "R&B_Beat#3.mp3";
            }
        });
        CB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Country beat 1.mp3";
                path = "Country_Beat#1.mp3";
            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Download();
            }
        });
    }
    public void Download()
    {
            final LoadingHelper loadingHelper = new LoadingHelper(DownloadedBeats.this);
            loadingHelper.startLoadingDialog();
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            ref = storageReference.child(child);

            File dir = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + path);
        if (dir.exists() == true)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
            builder.setMessage("This beat appears to be already downloaded.");
            builder.setNegativeButton("Cancel", null);
            builder.show();
        }
            if(!dir.exists())
            {
                try {
                    dir.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ref.getFile(dir).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        loadingHelper.dismissDialog();
                        Toast.makeText(getApplicationContext(),"This Beat Has Successfully Downloaded",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        loadingHelper.dismissDialog();
                        Toast.makeText(getApplicationContext(),"Error Occurred. Download Beat Again!",Toast.LENGTH_LONG).show();
                    }
                });
            }
    }
    public void isChecked()
    {

    }
}