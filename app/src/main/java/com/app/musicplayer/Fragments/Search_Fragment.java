package com.app.musicplayer.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.musicplayer.FireBase.FSong;
import com.app.musicplayer.FireBase.Load_SongList;
import com.app.musicplayer.R;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class Search_Fragment extends Fragment {
    private FirebaseStorage storage;
    private StorageReference storageRef;
    Context context;

    public Search_Fragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Load_SongList list=new Load_SongList();
        AbstractList<FSong>song_list=new ArrayList<>();


        return view;
    }


}