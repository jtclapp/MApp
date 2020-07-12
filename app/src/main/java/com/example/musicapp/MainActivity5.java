package com.example.musicapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
    public static Boolean recording;
    public Spinner spFrequency, UpDown, UpDown2;
    Button setbutton;
    ToggleButton custombutton, play;
    EditText editText, et_name;
    ArrayAdapter<String> adapter, adapter2;
    AudioTrack audioTrack;
    int buffersizeinbytes;
    private int STORAGE_PERMISSSION_CODE = 1;

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
                k.putExtra("svalue", svalue);
                startActivity(k);
            }
        };
        ss.setSpan(clickableSpan1, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        String[] arrayOfStrings = new String[8];
        arrayOfStrings[0] = "Slow Motion";
        arrayOfStrings[1] = "Robot";
        arrayOfStrings[2] = "Deep Pitch";
        arrayOfStrings[3] = "Normal";
        arrayOfStrings[4] = "High Pitch";
        arrayOfStrings[5] = "Squeaky Pitch";
        arrayOfStrings[6] = "Helium";
        arrayOfStrings[7] = "Fast Forward";

        final String[] arrayOfStrings2 = new String[5];
        arrayOfStrings2[0] = "                 ";
        arrayOfStrings2[1] = "Download Lyrics";
        arrayOfStrings2[2] = "Upload Lyrics";
        arrayOfStrings2[3] = "Download Recording";
        arrayOfStrings2[4] = "Upload Recording";

        UpDown = findViewById(R.id.Download_Upload);
        UpDown2 = findViewById(R.id.Download_Upload);
        spFrequency = findViewById(R.id.Voice_spinner);
        custombutton = findViewById(R.id.CustomButton);
        play = findViewById(R.id.playrec);
        play.setVisibility(View.INVISIBLE);
        setbutton = findViewById(R.id.button3);
        editText = findViewById(R.id.editTextTextMultiLine);
        et_name = findViewById(R.id.editTextTextPersonName);
        buffersizeinbytes = 0;

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
                    custombutton.setActivated(true);
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
                } else {
                    custombutton.setActivated(false);
                    setbutton.setEnabled(true);
                    play.setVisibility(View.VISIBLE);

                    stoprecording();
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
                if (play.isChecked() == false) {
                    play.setActivated(false);
                    pauseRecord();
                }
            }
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOfStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFrequency.setAdapter(adapter);

        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayOfStrings2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UpDown.setAdapter(adapter2);
        UpDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        TextView tv = (TextView) view;
                        tv.setTextSize(10);
                        tv.setText("Download/Upload");
                        tv.setTextColor(Color.DKGRAY);
                    case 1:
                        LyricsModel lyricsModel;
                        try {
                            lyricsModel = new LyricsModel(-1, et_name.getText().toString(), editText.getText().toString());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error Creating Lyrics!", Toast.LENGTH_SHORT).show();
                            lyricsModel = new LyricsModel(-1, "error", "No Lyrics");
                        }
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity5.this);
                        boolean success = dataBaseHelper.addOne(lyricsModel);
                        Toast.makeText(getApplicationContext(), "Entry Added = " + success, Toast.LENGTH_LONG).show();
                        tv = (TextView) view;
                        tv.setTextSize(10);
                        tv.setText("Download/Upload");
                        tv.setTextColor(Color.DKGRAY);
                        break;
                    case 2:
                        startSelectAct();
                        tv = (TextView) view;
                        tv.setTextSize(10);
                        tv.setText("Download/Upload");
                        tv.setTextColor(Color.DKGRAY);
                        break;
                    case 3:
                        if (path != null) {
                            RecordingModel recordingModel;
                            try {
                                recordingModel = new RecordingModel(-1, path);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error Creating Recording!", Toast.LENGTH_SHORT).show();
                                recordingModel = new RecordingModel(-1, "error");
                            }
                            DataBaseHelper dataBaseHelper2 = new DataBaseHelper(MainActivity5.this);
                            success = dataBaseHelper2.addOneRecording(recordingModel);
                            Toast.makeText(getApplicationContext(), "Saved = " + success, Toast.LENGTH_SHORT).show();
                            tv = (TextView) view;
                            tv.setTextSize(10);
                            tv.setText("Download/Upload");
                            tv.setTextColor(Color.DKGRAY);
                            break;
                        } else {
                            Toast.makeText(getApplicationContext(), "No Recording to save", Toast.LENGTH_LONG).show();
                            tv = (TextView) view;
                            tv.setTextSize(10);
                            tv.setText("Download/Upload");
                            tv.setTextColor(Color.DKGRAY);
                            break;
                        }
                    case 4:
                        startSelectAct2();
                        tv = (TextView) view;
                        tv.setTextSize(10);
                        tv.setText("Download/Upload");
                        tv.setTextColor(Color.DKGRAY);
                        break;
                }
                UpDown2.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            custombutton.setEnabled(true);
        }
    }

    private void startRecord() throws IOException {
        path = Environment.getExternalStorageDirectory() + "/Recording_" + System.currentTimeMillis() + ".pcm";
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
            audioTrack.write(audioData, 0, buffersizeinbytes);
        }
    }

    public void pauseRecord() {
        if (audioTrack != null) {
            audioTrack.pause();
        }
    }

    public int GetHZ() {
        //arrayOfStrings[0] = "Slow Motion";
        //arrayOfStrings[1] = "Robot";
        //arrayOfStrings[2] = "Deep Pitch";
        //arrayOfStrings[3] = "Normal";
        //arrayOfStrings[4] = "High Pitch";
        //arrayOfStrings[5] = "Squeaky Pitch";
        //arrayOfStrings[6] = "Helium";
        //arrayOfStrings[7] = "Fast Forward";
        int i = 0;
        String str = (String) spFrequency.getSelectedItem();
        if (str.equals("Slow Motion")) {
            i = 6050;
        }
        if (str.equals("Robot")) {
            i = 8500;
        }
        if (str.equals("Deep Pitch")) {
            i = 9300;
        }
        if (str.equals("Normal")) {
            i = 11025;
        }
        if (str.equals("High Pitch")) {
            i = 13000;
        }
        if (str.equals("Squeaky Pitch")) {
            i = 15150;
        }
        if (str.equals("Helium")) {
            i = 19000;
        }
        if (str.equals("Fast Forward")) {
            i = 25000;
        }
        return i;
    }

    public void LyricBuilder(View view) {
        SongBuilder songBuilder = new SongBuilder();
        songBuilder.CreatingRapVerse1();
        String display = songBuilder.ReturningRapDisplay();
        editText.setText(display);
        editText.setMovementMethod(new ScrollingMovementMethod());
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
            }
        }
    }

    public void stoprecording() {
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
        Intent myintent = getIntent();
        int svalue = myintent.getIntExtra("check", 0);
        int sampleRateInHz = GetHZ();
        int shortSizeInBytes = Short.SIZE / Byte.SIZE;
        buffersizeinbytes = (int) (file.length() / shortSizeInBytes);
        String editTextData = editText.getText().toString();

        Intent intent = new Intent(this, FinalActivity.class);
        intent.putExtra("svalue", svalue);
        intent.putExtra("sampleRateInHz", sampleRateInHz);
        intent.putExtra("buffersizeinbytes", buffersizeinbytes);
        intent.putExtra("path", path);
        intent.putExtra("editTextData", editTextData);
        startActivity(intent);
    }
}

