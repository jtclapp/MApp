package com.example.musicapp;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity5 extends AppCompatActivity {
    String path;
    File file;
    private Chronometer chronometer;
    private long stopOffset;
    public static Boolean recording;
    public Spinner spFrequency;
    Button setbutton;
    ToggleButton custombutton,play;
    EditText editText, et_name;
    ArrayAdapter<String> adapter;
    AudioTrack audioTrack;
    int buffersizeinbytes,idvalue;
    LoadingHelper loadingHelper = new LoadingHelper(MainActivity5.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView textView = findViewById(R.id.textView5);
        String textViewtext = "Click HERE to create an AI Voice for your song.";
        SpannableString ss = new SpannableString(textViewtext);
        final Intent k = new Intent(this, MainActivity4.class);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent myintent = getIntent();
                int svalue = myintent.getIntExtra("check", 0);
                Intent myintent2 = getIntent();
                float volume = myintent2.getFloatExtra("setVolume",0);
                k.putExtra("volume",volume);
                k.putExtra("svalue", svalue);
                k.putExtra("idvalue",idvalue);
                startActivity(k);
            }
        };
        ss.setSpan(clickableSpan1, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        String[] arrayOfStrings = new String[9];
        arrayOfStrings[0] = "Slow Motion";
        arrayOfStrings[1] = "Bass";
        arrayOfStrings[2] = "Alto";
        arrayOfStrings[3] = "Normal";
        arrayOfStrings[4] = "Tenor";
        arrayOfStrings[5] = "Soprano";
        arrayOfStrings[6] = "Coloratura Soprano";
        arrayOfStrings[7] = "Helium";
        arrayOfStrings[8] = "2x Speed";

        spFrequency = findViewById(R.id.Voice_spinner);
        custombutton = findViewById(R.id.CustomButton);
        play = findViewById(R.id.playrec);
        play.setVisibility(View.INVISIBLE);
        setbutton = findViewById(R.id.button3);
        editText = findViewById(R.id.editTextTextMultiLine);
        et_name = findViewById(R.id.editTextTextPersonName);
        final LoadingHelper loadingHelper = new LoadingHelper(MainActivity5.this);
        chronometer = findViewById(R.id.chronometer);
        buffersizeinbytes = 0;
        Intent idintent = getIntent();
        idvalue = idintent.getIntExtra("id",0);

        custombutton.setEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
            custombutton.setEnabled(true);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            custombutton.setEnabled(true);
        }
        custombutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (custombutton.isChecked()) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - stopOffset);
                    chronometer.setVisibility(View.VISIBLE);
                    chronometer.start();
                    custombutton.setActivated(true);
                    play.setVisibility(View.INVISIBLE);
                    setbutton.setEnabled(false);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            recording = true;
                            try {
                                startRecord();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                else {
                    chronometer.stop();
                    stopOffset = 0;
                    custombutton.setActivated(false);
                    setbutton.setEnabled(true);
                    play.setVisibility(View.VISIBLE);
                    stoprecording();
                    if (path != null) {
                        RecordingModel recordingModel;
                        try {
                            recordingModel = new RecordingModel(-1, path);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error Creating Recording!", Toast.LENGTH_SHORT).show();
                            recordingModel = new RecordingModel(-1, "error");
                        }
                        DataBaseHelper dataBaseHelper2 = new DataBaseHelper(MainActivity5.this);
                        dataBaseHelper2.addOneRecording(recordingModel);
                        Toast.makeText(getApplicationContext(), "Recording Successfully Saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No Recording to save", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play.isChecked()) {
                    play.setActivated(true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                playRecord();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                if (play.isChecked() == false || play.isActivated() == false) {
                    play.setActivated(false);
                    pauseRecord();
                }
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOfStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrequency.setAdapter(adapter);
        setbutton.setEnabled(false);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            custombutton.setEnabled(true);
        }
    }

    private void startRecord() throws IOException {
        path = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + "Recording_" + System.currentTimeMillis() + ".pcm";
        file = new File(path);
        file.createNewFile();

        OutputStream outputStream = new FileOutputStream(file);

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);

        int minBufferSize = AudioRecord.getMinBufferSize(11025, 2, 2);

        short[] audioData = new short[minBufferSize];

        AudioRecord audioRecord = new AudioRecord(1, 11025, 2, 2, minBufferSize);

        audioRecord.startRecording();

        while (recording) {
            int numberOfShort = audioRecord.read(audioData, 0, minBufferSize);

            for (int i = 0; i < numberOfShort; i++) {
                dataOutputStream.writeShort(audioData[i]);
            }
        }
        if (!recording.booleanValue()) {
            audioRecord.stop();
            dataOutputStream.close();
        }
    }
    private void playRecord() throws IOException {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingHelper.startLoadingDialog();
            }
        });
        int i = 0;
        int shortSizeInBytes = Short.SIZE / Byte.SIZE;
        buffersizeinbytes = (int) (file.length() / shortSizeInBytes);
        short[] audioData = new short[buffersizeinbytes];
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        int j = 0;
        while (dataInputStream.available() > 0) {
            audioData[j] = dataInputStream.readShort();
            j++;
        }
        dataInputStream.close();
        i = GetHZ();
        if (buffersizeinbytes != 0) {
            audioTrack = new AudioTrack(3, i, 2, 2, buffersizeinbytes, 1);
            audioTrack.play();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingHelper.dismissDialog();
                }
            });
            audioTrack.write(audioData, 0, buffersizeinbytes);
        }
        play.setActivated(false);
    }
    public void pauseRecord() {
        if (audioTrack != null) {
            audioTrack.pause();
        }
    }
    public int GetHZ() {
        //arrayOfStrings[0] = "Slow Motion";
        //arrayOfStrings[1] = "Bass";
        //arrayOfStrings[2] = "Alto";
        //arrayOfStrings[3] = "Normal";
        //arrayOfStrings[4] = "Tenor";
        //arrayOfStrings[5] = "Soprano";
        //arrayOfStrings[6] = "Coloratura Soprano";
        //arrayOfStrings[7] = "Helium";
        //arrayOfStrings[8] = "2x Speed";
        int i = 0;
        String str = (String) spFrequency.getSelectedItem();
        if (str.equals("Slow Motion")) {
            i = 6400;
        }
        if (str.equals("Bass")) {
            i = 8450;
        }
        if (str.equals("Alto")) {
            i = 9800;
        }
        if (str.equals("Normal")) {
            i = 11025;
        }
        if(str.equals("Tenor"))
        {
            i = 12800;
        }
        if (str.equals("Soprano")) {
            i = 14100;
        }
        if (str.equals("Coloratura Soprano")) {
            i = 15000;
        }
        if (str.equals("Helium")) {
            i = 20000;
        }
        if (str.equals("2x Speed")) {
            i = 24200;
        }
        return i;
    }

    public void LyricBuilder(View view) {
        SongBuilder songBuilder = new SongBuilder();
        if(idvalue == 1)
        {
            songBuilder.CreatingRapVerse1();
            String display = songBuilder.ReturningRapDisplay();
            editText.setText(display);
            editText.setMovementMethod(new ScrollingMovementMethod());
        }
        if(idvalue == 2)
        {
            songBuilder.CreatingRockVerse1();
        }
        if(idvalue == 3)
        {
            songBuilder.CreatingRandBVerse1();
        }
        if(idvalue == 4)
        {
            songBuilder.CreatingCountryVerse1();
        }
    }
    public void startSelectAct() {
        Intent i = new Intent(this, Selector.class);
        startActivityForResult(i, 1);
    }

    public void startSelectAct2() {
        Intent i = new Intent(this, RecSelector.class);
        startActivityForResult(i, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strName = data.getStringExtra("name");
                String strLyrics = data.getStringExtra("lyrics");
                et_name.setText(strName);
                editText.setText(strLyrics);
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                path = data.getStringExtra("name");
                file = new File(path);
                play.setVisibility(View.VISIBLE);
                setbutton.setEnabled(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.act5menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.DL:
                LyricsModel lyricsModel;
                try {
                    lyricsModel = new LyricsModel(-1, et_name.getText().toString(), editText.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error Creating Lyrics!", Toast.LENGTH_SHORT).show();
                    lyricsModel = new LyricsModel(-1, "error", "No Lyrics");
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity5.this);
                boolean success = dataBaseHelper.addOne(lyricsModel);
                if(success)
                {
                    Toast.makeText(getApplicationContext(), "Lyrics were successfully saved.", Toast.LENGTH_LONG).show();
                }
                if(!success)
                {
                    Toast.makeText(getApplicationContext(),"Error occurred when trying to save lyrics.", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.UL:
                startSelectAct();
                break;
            case R.id.UR:
                startSelectAct2();
                break;
            case  R.id.homeitem2:
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);

        }
        return true;
    }

    public void stoprecording() {
        setbutton.setEnabled(true);
        recording = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recording = false;
        if (audioTrack != null) {
            audioTrack.release();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void SetRecord(View view) {
        pauseRecord();
        Intent myintent = getIntent();
        Intent myintent2 = getIntent();
        int svalue = myintent.getIntExtra("check", 0);
        float volume = myintent2.getFloatExtra("setVolume",0);
        int sampleRateInHz = GetHZ();
        int shortSizeInBytes = Short.SIZE / Byte.SIZE;
        buffersizeinbytes = (int) (file.length() / shortSizeInBytes);
        String editTextData = editText.getText().toString();
        String songtitle = et_name.getText().toString();

        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra("songtitle", songtitle);
        intent.putExtra("svalue", svalue);
        intent.putExtra("volume",volume);
        intent.putExtra("sampleRateInHz", sampleRateInHz);
        intent.putExtra("buffersizeinbytes", buffersizeinbytes);
        intent.putExtra("path", path);
        intent.putExtra("editTextData", editTextData);
        startActivity(intent);
    }
}

