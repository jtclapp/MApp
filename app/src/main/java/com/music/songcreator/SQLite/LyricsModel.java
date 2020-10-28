package com.music.songcreator.SQLite;

public class LyricsModel {
    private int id;
    private String name;
    private String lyrics;

    public LyricsModel(int id, String name, String lyrics) {
        this.id = id;
        this.name = name;
        this.lyrics = lyrics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TITLE: " + name + "\n\n" + lyrics + "\n";
    }
}

