package com.example.musicapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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
    RadioButton RB1,RB2,RB3,RB4,RB5,RB6,RB7,RB8,RB9;
    ImageButton B1;
    String child;
    String path;
    boolean connected;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_beats);
        mAdView = findViewById(R.id.adView7);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        B1 = findViewById(R.id.DownloadButton);
        RB1 = findViewById(R.id.radioButton1);
        RB2 = findViewById(R.id.radioButton2);
        RB3 = findViewById(R.id.radioButton3);
        RB4 = findViewById(R.id.radioButton4);
        RB5 = findViewById(R.id.radioButton5);
        RB6 = findViewById(R.id.radioButton6);
        RB7 = findViewById(R.id.radioButton7);
        RB8 = findViewById(R.id.radioButton8);
        RB9 = findViewById(R.id.radioButton9);

        RB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rap Beat 1.mp3";
                path = "HipHop_Beat#1.mp3";
            }
        });
        RB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rap Beat 2.mp3";
                path = "HipHop_Beat#2.mp3";
            }
        });
        RB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rap_Beat_3.mp3";
                path = "HipHop_Beat#3.mp3";
            }
        });
        RB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "Rock Beat 1.mp3";
                path = "Rock_Beat#1.mp3";
            }
        });
        RB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "R&B Beat_1.mp3";
                path = "R&B_Beat#1.mp3";
            }
        });
        RB6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "R&B Beat 2.mp3";
                path = "R&B_Beat#2.mp3";
            }
        });
        RB7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                child = "R&B Beat 3.mp3";
                path = "R&B_Beat#3.mp3";
            }
        });
        RB8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                child = "R&B beat 4.mp3";
                path = "R&B_Beat#4.mp3";
            }
        });
        RB9.setOnClickListener(new View.OnClickListener() {
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
            connected = isConnected();
            if(connected == false)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
                builder.setMessage("Please connect to internet to download a beat!");
                builder.show();
            }
            if(connected == true)
            {
            final LoadingHelper loadingHelper = new LoadingHelper(DownloadedBeats.this);
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            ref = storageReference.child(child);
            File dir = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + path);

        if (dir.exists() == true)
            {
                String newfilepath = dir.getAbsolutePath();
                final File standin = new File(newfilepath);
                AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
                builder.setMessage("This beat appears to be already downloaded.");
                builder.setPositiveButton("Delete Download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean success = standin.delete();
                        Toast.makeText(getApplicationContext(),"Deleted = " + success,Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        else if(dir.exists() == false)
            {
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
                        Toast.makeText(getApplicationContext(),"This Beat Has Successfully Downloaded",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        loadingHelper.dismissDialog();
                        Toast.makeText(getApplicationContext(),"Error Occurred. Download Beat Again!",Toast.LENGTH_LONG).show();
                    }
                });
            }}}
    public boolean isConnected()
    {
        boolean result;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }
}