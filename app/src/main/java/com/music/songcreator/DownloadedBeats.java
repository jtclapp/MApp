package com.music.songcreator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    ImageButton B1;
    String child;
    String path;
    ArrayAdapter<String> adapter;
    Spinner BeatSpinner;
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
        BeatSpinner = findViewById(R.id.Beat_spinner);

        String[] arrayOfStrings = new String[23];
        arrayOfStrings[0] = " Call Me Now";
        arrayOfStrings[1] = " Blame Me";
        arrayOfStrings[2] = " Uppercut";
        arrayOfStrings[3] = " Only At Night";
        arrayOfStrings[4] = " Too Hot For You";
        arrayOfStrings[5] = " Just Go";
        arrayOfStrings[6] = " Let Me In";
        arrayOfStrings[7] = " Where Did You Go";
        arrayOfStrings[8] = " Just By Myself";
        arrayOfStrings[9] = " Champion Of The Fight";
        arrayOfStrings[10] = " Unbeatable";
        arrayOfStrings[11] = " Dad And I";
        arrayOfStrings[12] = " Late Night";
        arrayOfStrings[13] = " Scary Night";
        arrayOfStrings[14] = " On My Grind";
        arrayOfStrings[15] = " On My Mind";
        arrayOfStrings[16] = " Out Like A Light";
        arrayOfStrings[17] = " No Sleep For The Damned";
        arrayOfStrings[18] = " She's With Someone Else";
        arrayOfStrings[19] = " Party Time";
        arrayOfStrings[20] = " Her Loss";
        arrayOfStrings[21] = " This Is My Town";
        arrayOfStrings[22] = " What I Love The Most";

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOfStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BeatSpinner.setAdapter(adapter);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    getBeatName();
                    Download();
            }
        });
    }
    public void Download()
    {
            connected = isConnected();
            if(!connected)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
                builder.setMessage("Please connect to internet to download a beat!");
                builder.show();
            }
            if(connected)
            {
            final LoadingHelper loadingHelper = new LoadingHelper(DownloadedBeats.this);
            firebaseStorage = FirebaseStorage.getInstance();
            storageReference = firebaseStorage.getReference();
            ref = storageReference.child(child);
            File dir = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC) + File.separator + path);

        if (dir.exists())
            {
                String newfilepath = dir.getAbsolutePath();
                final File standin = new File(newfilepath);
                AlertDialog.Builder builder = new AlertDialog.Builder(DownloadedBeats.this);
                builder.setMessage("This beat appears to be already downloaded.");
                builder.setPositiveButton("Delete Download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean success = standin.delete();
                        if(success) {
                            Toast.makeText(getApplicationContext(), "Beat Successfully Deleted", Toast.LENGTH_SHORT).show();
                        }
                        if(!success)
                        {
                            Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.show();
            }
        else if(!dir.exists())
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

    public void getBeatName()
    {
        String str = (String) BeatSpinner.getSelectedItem();
        if(str.equals(" Call Me Now"))
        {
            child = "Rap Beat 1.mp3";
            path = "HipHop_Beat#1.mp3";
        }
        if(str.equals(" Blame Me"))
        {
            child = "Rap Beat 2.mp3";
            path = "HipHop_Beat#2.mp3";
        }
        if(str.equals(" Uppercut"))
        {
            child = "Rap_Beat_3.mp3";
            path = "HipHop_Beat#3.mp3";
        }
        if(str.equals(" Only At Night"))
        {
            child = "Rap Beat 4.mp3";
            path = "HipHop_Beat#4.mp3";
        }
        if(str.equals(" Too Hot For You"))
        {
            child = "Rap Beat 5.mp3";
            path = "HipHop_Beat#5.mp3";
        }
        if(str.equals(" Just Go"))
        {
            child = "Rap Beat 6.mp3";
            path = "HipHop_Beat#6.mp3";
        }
        if(str.equals(" Let Me In"))
        {
            child = "Rap Beat 7.mp3";
            path = "HipHop_Beat#7.mp3";
        }
        if(str.equals(" Where Did You Go"))
        {
            child = "Rock Beat 1.mp3";
            path = "Rock_Beat#1.mp3";
        }
        if(str.equals(" Just By Myself"))
        {
            child = "Rock Beat 2.mp3";
            path = "Rock_Beat#2.mp3";
        }
        if(str.equals(" Champion Of The Fight"))
        {
            child = "Rock Beat 3.mp3";
            path = "Rock_Beat#3.mp3";
        }
        if(str.equals(" Unbeatable"))
        {
            child = "rock beat 4.mp3";
            path = "Rock_Beat#4.mp3";
        }
        if(str.equals(" Dad And I"))
        {
            child = "Rock beat 5.mp3";
            path = "Rock_Beat#5.mp3";
        }
        if(str.equals(" Late Night"))
        {
            child = "R&B Beat_1.mp3";
            path = "R&B_Beat#1.mp3";
        }
        if(str.equals(" Scary Night"))
        {
            child = "R&B Beat 2.mp3";
            path = "R&B_Beat#2.mp3";
        }
        if(str.equals(" On My Grind"))
        {
            child = "R&B Beat 3.mp3";
            path = "R&B_Beat#3.mp3";
        }
        if(str.equals(" On My Mind"))
        {
            child = "R&B beat 4.mp3";
            path = "R&B_Beat#4.mp3";
        }
        if(str.equals(" Out Like A Light"))
        {
            child = "R&B beat 5.mp3";
            path = "R&B_Beat#5.mp3";
        }
        if(str.equals(" No Sleep For The Damned"))
        {
            child = "R&B Beat 6.mp3";
            path = "R&B_Beat#6.mp3";
        }
        if(str.equals(" She's With Someone Else"))
        {
            child = "Country beat 1.mp3";
            path = "Country_Beat#1.mp3";
        }
        if(str.equals(" Party Time"))
        {
            child = "country beat 2.mp3";
            path = "Country_Beat#2.mp3";
        }
        if(str.equals(" Her Loss"))
        {
            child = "country beat 3.mp3";
            path = "Country_Beat#3.mp3";
        }
        if(str.equals(" This Is My Town"))
        {
            child = "country-beat-4.mp3";
            path = "Country_Beat#4.mp3";
        }
        if(str.equals(" What I Love The Most"))
        {
            child = "country beat 5.mp3";
            path = "Country_Beat#5.mp3";
        }
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