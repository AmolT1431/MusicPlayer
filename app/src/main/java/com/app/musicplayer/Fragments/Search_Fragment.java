package com.app.musicplayer.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.musicplayer.Adapters.Fetch_Songs;
import com.app.musicplayer.Adapters.Online_Song_LIst_View_Holder;
import com.app.musicplayer.Adapters.Song_List_View_Holder;
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


public class Search_Fragment extends Fragment implements Online_Song_LIst_View_Holder.OnItemClickListener {

    RecyclerView recyclerView;
    Online_Song_LIst_View_Holder adapter;
    Context context;
    ArrayList<FSong> songs;
    private update update;

    public Search_Fragment(Context context) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        context = getActivity();

        recyclerView = view.findViewById(R.id.song_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (songs == null) {
            Load_SongList list = new Load_SongList();
            list.setLoadListener(new Load_SongList.Load() {
                @Override
                public void onLoad(ArrayList<FSong> Song_List) {
                    songs = Song_List;
                    adapter = new Online_Song_LIst_View_Holder(context, songs,Search_Fragment.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged(); // Notify data set changed

                }
            });

        } else {
            adapter = new Online_Song_LIst_View_Holder(context, songs,this::onItemClick);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged(); // Notify data set changed
        }


        return view;
    }


    @Override
    public void onItemClick(String song_name, String url, String artist, String image_url) {
        update.onChange(song_name, url, artist, image_url);

    }

    public void onUpdateListener(update listener) {
        this.update = listener;
    }


    public interface update {
        void onChange(String song_name, String song_url, String artist, String Song_img_url);
    }

}