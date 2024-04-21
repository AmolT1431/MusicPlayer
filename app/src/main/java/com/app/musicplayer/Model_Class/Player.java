package com.app.musicplayer.Model_Class;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;

public class Player {

    public static MediaPlayer mediaPlayer;
    Context context;

    public Player(Context context, MediaPlayer mediaPlayer) {
        this.context = context;
        this.mediaPlayer = mediaPlayer;

    }

    public void play(String Id) {

        String data = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI + "/" + Id;
        Uri suri = Uri.parse(data);


//        if (mediaPlayer.isPlaying())
//        {
//            mediaPlayer.reset();
//        }
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(context, suri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            Toast.makeText(context, "Song is corrupted", Toast.LENGTH_SHORT).show();
        }
        mediaPlayer.start();

    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }

    }

    public void start() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }

    }

    public boolean is_playing() {
        return mediaPlayer.isPlaying();
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }
    public int getMax()
    {
        return mediaPlayer.getDuration();
    }

}
