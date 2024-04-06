package com.app.musicplayer.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.musicplayer.Adapters.Song_List_View_Holder;
import com.app.musicplayer.Fetch_Songs;
import com.app.musicplayer.MainActivity;
import com.app.musicplayer.R;
import com.app.musicplayer.Song;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class Home_Fragment extends Fragment implements Song_List_View_Holder.OnItemClickListener {

    RecyclerView song_list;
    Context context;
    private update update;
    Song_List_View_Holder list;
    ArrayList<Song> songs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        song_list = view.findViewById(R.id.song_lists);


        context = getActivity();
        song_list.setLayoutManager(new LinearLayoutManager(context));

        if (songs == null) {
            Fetch_Songs fetchSongs = new Fetch_Songs(context);
            songs = fetchSongs.Load_Songs();
        }
        list = new Song_List_View_Holder(context, songs, this::onItemClick);
        song_list.setAdapter(list);
        song_list.setHasFixedSize(true);

        return view;
    }

    public void onUpdateListener(update listener) {
        this.update = listener;
    }

    @Override
    public void onItemClick(String song_name, String id, String artist, Bitmap Song_img) {
        update.onChange(song_name, id, artist, Song_img);
    }

    public interface update {
        void onChange(String song_name, String id, String artist, Bitmap Song_img);
    }

    ;

}