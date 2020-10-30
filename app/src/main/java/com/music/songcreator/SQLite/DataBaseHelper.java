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

    public DataBaseHelper(@Nullable Context context) {
        super(context, "SongCreatorDB.db", null, 1);
    }

    // this is called the first time the database is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + LYRICS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LYRICS_NAME + " TEXT, " + COLUMN_LYRICS + " TEXT)";
        String createTableStatement2 = "CREATE TABLE " + RECORDING_TABLE + " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECORDING_NAME + " TEXT)";
        String createTableStatement3 = "CREATE TABLE " + SONG_TABLE + " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SONG_NAME + " TEXT, " +
                                        COLUMN_LYRICS2 + " TEXT, " + COLUMN_RECORDING_NAME2 + " TEXT, " + COLUMN_BEAT_NUMBER + " INTEGER)";
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

                CreatedSongModel createdSongModel = new CreatedSongModel(SongID,Songname,Recordingname,Lyricsname,BeatNumber);
                returnlist.add(createdSongModel);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnlist;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}



