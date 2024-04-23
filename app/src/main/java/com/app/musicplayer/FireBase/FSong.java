package com.app.musicplayer.FireBase;

public class FSong {
    String title;
    String artist;
    String image_url;
    String Song_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSong_url() {
        return Song_url;
    }

    public void setSong_url(String song_url) {
        Song_url = song_url;
    }
}
