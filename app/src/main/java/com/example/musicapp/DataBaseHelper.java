package com.example.musicapp;

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

    public DataBaseHelper(@Nullable Context context) {
        super(context, "storage.db", null, 1);
    }

    // this is called the first time the database is called
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + LYRICS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LYRICS_NAME + " TEXT, " + COLUMN_LYRICS + " TEXT)";
        String createTableStatement2 = "CREATE TABLE " + RECORDING_TABLE + " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECORDING_NAME + " TEXT)";
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
        } else {

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
        } else {

        }
        cursor.close();
        db.close();
        return returnlist;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP IF TABLE EXISTS " + LYRICS_TABLE);
        onCreate(sqLiteDatabase);
    }
}



