package com.music.songcreator.SQLite;

public class AISongModel
{
    private int id;
    private String songTitle;
    private String song;
    private String voicename;
    private String voicelang;
    private String voicecountry;
    private float speed;
    private float pitch;
    private int beatnum;
    private float volume;

    public AISongModel(int id, String songTitle, String song, String voicename, String voicelang, String voicecountry, float speed, float pitch, int beatnum, float volume)
    {
        this.id = id;
        this.songTitle = songTitle;
        this.song = song;
        this.voicename = voicename;
        this.voicelang = voicelang;
        this.voicecountry = voicecountry;
        this.speed = speed;
        this.pitch = pitch;
        this.beatnum = beatnum;
        this.volume = volume;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSongTitle() {
        return songTitle;
    }
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }
    public String getVoicename() {
        return voicename;
    }
    public void setVoicename(String voicename) {
        this.voicename = voicename;
    }
    public String getVoicelang() {
        return voicelang;
    }
    public void setVoicelang(String voicelang) {
        this.voicelang = voicelang;
    }
    public String getVoicecountry() {
        return voicecountry;
    }
    public void setVoicecountry(String voicecountry) {
        this.voicecountry = voicecountry;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getPitch() {
        return pitch;
    }
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
    public int getBeatnum() {
        return beatnum;
    }
    public void setBeatnum(int beatnum) {
        this.beatnum = beatnum;
    }
    public float getVolume() {
        return volume;
    }
    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return songTitle + "\n\n" + "id= " + id;
    }
}
