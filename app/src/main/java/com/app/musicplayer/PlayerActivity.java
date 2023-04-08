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
    ArrayList<String> names;
    ArrayList<String> ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.puase);
        song = (TextView) findViewById(R.id.songname);
        getSupportActionBar().hide();

        Intent s = getIntent();
       names= s.getStringArrayListExtra("SongName");
        int position = s.getIntExtra("position", 0);
        ids = s.getStringArrayListExtra("songId");


        song.setText(names.get(position).toString());


        String uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI + "/" + ids.get(position).toString();
        Uri suri = Uri.parse(uri);

       mediaPlayer = new MediaPlayer();


        try {
            mediaPlayer.setDataSource(PlayerActivity.this,suri);
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