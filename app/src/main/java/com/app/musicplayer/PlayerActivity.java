package com.app.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {
    TextView song;
    Button play;
    Button stop;
    static MediaPlayer mediaPlayer;
    String names;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.puase);
        song = (TextView) findViewById(R.id.songname);

        Intent s = getIntent();
        names = s.getStringExtra("SongName");
        id=s.getStringExtra("songId");


        song.setText(names);


        String uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI + "/" + id;
        Uri suri = Uri.parse(uri);

        mediaPlayer = new MediaPlayer();

        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }

        try {
            mediaPlayer.setDataSource(PlayerActivity.this, suri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();

            }

        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();

            }
        });


    }


}