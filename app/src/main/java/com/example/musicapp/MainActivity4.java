package com.example.musicapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import static android.Manifest.permission;

public class MainActivity4 extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    public Set<String> a;
    private SeekBar seekBarpitch;
    private SeekBar seekBarspeed;
    EditText editText, et_name;
    Spinner spinner;
    Voice selectedvoice;
    String song;
    int idvalue;
    ArrayAdapter<String> adapter;
    float speed = 0.1f;
    float pitch = 0.1f;
    String country;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ActivityCompat.requestPermissions(this, new String[]{permission.RECORD_AUDIO, permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                Log Log = null;
                if (status == TextToSpeech.SUCCESS) {
                    // Setting speech language
                    // System.out.println(Locale.getAvailableLocales());
                    int result = textToSpeech.setLanguage(Locale.US);
                    // int result = tts.setLanguage(Locale.getDefault());
                    // If your device doesn't support language you set above
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Cook simple toast message with message
                        Toast.makeText(getApplicationContext(), "Language not supported", Toast.LENGTH_SHORT).show();
                        android.util.Log.e("textToSpeech", "Language is not supported");
                    }
                    // TTS is not initialized properly
                } else {
                    Toast.makeText(getApplicationContext(), "TextToSpeech Initilization Failed", Toast.LENGTH_SHORT).show();
                    android.util.Log.e("textToSpeech", "Initilization Failed");
                }
            }
        }, "com.google.android.tts");

        a = new HashSet<>();
        String[] names = {"Voice1", "Voice2", "Voice3", "Voice4", "Voice5", "Voice6", "Voice7", "Voice8"};

        spinner = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, names);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Cannot use any network voices since they do not load in at the right time.
                switch (i) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("en-gb-x-rjs#male_3-local", new Locale("en", "GB"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("en", "GB"));
                            language = "en";
                            country = "GB";
                        }
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("es-us-language", new Locale("es", "US"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("es", "US"));
                            language = "es";
                            country = "US";
                        }
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("en-in-x-ahp-local", new Locale("en", "IN"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("en", "AU"));
                            language = "en";
                            country = "AU";
                        }
                        break;
                    case 3:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("es-us-x-sfb#female_2-local", new Locale("es", "US"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("en", "IN"));
                            language = "en";
                            country = "IN";
                        }
                        break;
                    case 4:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("es-us-x-sfb#male_3-local", new Locale("es", "US"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("en", "Zh"));
                            language = "en";
                            country = "Zh";
                        }
                        break;
                    case 5:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("en-us-x-sfg#male_1-local", new Locale("en", "US"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("en", "DE"));
                            language = "en";
                            country = "DE";
                        }
                        break;
                    case 6:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("en-gb-x-fis#female_2-local", new Locale("en", "GB"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("es", "CA"));
                            language = "es";
                            country = "CA";
                        }
                        break;
                    case 7:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            selectedvoice = new Voice("en-AU-language", new Locale("en", "AU"), 600, 500, true, a);
                        } else {
                            textToSpeech.setLanguage(new Locale("en", "IT"));
                            language = "en";
                            country = "IT";
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        editText = findViewById(R.id.editTextTextMultiLine2);
        seekBarpitch = findViewById(R.id.seekBarPitch);
        seekBarspeed = findViewById(R.id.seekBarSpeed);
        et_name = findViewById(R.id.editTextTextPersonName2);
        Intent idintent = getIntent();
        idvalue = idintent.getIntExtra("idvalue",0);
    }
    public void TestAI(View view) {
        String song = "Hello, do you like the sound of my voice? Select me if you do!";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.setVoice(selectedvoice);
        }
        pitch = (float) seekBarpitch.getProgress() / 50;
        if (pitch < 0.1) {
            pitch = 0.1f;
        }
        speed = (float) seekBarspeed.getProgress() / 50;
        if (speed < 0.1) {
            speed = 0.1f;
        }
        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(song, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            textToSpeech.speak(song, TextToSpeech.QUEUE_FLUSH, null);
        }
    }
    public void LyricBuilder2(View view) {
        SongBuilder songBuilder = new SongBuilder();
        if(idvalue == 1)
        {
            songBuilder.CreatingRapVerse1();
        song = songBuilder.ReturningRap();
        String display = songBuilder.ReturningRapDisplay();
        editText.setText(display);
        editText.setMovementMethod(new ScrollingMovementMethod());
        }
        if(idvalue == 2)
        {
            songBuilder.CreatingRockVerse1();
            song = songBuilder.ReturningRock();
            String display2 = songBuilder.ReturningRockDisplay();
            editText.setText(display2);
            editText.setMovementMethod(new ScrollingMovementMethod());
        }
        if(idvalue == 3)
        {
            songBuilder.CreatingRandBVerse1();
            song = songBuilder.ReturningRandB();
            String display3 = songBuilder.ReturningRandBDisplay();
            editText.setText(display3);
            editText.setMovementMethod(new ScrollingMovementMethod());
        }
        if(idvalue == 4)
        {
            songBuilder.CreatingCountryVerse1();
            song = songBuilder.ReturningCountry();
            String display4 = songBuilder.ReturningCountryDisplay();
            editText.setText(display4);
            editText.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.act4menu, menu);
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
                    Toast.makeText(getApplicationContext(), "Error Creating Customer!", Toast.LENGTH_SHORT).show();
                    lyricsModel = new LyricsModel(-1, "error", "No Lyrics");
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity4.this);
                boolean success = dataBaseHelper.addOne(lyricsModel);
                Toast.makeText(getApplicationContext(), "Entry Added = " + success, Toast.LENGTH_SHORT).show();
                break;
            case R.id.UL:
                startSelectAct();
                break;
            case R.id.homeitem1:
                Intent home = new Intent(this,MainActivity.class);
                startActivity(home);
        }
        return true;
    }
    public void SendVoice(View view) {
        if(textToSpeech.isSpeaking())
        {
            textToSpeech.stop();
        }
        String voicename = " ";
        String voicecountry = " ";
        String voicelang = " ";
        String songtitle = et_name.getText().toString();
        speed = (float) seekBarspeed.getProgress() / 50;
        if (speed < 0.1) {
            speed = 0.1f;
        }
        pitch = (float) seekBarpitch.getProgress() / 50;
        if (pitch < 0.1) {
            pitch = 0.1f;
        }
        String editTextData = editText.getText().toString();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            voicename = selectedvoice.getName();
            voicecountry = selectedvoice.getLocale().getCountry();
            voicelang = selectedvoice.getLocale().getLanguage();
        } else {
            voicecountry = country;
            voicelang = language;
        }
        Intent i = new Intent(this, FinalActivity.class);
        i.putExtra("songtitle", songtitle);
        i.putExtra("speed", speed);
        i.putExtra("pitch", pitch);
        i.putExtra("voicename", voicename);
        i.putExtra("voicelang", voicelang);
        i.putExtra("voicecountry", voicecountry);
        i.putExtra("editTextData", editTextData);
        i.putExtra("song",song);

        String print = "Press Load AI First";
        Toast.makeText(getApplicationContext(), print, Toast.LENGTH_LONG).show();

        Intent myintent2 = getIntent();
        int svalue = myintent2.getIntExtra("svalue", 0);
        Intent myintent3 = getIntent();
        float volume = myintent3.getFloatExtra("volume",0);

        i.putExtra("svalue", svalue);
        i.putExtra("volume",volume);
        startActivity(i);
    }

    public void startSelectAct() {
        Intent i = new Intent(this, Selector.class);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strName = data.getStringExtra("name");
                String strLyrics = data.getStringExtra("lyrics");
                et_name.setText(strName);
                editText.setText(strLyrics);
                String place = editText.getText().toString();
                String[] parts = place.split("\n");
                for(int j = 0; j < parts.length; j++)
                {
                    parts[j] = parts[j].concat("...");
                }
                for(int i = 0; i < parts.length; i++)
                {
                    song += parts[i];
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
