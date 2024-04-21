package com.app.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.musicplayer.Adapters.Song_List_View_Holder;
import com.app.musicplayer.Layouts.Bottom_Navigation_Bar;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    ImageView song_img;
    TextView song_name_txt;
    Button play;
    MainActivity mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        song_img = findViewById(R.id.song_image);
        play = findViewById(R.id.play_btn);
        song_name_txt = findViewById(R.id.song_name);



    }



}