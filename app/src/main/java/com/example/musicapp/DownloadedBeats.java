package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class DownloadedBeats extends AppCompatActivity
{
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_beats);
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        ref = storageReference.child("Rap_Beat_3.mp3");
        final File dir = new File(Environment.getExternalStorageDirectory() + "/HipHop_Beat#3" + ".mp3" );
        if(dir.exists() == true)
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
            builder.setMessage("This beat appears to be already downloaded. Would you like to delete it?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which)
                {
                    boolean deleted = dir.delete();
                    Toast.makeText(getApplicationContext(),"File deleted: " + deleted, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Cancel",null);
            builder.show();
        }
        try {
            dir.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final File finalLocalFile = dir;
        ref.getFile(dir).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot)
            {
                String path = finalLocalFile.getAbsolutePath();
                MediaPlayer player = new MediaPlayer();
                try {
                    player.setDataSource(path);
                    player.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                player.start();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });


    }
}