package com.app.musicplayer;


import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.musicplayer.FireBase.Add_Song;
import com.app.musicplayer.FireBase.FSong;
import com.app.musicplayer.FireBase.Load_SongList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.UUID;


public class Testing extends AppCompatActivity {
    // private StorageReference mStorageRef;
    private static final int PICK_AUDIO_REQUEST = 1;
    TextView name;
    TextView art;
ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        name=findViewById(R.id.song_name);
        art=findViewById(R.id.art_name);
        imageView=findViewById(R.id.song_img);

        Load_SongList list = new Load_SongList();
        list.setLoadListener(new Load_SongList.Load() {
            @Override
            public void onLoad(ArrayList<FSong> Song_List) {
                if (Song_List != null && !Song_List.isEmpty()) {
                    FSong song = Song_List.get(1);
                    Log.d("Testing", song.getTitle());
                    name.setText(song.getTitle());
                    Log.d("Testing", song.getArtist());
                    art.setText(song.getArtist());
                    Log.d("Testing_surl", song.getSong_url());
                    playSong(song.getSong_url());
                    Glide.with(Testing.this).load(song.getImage_url()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                }
            }
        });



        // selectAndUploadSong();

    }
    public void playSong(String songUrl) {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build());
            mediaPlayer.setDataSource(songUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void selectAndUploadSong() {
//        Intent intent = new Intent();
//        intent.setType("audio/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Audio"), PICK_AUDIO_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri audioUri = data.getData();
//            new Add_Song().uploadSong(Testing.this,audioUri);
//        }
//    }
//
//
//    public void upload_song() {
//
//    }
}