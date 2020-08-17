package com.music.songcreator;

public class CreatedSongModel
{
    private int id;
    private String songname;
    private String recordingname;
    private String lyricsname;
    private int beatnum;

    public CreatedSongModel(int id,String songname,String lyricsname,String recordingname,int beatnum)
    {
        this.id = id;
        this.songname = songname;
        this.lyricsname = lyricsname;
        this.recordingname = recordingname;
        this.beatnum = beatnum;
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
    @Override
    public String toString() {
        return songname + "\n\n" + "id= " + id;
    }
}
