package com.app.musicplayer.FireBase;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Load_SongList {
    FirebaseStorage storage;
    StorageReference song_list;
    private Load listener;

    public Load_SongList() {
        get_song_list();
    }

    public void get_song_list() {
        ArrayList<FSong> list = new ArrayList<>();

        storage = FirebaseStorage.getInstance();
        song_list = storage.getReference().child("Songs");

        song_list.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for (StorageReference item : listResult.getPrefixes()) {
                    FSong song = new FSong();
                    song.setTitle(item.getName());

                    StorageReference data = storage.getReference().child("Songs").child(item.getName()).child("song").child(item.getName() + ".mp3");
                    data.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                        @Override
                        public void onSuccess(StorageMetadata storageMetadata) {
                            String artistName = storageMetadata.getCustomMetadata("artistName");
                            Log.d("Load_song_list", "art_name_fetched");
                            song.setArtist(artistName);
                        }
                    });

                    data.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            song.setSong_url(uri.toString());
                            StorageReference image = storage.getReference().child("Songs").child(item.getName()).child("images").child(item.getName() + ".jpg");
                            image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    song.setImage_url(uri.toString());
                                    Log.d("Load_song_list", "image_url_fetched");
                                    list.add(song);
                                    if (list.size() == listResult.getPrefixes().size()) {
                                        Collections.sort(list, new Comparator<FSong>() {
                                            @Override
                                            public int compare(FSong song1, FSong song2) {
                                                return song1.getTitle().compareTo(song2.getTitle());
                                            }
                                        });
                                        listener.onLoad(list);
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle failure
            }
        });
    }

    public void setLoadListener(Load listener) {
        this.listener = listener;
    }

    public interface Load {
        void onLoad(ArrayList<FSong> Song_List);
    }
}
