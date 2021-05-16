package com.music.songcreator.java_operations;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.music.songcreator.activities.BeatPage1;
import com.music.songcreator.activities.BeatPage2;
import com.music.songcreator.activities.Beatpage3;
import com.music.songcreator.activities.Beatpage4;
import com.music.songcreator.R;

import java.io.File;
import java.io.IOException;

public class DownloadedBeats extends AppCompatActivity {
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    BeatFileSelector beatFileSelector;
    StorageReferenceSelector selector;
    boolean connected;
    Intent beatIntent;
    int beatnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_beats);
        selector = new StorageReferenceSelector();
        beatFileSelector = new BeatFileSelector();
        beatIntent = getIntent();
        connected = isConnected();
        beatnum = beatIntent.getIntExtra("check",0);
        Download();

    }
    public void Download() {
            final Intent intent = getPageIntent();
            final LoadingHelper loadingHelper = new LoadingHelper(DownloadedBeats.this);
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            ref = storageReference.child(selector.getStorageReference(beatFileSelector.FileSelector(beatnum)));
        if (connected) {
            File dir = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + beatFileSelector.FileSelector(beatnum));

            loadingHelper.startLoadingDialog();
            try {
                dir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ref.getFile(dir).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    loadingHelper.dismissDialog();
                    Toast.makeText(getApplicationContext(), "This Beat Has Successfully Downloaded", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    loadingHelper.dismissDialog();
                    Toast.makeText(getApplicationContext(), "Error Occurred. Download Beat Again!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });
        }
        else
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
            builder.setMessage("Connect to internet to download this beat.");
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(intent);
                }
            });
            builder.show();
        }
    }
    public Intent getPageIntent()
    {
        Intent intent = new Intent();
        if(beatnum == 24 || beatnum == 25 || beatnum == 26 || beatnum == 27 || beatnum == 1 || beatnum == 2 || beatnum == 3)
        {
            intent = new Intent(getApplicationContext(), BeatPage1.class);
        }
        if(beatnum == 4 || beatnum == 5 || beatnum == 6 || beatnum == 7 || beatnum == 29)
        {
            intent = new Intent(getApplicationContext(), BeatPage2.class);
        }
        if(beatnum == 8 || beatnum == 9 || beatnum == 10 || beatnum == 11 || beatnum == 35 || beatnum == 36)
        {
            intent = new Intent(getApplicationContext(), Beatpage3.class);
        }
        if(beatnum == 13 || beatnum == 14 || beatnum == 15 || beatnum == 16 || beatnum == 17)
        {
            intent = new Intent(getApplicationContext(), Beatpage4.class);
        }
        return intent;
    }
    public boolean isConnected()
    {
        boolean result;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            result = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        }
        catch (Exception e)
        {
            result = false;
        }
        return result;
    }
}