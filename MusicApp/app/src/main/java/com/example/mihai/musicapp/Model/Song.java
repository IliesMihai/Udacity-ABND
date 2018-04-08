package com.example.mihai.musicapp.Model;

public class Song {

    private String artist;
    private String songTitle;
    private int audio;

    public Song(String artist, String songTitle, int audio) {
        this.artist = artist;
        this.songTitle = songTitle;
        this.audio = audio;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }
}
