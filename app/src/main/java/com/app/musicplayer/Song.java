package com.app.musicplayer;

import android.graphics.Bitmap;

public class Song {
    String name;
    String id;
    String art_name;

    public String getArt_name() {
        return art_name;
    }

    public void setArt_name(String art_name) {
        this.art_name = art_name;
    }

    Bitmap song_img;

    public Bitmap getSong_img() {
        return song_img;
    }

    public void setSong_img(Bitmap song_img) {
        this.song_img = song_img;
    }

    public Song() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
