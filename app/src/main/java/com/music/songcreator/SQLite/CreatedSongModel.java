package com.music.songcreator.SQLite;

import java.io.Serializable;

public class CreatedSongModel implements Serializable
{
    private int id;
    private String songname;
    private String recordingname;
    private String lyricsname;
    private int beatnum;
    private int hz;
    private float volume;

    public CreatedSongModel(int id, String songname, String lyricsname, String recordingname, int beatnum, int hz, float volume)
    {
        this.id = id;
        this.songname = songname;
        this.lyricsname = lyricsname;
        this.recordingname = recordingname;
        this.beatnum = beatnum;
        this.hz = hz;
        this.volume = volume;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }
    public String getRecordingname() {
        return recordingname;
    }

    public void setRecordingname(String recordingname) {
        this.recordingname = recordingname;
    }
    public String getLyricsname() {
        return lyricsname;
    }

    public void setLyricsname(String lyricsname) {
        this.lyricsname = lyricsname;
    }
    public int getBeatnum() {
        return beatnum;
    }
    public void setBeatnum(int beatnum) {
        this.beatnum = beatnum;
    }
    public int getHz() {
        return hz;
    }

    public void setHz(int hz) {
        this.hz = hz;
    }
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
    @Override
    public String toString() {
        return songname + "\n\n" + "id= " + id;
    }
}
