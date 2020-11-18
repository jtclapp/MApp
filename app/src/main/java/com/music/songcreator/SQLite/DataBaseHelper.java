package com.music.songcreator.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String LYRICS_TABLE = "LYRICS_TABLE";
    public static final String COLUMN_LYRICS_NAME = "LYRICS_NAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LYRICS = "COLUMN_ACTIVE_SONG";
    public static final String RECORDING_TABLE = "RECORDING_TABLE";
    public static final String COLUMN_RECORDING_NAME = "RECORDING_NAME";
    public static final String COLUMN_ID2 = "ID";
    private static final String SONG_TABLE = "SONG_TABLE";
    private static final String COLUMN_ID3 = "ID";
    private static final String COLUMN_SONG_NAME = "SONG_NAME";
    private static final String COLUMN_LYRICS2 = "LYRICS";
    private static final String COLUMN_RECORDING_NAME2 = "RECORDING_NAME";
    private static final String COLUMN_BEAT_NUMBER = "Beat_Number";
    private static final String COLUMN_HZ_NUMBER = "Hz_Number";
    private static final String COLUMN_BEAT_VOLUME = "Beat_Volume";
    private static final String AI_TABLE = "AI_TABLE";
    private static final String COLUMN_ID4 = "ID";
    private static final String COLUMN_SONG_NAME2 = "SONG_NAME";
    private static final String COLUMN_LYRICS3 = "LYRICS";
    private static final String COLUMN_VOICE_NAME = "Voice_Name";
    private static final String COLUMN_VOICE_LANG = "Voice_Lang";
    private static final String COLUMN_VOICE_COUNTRY = "Voice_Country";
    private static final String COLUMN_SPEED = "SPEED";
    private static final String COLUMN_PITCH = "PITCH";
    private static final String COLUMN_BEAT_NUMBER2 = "Beat_Number";
    private static final String COLUMN_BEAT_VOLUME2 = "Beat_Volume";



    public DataBaseHelper(@Nullable Context context) {
        super(context, "SongCreatorDB.db", null, 2);
    }

    // this is called the first time the database is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + LYRICS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LYRICS_NAME + " TEXT, " + COLUMN_LYRICS + " TEXT)";
        String createTableStatement2 = "CREATE TABLE " + RECORDING_TABLE + " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECORDING_NAME + " TEXT)";
        String createTableStatement3 = "CREATE TABLE " + SONG_TABLE + " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SONG_NAME + " TEXT, " +
                                        COLUMN_LYRICS2 + " TEXT, " + COLUMN_RECORDING_NAME2 + " TEXT, " + COLUMN_BEAT_NUMBER + " INTEGER, " + COLUMN_HZ_NUMBER + " INTEGER, " +
                                        COLUMN_BEAT_VOLUME + " FLOAT)";
        String createTableStatement4 = "CREATE TABLE " + AI_TABLE + " (" + COLUMN_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SONG_NAME2 + " TEXT, " + COLUMN_LYRICS3 + " TEXT, " +
                COLUMN_VOICE_NAME + " TEXT, " + COLUMN_VOICE_LANG + " TEXT, " + COLUMN_VOICE_COUNTRY + " TEXT, " + COLUMN_SPEED + " FLOAT, " + COLUMN_PITCH + " FLOAT, " +
                COLUMN_BEAT_NUMBER2 + " INTEGER, " + COLUMN_BEAT_VOLUME2 + " FLOAT)";

        db.execSQL(createTableStatement4);
        db.execSQL(createTableStatement3);
        db.execSQL(createTableStatement2);
        db.execSQL(createTableStatement);
    }

    public boolean addOne(LyricsModel lyricsModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LYRICS_NAME, lyricsModel.getName());
        cv.put(COLUMN_LYRICS, lyricsModel.getLyrics());

        long insert = db.insert(LYRICS_TABLE, null, cv);
        return insert != -1;
    }

    public boolean DeleteOne(LyricsModel lyricsModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + LYRICS_TABLE + " WHERE " + COLUMN_ID + " = " + lyricsModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public boolean SelectOne(LyricsModel lyricsModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT FROM " + LYRICS_TABLE + " WHERE " + COLUMN_ID + " = " + lyricsModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }

    public List<LyricsModel> getEveryone() {
        List<LyricsModel> returnlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + LYRICS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // Loop through the results and create new objects
            do {
                int lyricsID = cursor.getInt(0);
                String lyricsname = cursor.getString(1);
                String lyricstext = cursor.getString(2);

                LyricsModel newlyric = new LyricsModel(lyricsID, lyricsname, lyricstext);
                returnlist.add(newlyric);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }

    public boolean addOneRecording(RecordingModel recordingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RECORDING_NAME, recordingModel.getName());

        long insert = db.insert(RECORDING_TABLE, null, cv);
        return insert != -1;
    }

    public boolean updateOneRecording(RecordingModel recordingModel, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        File filefrom = new File(recordingModel.getName());
        File fileto = new File(name);
        filefrom.renameTo(fileto);
        cv.put(COLUMN_RECORDING_NAME, name);
        db.update(RECORDING_TABLE, cv, COLUMN_ID2 + " = ?", new String[]{String.valueOf(recordingModel.getId())});
        return true;
    }

    public boolean DeleteOneRecording(RecordingModel recordingModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + RECORDING_TABLE + " WHERE " + COLUMN_ID2 + " = " + recordingModel.getId();

        if (recordingModel.getName() != null) {
            File file = new File(recordingModel.getName());
            boolean delete = file.delete();
        }

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }
    public List<RecordingModel> getEveryone2() {
        List<RecordingModel> returnlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + RECORDING_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // Loop through the results and create new objects
            do {
                int RecordingID = cursor.getInt(0);
                String Recordingname = cursor.getString(1);

                RecordingModel clickedRecording = new RecordingModel(RecordingID, Recordingname);
                returnlist.add(clickedRecording);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }

    public boolean addOneSong(CreatedSongModel createdSongModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SONG_NAME, createdSongModel.getSongname());
        cv.put(COLUMN_LYRICS2, createdSongModel.getLyricsname());
        cv.put(COLUMN_RECORDING_NAME2, createdSongModel.getRecordingname());
        cv.put(COLUMN_BEAT_NUMBER, createdSongModel.getBeatnum());
        cv.put(COLUMN_HZ_NUMBER,createdSongModel.getHz());
        cv.put(COLUMN_BEAT_VOLUME,createdSongModel.getVolume());

        long insert = db.insert(SONG_TABLE, null, cv);
        return insert != -1;
    }
    public boolean updateOneSong(CreatedSongModel createdSongModel, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RECORDING_NAME2, name);
        db.update(SONG_TABLE, cv, COLUMN_ID3 + " = ?", new String[]{String.valueOf(createdSongModel.getId())});
        return true;
    }
    public boolean DeleteOneSong(CreatedSongModel createdSongModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + SONG_TABLE + " WHERE " + COLUMN_ID3 + " = " + createdSongModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }
    public List<CreatedSongModel> getEveryone3() {
        List<CreatedSongModel> returnlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + SONG_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // Loop through the results and create new objects
            do {
                int SongID = cursor.getInt(0);
                String Songname = cursor.getString(1);
                String Recordingname = cursor.getString(2);
                String Lyricsname = cursor.getString(3);
                int BeatNumber = cursor.getInt(4);
                int hz = cursor.getInt(5);
                float volume = cursor.getFloat(6);
                CreatedSongModel createdSongModel = new CreatedSongModel(SongID,Songname,Recordingname,Lyricsname,BeatNumber,hz,volume);
                returnlist.add(createdSongModel);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnlist;
    }

    public boolean addOneAISong(AISongModel aiSongModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        private static final String AI_TABLE = "AI_TABLE";
//        private static final String COLUMN_ID4 = "ID";
//        private static final String COLUMN_SONG_NAME2 = "SONG_NAME";
//        private static final String COLUMN_LYRICS3 = "LYRICS";
//        private static final String COLUMN_VOICE_NAME = "Voice_Name";
//        private static final String COLUMN_VOICE_LANG = "Voice_Lang";
//        private static final String COLUMN_VOICE_COUNTRY = "Voice_Country";
//        private static final String COLUMN_SPEED = "SPEED";
//        private static final String COLUMN_PITCH = "PITCH";
//        private static final String COLUMN_BEAT_NUMBER2 = "Beat_Number";
//        private static final String COLUMN_BEAT_VOLUME2 = "Beat_Volume";
        cv.put(COLUMN_SONG_NAME2,aiSongModel.getSongTitle());
        cv.put(COLUMN_LYRICS3,aiSongModel.getSong());
        cv.put(COLUMN_VOICE_NAME, aiSongModel.getVoicename());
        cv.put(COLUMN_VOICE_LANG, aiSongModel.getVoicelang());
        cv.put(COLUMN_VOICE_COUNTRY,aiSongModel.getVoicecountry());
        cv.put(COLUMN_SPEED,aiSongModel.getSpeed());
        cv.put(COLUMN_PITCH,aiSongModel.getPitch());
        cv.put(COLUMN_BEAT_NUMBER2,aiSongModel.getBeatnum());
        cv.put(COLUMN_BEAT_VOLUME2,aiSongModel.getVolume());

        long insert = db.insert(AI_TABLE, null, cv);
        return insert != -1;
    }
    public boolean updateOneAISong(AISongModel aiSongModel, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SONG_NAME2, name);
        db.update(AI_TABLE, cv, COLUMN_ID4 + " = ?", new String[]{String.valueOf(aiSongModel.getId())});
        return true;
    }
    public boolean DeleteOneAISong(AISongModel aiSongModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + AI_TABLE + " WHERE " + COLUMN_ID4 + " = " + aiSongModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        return cursor.moveToFirst();
    }
    public List<AISongModel> getEveryone4() {
        List<AISongModel> returnlist = new ArrayList<>();
        String queryString = "SELECT * FROM " + AI_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // Loop through the results and create new objects
            do {
                int SongID = cursor.getInt(0);
                String Songname = cursor.getString(1);
                String Lyricsname = cursor.getString(2);
                String voicename = cursor.getString(3);
                String voicelang = cursor.getString(4);
                String voicecountry = cursor.getString(5);
                float speed = cursor.getFloat(6);
                float pitch = cursor.getFloat(7);
                int beatnum = cursor.getInt(8);
                float volume = cursor.getFloat(9);
                AISongModel aiSongModel = new AISongModel(SongID,Songname,Lyricsname,voicename,voicelang,voicecountry,speed,pitch,beatnum,volume);
                returnlist.add(aiSongModel);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnlist;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String createTableStatement4 = "CREATE TABLE " + AI_TABLE + " (" + COLUMN_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SONG_NAME2 + " TEXT, " + COLUMN_LYRICS3 + " TEXT, " +
                    COLUMN_VOICE_NAME + " TEXT, " + COLUMN_VOICE_LANG + " TEXT, " + COLUMN_VOICE_COUNTRY + " TEXT, " + COLUMN_SPEED + " FLOAT, " + COLUMN_PITCH + " FLOAT, " +
                    COLUMN_BEAT_NUMBER2 + " INTEGER, " + COLUMN_BEAT_VOLUME2 + " FLOAT)";
            String createTableStatement3 = "CREATE TABLE " + SONG_TABLE + " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SONG_NAME + " TEXT, " +
                COLUMN_LYRICS2 + " TEXT, " + COLUMN_RECORDING_NAME2 + " TEXT, " + COLUMN_BEAT_NUMBER + " INTEGER, " + COLUMN_HZ_NUMBER + " INTEGER, " +
                COLUMN_BEAT_VOLUME + " FLOAT)";
            String drop = "DROP TABLE " + SONG_TABLE;
            String dropAI = "DROP TABLE " + AI_TABLE;
            db.execSQL(dropAI);
            db.execSQL(drop);
            db.execSQL(createTableStatement4);
            db.execSQL(createTableStatement3);
    }
}



